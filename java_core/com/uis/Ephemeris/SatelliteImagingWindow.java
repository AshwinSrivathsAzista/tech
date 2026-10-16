package org.yamcs.tctm.Azista100ProcessingUtils;

import java.util.ArrayList;
import java.util.List;

public class SatelliteImagingWindow {



    /**
     * Finds the start and stop coordinates for imaging a specific target area.
     * 
     * @param ephemerisData The parsed list of satellite coordinates over time
     * @param target The target area on the ground
     * @param maxDistanceFromPath The max cross-track distance the sensor can see (usually imageWidth / 2)
     * @return A list of valid imaging passes (Start and Stop records)
     */
    public static List<ImagingPass> findImagingWindows(
            List<EphemerisRecord> ephemerisData, 
            TargetArea target, 
            double maxDistanceFromPath) {
        
        List<ImagingPass> passes = new ArrayList<>();
        EphemerisRecord currentStart = null;
        EphemerisRecord previousRecord = null;
        
        // The effective sensor reach includes the size of the target area
        // double effectiveMaxDistance = maxDistanceFromPath + target.radiusKm;

        for (EphemerisRecord record : ephemerisData) {
            Vector3 satPos = new Vector3(record.x, record.y, record.z);
            Vector3 satVel = new Vector3(record.vx, record.vy, record.vz);
            
            // Vector from Satellite to Target
            Vector3 satToTarget = target.center.subtract(satPos);
            
            // Calculate shortest orthogonal distance from target to satellite path (velocity vector)
            // Distance = | (Target - SatPos) x SatVel | / | SatVel |
            double crossTrackDistance = satToTarget.crossProduct(satVel).magnitude() / satVel.magnitude();
            
            // boolean canSeeTarget = crossTrackDistance <= effectiveMaxDistance;


            /**
             * The satellite sees out to maxDistanceFromPath (EphemerisReader.acceptableDistanceFromPath)
             * The target's center is sitting at some distance from the path (crossTrackDistance).
             * The target's closest edge reaches outward from its center by target.radiusKm toward the satellite's path.
             */
            // This formula calculates the maximum possible distance the target's center can be from the satellite's path while still allowing the camera to see the target's outer edge
            boolean canSeeTarget = (crossTrackDistance - target.radiusKm) <= EphemerisReader.acceptableDistanceFromPath;


            if (canSeeTarget) {
                if (currentStart == null) {
                    // Target just entered the sensor's swath
                    currentStart = record;
                }
            } else {
                if (currentStart != null) {
                    // Target just exited the sensor's swath
                    passes.add(new ImagingPass(currentStart, previousRecord));
                    currentStart = null; // Reset for the next orbit/pass
                }
            }
            previousRecord = record;
        }

        // If the data ends while the camera is still on, close the pass
        if (currentStart != null && previousRecord != null) {
            passes.add(new ImagingPass(currentStart, previousRecord));
        }

        return passes;
    }

    public static void main(String[] args) {
        // 1. Load your data using the previous code
        // List<EphemerisReader.EphemerisRecord> data = EphemerisReader.readEphemerisFile("data.txt");

        EphemerisReader ephemerisReader = new EphemerisReader();
        ephemerisReader.consumeData();

        List<EphemerisRecord> data = ephemerisReader.ephemeris;
        
        

        // to begin, we will only image on nadir
        // 2. Define your parameters
        double imageWidthKm = 100.0; 
        double maxDistanceFromPath = imageWidthKm / 2.0; // 50 km to the left and right of the satellite
        
        // Define target area (e.g., a city represented by its central ECEF coordinates and a 10km radius)
        // this is supplied by the user and is the circular image area needed (note first development will be done on circular area)
        TargetArea target = new TargetArea(3000.0, 6150.0, -1000.0, 10.0);

        // 3. Process the start and stop windows
        List<ImagingPass> passes = findImagingWindows(data, target, maxDistanceFromPath);

        // 4. Output the results
        for (int i = 0; i < passes.size(); i++) {
            System.out.println("--- Imaging Pass " + (i + 1) + " ---");
            System.out.println("START CAMERA AT: " + passes.get(i).startCoord.utc);
            System.out.println("  Sat Position: X=" + passes.get(i).startCoord.x + " Y=" + passes.get(i).startCoord.y);
            System.out.println("STOP CAMERA AT:  " + passes.get(i).stopCoord.utc);
            System.out.println("  Sat Position: X=" + passes.get(i).stopCoord.x + " Y=" + passes.get(i).stopCoord.y);
        }
    }
}

// Helper class for 3D Vector Math
class Vector3 {
    double x, y, z;

    Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    Vector3 subtract(Vector3 v) {
        return new Vector3(x - v.x, y - v.y, z - v.z);
    }

    Vector3 crossProduct(Vector3 v) {
        return new Vector3(
                y * v.z - z * v.y,
                z * v.x - x * v.z,
                x * v.y - y * v.x);
    }

    double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }
}

// Represents the target area on the ground (Bounding Circle)
public class TargetArea {
    Vector3 center;
    double radiusKm; // 0 for a single point, >0 for a circular area

    public TargetArea(double x, double y, double z, double radiusKm) {
        this.center = new Vector3(x, y, z);
        this.radiusKm = radiusKm;
    }
}

// Represents a continuous time window where the camera should be ON
public class ImagingPass {
    public EphemerisRecord startCoord;
    public EphemerisRecord stopCoord;

    public ImagingPass(EphemerisRecord start, EphemerisRecord stop) {
        this.startCoord = start;
        this.stopCoord = stop;
    }
}
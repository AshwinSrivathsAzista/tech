// package org.yamcs.tctm.Azista100ProcessingUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Test
 * 
 * this file is used to only read the EphemerisRecord file
 */

public class Test {

    public static List<EphemerisRecord> ephemeris = new ArrayList<EphemerisRecord>();
    public static final double acceptableDistanceFromPath = 200;
    public static final double imageWidth = 20;

    public static void consumeData() {
        // read the .txt fmarace file
        try {
            String content = Files.readString(Path.of("sgp4_fix_latest.txt"));

            String data = content.split(
                    "# ------     ---------------   -----------------------    ---------      ---------------------     ---------------------    ----------------------     ---------------------    ----------------------     ---------------------\n")[1];

            for (String str : data.split("\n")) {
                try {
                    String[] strArr = str.split(",");

                    for (int i = 0; i < strArr.length; i++) {
                        strArr[i] = strArr[i].trim();
                    }

                    String orbitNo = strArr[0];

                    long epochTime = (long) Double.parseDouble(strArr[1]);
                    double x = Double.parseDouble(strArr[4]);
                    double y = Double.parseDouble(strArr[5]);
                    double z = Double.parseDouble(strArr[6]);
                    double vx = Double.parseDouble(strArr[7]);
                    double vy = Double.parseDouble(strArr[8]);
                    double vz = Double.parseDouble(strArr[9]);
                    EphemerisRecord loc = new EphemerisRecord(x, y, z, vx, vy, vz, epochTime);
                    ephemeris.add(loc);
                } catch (Exception e) {
                    System.out.println("issue decoding line => " + str);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static double calculateDistance(EphemerisRecord loc1, EphemerisRecord loc2) {

        double distance = Math.sqrt(Math.pow((loc1.x-loc2.x), 2) + Math.pow((loc1.y-loc2.y), 2));

        return distance;
    }

    public static void main(String[] args) {

        consumeData();



    }
}

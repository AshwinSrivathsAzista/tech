public class EphemerisRecord {
    public final double x;
    public final double y;
    public final double z;

    public final double vx;
    public final double vy;
    public final double vz;

    public final long epochTime;

    public EphemerisRecord(double x, double y, double z, double vx, double vy, double vz, long epochTime) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
        this.epochTime = epochTime;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EphemerisRecord)) {
            return false;
        } else {
            EphemerisRecord l = (EphemerisRecord) o;
            return l.epochTime == this.epochTime;
        }
    }

    @Override
    public String toString() {
        return "Location [x=" + x + ", y=" + y + ", z=" + z + ", vx=" + vx + ", y=" + vy + ", z=" + vz + ", epochTime=" + epochTime + "]";
    }

}
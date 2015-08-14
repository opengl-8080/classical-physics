package gl8080.physics.domain.primitive;

public class Point {
    public static final Point ORIGIN = new Point(0, 0, 0);
    
    public final double x;
    public final double y;
    public final double z;
    
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point add(double dx, double dy, double dz) {
        return new Point(this.x + dx, this.y + dy, this.z + dz);
    }
    
    @Override
    public String toString() {
        return "Point {" + this.x + ", " + this.y + ", " + this.z + "}";
    }
}

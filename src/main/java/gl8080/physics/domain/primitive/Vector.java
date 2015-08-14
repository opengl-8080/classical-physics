package gl8080.physics.domain.primitive;

public class Vector {
    
    public final double x;
    public final double y;
    public final double z;
    
    public static Vector create(Point from, Point to) {
        return new Vector(to.x - from.x, to.y - from.y, to.z - from.z);
    }
    
    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector normalize() {
        double norm = this.getNorm();
        return new Vector(this.x/norm, this.y/norm, this.z/norm);
    }
    
    public Vector multiply(double d) {
        return new Vector(this.x * d, this.y * d, this.z * d);
    }
    
    public double getNorm() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }
}

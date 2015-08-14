package gl8080.physics.domain.primitive;

public class Velocity {
    public static final Velocity ZERO = new Velocity(0, 0, 0);
    
    public final double x;
    public final double y;
    public final double z;
    
    public Velocity(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public double getQuantity() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }
    
    @Override
    public String toString() {
        return "Velocity {" + this.x + ", " + this.y + ", " + this.z + "}";
    }
}

package gl8080.physics.domain.primitive;

public class Force {
    public static final Force ZERO = new Force(0.0, 0.0, 0.0);
    
    public final double x;
    public final double y;
    public final double z;
    
    public Force(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    @Override
    public String toString() {
        return "Force {" + this.x + ", " + this.y + ", " + this.z + "}";
    }
}

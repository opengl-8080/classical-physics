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
}

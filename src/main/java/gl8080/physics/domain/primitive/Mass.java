package gl8080.physics.domain.primitive;

public class Mass {
    public static final Mass ZERO = new Mass(0.0);
    
    public final double quantity;

    public Mass(double quantity) {
        this.quantity = quantity;
    }
}

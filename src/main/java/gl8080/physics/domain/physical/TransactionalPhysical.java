package gl8080.physics.domain.physical;

import java.util.Objects;

import gl8080.physics.domain.Physical;
import gl8080.physics.domain.primitive.Mass;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.domain.primitive.Velocity;

public class TransactionalPhysical implements Physical {
    
    private final Physical original;
    private Point location;
    private Velocity velocity;
    
    public TransactionalPhysical(Physical original) {
        Objects.requireNonNull(original);
        this.original = original;
    }
    
    public void commit() {
        if (this.location != null) {
            this.original.setLocation(location);
        }
        
        if (this.velocity != null) {
            this.original.setVelocity(velocity);
        }
    }

    @Override
    public Velocity getVelocity() {
        return this.original.getVelocity();
    }

    @Override
    public Point getLocation() {
        return this.original.getLocation();
    }

    @Override
    public Mass getMass() {
        return this.original.getMass();
    }

    @Override
    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }
}

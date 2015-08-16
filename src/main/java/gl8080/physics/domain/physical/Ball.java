package gl8080.physics.domain.physical;

import java.util.Objects;

import gl8080.physics.domain.Physical;
import gl8080.physics.domain.primitive.Mass;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.domain.primitive.Velocity;

public class Ball implements Physical {
    
    private Point location;
    private Velocity velocity;
    private final Mass mass;
    
    public Ball(Mass mass) {
        Objects.requireNonNull(mass);
        this.mass = mass;
    }

    @Override
    public Point getLocation() {
        return this.location;
    }
    
    @Override
    public void setLocation(Point location) {
        Objects.requireNonNull(location);
        this.location = location;
    }

    @Override
    public Velocity getVelocity() {
        return this.velocity;
    }
    
    @Override
    public void setVelocity(Velocity velocity) {
        Objects.requireNonNull(velocity);
        this.velocity = velocity;
    }
    
    @Override
    public Mass getMass() {
        return this.mass;
    }
}

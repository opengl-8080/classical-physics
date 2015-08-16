package gl8080.physics.domain.dummy;

import gl8080.physics.domain.Physical;
import gl8080.physics.domain.primitive.Mass;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.domain.primitive.Velocity;

public class DummyObject implements Physical {
    
    @Override
    public String toString() {
        return "DummyObject!!";
    }

    @Override
    public Velocity getVelocity() {
        return Velocity.ZERO;
    }

    @Override
    public Point getLocation() {
        return Point.ORIGIN;
    }

    @Override
    public Mass getMass() {
        return Mass.ZERO;
    }

    @Override
    public void setLocation(Point location) {
        // ignore
    }

    @Override
    public void setVelocity(Velocity velocity) {
        // ignore
    }
}

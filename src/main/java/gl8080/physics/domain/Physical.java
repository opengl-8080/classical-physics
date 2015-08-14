package gl8080.physics.domain;

import gl8080.physics.domain.primitive.Mass;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.domain.primitive.Velocity;

public interface Physical {

    default Velocity getVelocity() {
        return Velocity.ZERO;
    }

    default Point getLocation() {
        return Point.ORIGIN;
    }

    default Mass getMass() {
        return Mass.ZERO;
    }

    default void setLocation(Point location) {}
    default void setVelocity(Velocity velocity) {}
}

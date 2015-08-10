package gl8080.physics.domain;

import gl8080.physics.domain.primitive.Point;
import gl8080.physics.domain.primitive.Velocity;

public interface Physical {

    default Velocity getVelocity() {
        return Velocity.ZERO;
    }

    default Point getLocation() {
        return Point.ORIGIN;
    }

    default void setLocation(Point location) {}
}

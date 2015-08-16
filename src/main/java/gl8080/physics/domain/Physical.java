package gl8080.physics.domain;

import gl8080.physics.domain.primitive.Mass;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.domain.primitive.Velocity;

public interface Physical {

    Velocity getVelocity();
    Point getLocation();
    Mass getMass();
    void setLocation(Point location);
    void setVelocity(Velocity velocity);
}

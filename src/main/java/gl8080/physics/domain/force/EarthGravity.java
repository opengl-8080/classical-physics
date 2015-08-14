package gl8080.physics.domain.force;

import gl8080.physics.domain.ActingForce;
import gl8080.physics.domain.Physical;
import gl8080.physics.domain.primitive.Force;
import gl8080.physics.domain.primitive.Mass;

public class EarthGravity implements ActingForce {

    @Override
    public Force getForce(Physical target) {
        Mass mass = target.getMass();
        return new Force(0.0, mass.quantity * -9.8, 0.0);
    }
}

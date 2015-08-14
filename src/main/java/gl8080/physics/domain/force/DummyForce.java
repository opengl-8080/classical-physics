package gl8080.physics.domain.force;

import gl8080.physics.domain.ActingForce;
import gl8080.physics.domain.Physical;
import gl8080.physics.domain.primitive.Force;

public class DummyForce implements ActingForce {

    @Override
    public Force getForce(Physical target) {
        return Force.ZERO;
    }

}

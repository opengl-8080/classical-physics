package gl8080.physics.domain;

import gl8080.physics.domain.primitive.Force;

public interface ActingForce {
    
    Force getForce(Physical target);
}

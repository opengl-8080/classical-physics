package gl8080.physics.domain.law;

import gl8080.physics.domain.Physical;
import gl8080.physics.domain.PhysicalLaw;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.domain.primitive.Velocity;

/**
 * 運動の法則
 */
public class LawOfMotion implements PhysicalLaw {
    
    @Override
    public void apply(Physical physical, double sec) {
        Velocity velocity = physical.getVelocity();
        Point from = physical.getLocation();
        
        Point to = from.add(velocity.x * sec, velocity.y * sec, velocity.z * sec);
        
        physical.setLocation(to);
    }
}

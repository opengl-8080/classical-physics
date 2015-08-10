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
    public void apply(Physical physical, double t) {
        Velocity v = physical.getVelocity();
        Point p = physical.getLocation();
        
        Point pt = p.add(t*v.x, t*v.y, t*v.z);
        
        physical.setLocation(pt);
    }
}

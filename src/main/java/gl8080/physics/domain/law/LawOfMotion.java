package gl8080.physics.domain.law;

import java.util.Objects;

import gl8080.physics.domain.ActingForce;
import gl8080.physics.domain.Physical;
import gl8080.physics.domain.PhysicalLaw;
import gl8080.physics.domain.primitive.Force;
import gl8080.physics.domain.primitive.Mass;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.domain.primitive.Velocity;

public class LawOfMotion implements PhysicalLaw {
    
    private final ActingForce actingForce;

    public LawOfMotion(ActingForce actingForce) {
        Objects.requireNonNull(actingForce);
        this.actingForce = actingForce;
    }

    @Override
    public void apply(Physical physical, double t) {
        Force f = this.actingForce.getForce(physical);
        Velocity v0 = physical.getVelocity();
        Mass m = physical.getMass();
        
        Velocity vt = this.calcVelocity(v0, f, m, t);
        
        Point p0 = physical.getLocation();
        Point pt = this.calcLocation(p0, v0, vt, t);
        
        physical.setVelocity(vt);
        physical.setLocation(pt);
    }
    
    private Velocity calcVelocity(Velocity v0, Force f, Mass m, double t) {
        double vtx = v0.x + f.x * t / m.quantity;
        double vty = v0.y + f.y * t / m.quantity;
        double vtz = v0.z + f.z * t / m.quantity;
        
        return new Velocity(vtx, vty, vtz);
    }
    
    private Point calcLocation(Point p0, Velocity v0, Velocity vt, double t) {
        double xt = p0.x + t * (v0.x + vt.x) / 2.0;
        double yt = p0.y + t * (v0.y + vt.y) / 2.0;
        double zt = p0.z + t * (v0.z + vt.z) / 2.0;
        
        return new Point(xt, yt, zt);
    }
}

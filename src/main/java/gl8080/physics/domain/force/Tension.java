package gl8080.physics.domain.force;

import java.util.Objects;

import gl8080.physics.domain.ActingForce;
import gl8080.physics.domain.Physical;
import gl8080.physics.domain.primitive.Force;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.domain.primitive.Vector;
import gl8080.physics.domain.primitive.Velocity;

public class Tension implements ActingForce {
    
    private final Point fixdPoint;
    
    public Tension(Point fixdPoint) {
        Objects.requireNonNull(fixdPoint);
        this.fixdPoint = fixdPoint;
    }

    @Override
    public Force getForce(Physical target) {
        // 重力と
        Force gravityForce = this.calcGravityForce(target);
        // 張力を求めて
        Force tensionForce = this.calcTensionForce(target);
        
        // ２つの合力を返す
        return gravityForce.add(tensionForce);
    }

    private Force calcGravityForce(Physical target) {
        return new Force(0.0, target.getMass().quantity * -9.8, 0.0);
    }

    private Force calcTensionForce(Physical target) {
        double m = target.getMass().quantity;
        double g = 9.8;
        Vector op = Vector.create(this.fixdPoint, target.getLocation());
        Velocity v = target.getVelocity();
        double vv = v.x*v.x + v.y*v.y + v.z*v.z;
        double opop = op.x*op.x + op.y*op.y + op.z*op.z;
        
        double k = m * (g*op.y - vv) / opop;
        
        double fx = k * op.x;
        double fy = k * op.y;
        double fz = k * op.z;
        
        return new Force(fx, fy, fz);
    }
}

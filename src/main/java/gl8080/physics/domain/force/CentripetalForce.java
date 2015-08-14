package gl8080.physics.domain.force;

import java.util.Objects;

import gl8080.physics.domain.ActingForce;
import gl8080.physics.domain.Physical;
import gl8080.physics.domain.primitive.Force;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.domain.primitive.Vector;
import gl8080.physics.domain.primitive.Velocity;

public class CentripetalForce implements ActingForce {
    
    private final Point center;
    
    public CentripetalForce(Point center) {
        Objects.requireNonNull(center);
        this.center = center;
    }

    @Override
    public Force getForce(Physical target) {
        
        Point p = target.getLocation();

        // 力の大きさを求める
        double r = Math.sqrt(
                     Math.pow(this.center.x - p.x, 2)
                   + Math.pow(this.center.y - p.y, 2)
                   + Math.pow(this.center.z - p.z, 2)
                 );
        double m = target.getMass().quantity;
        Velocity v = target.getVelocity();
        double vv = Math.pow(v.x, 2) + Math.pow(v.y, 2) + Math.pow(v.z, 2);
        
        double quantityOfForce = m * vv / r;
        
        // po ベクトルを求め
        Vector po = Vector.create(p, this.center);
        // 正規化し
        Vector e = po.normalize();
        // 力の大きさを掛ける
        Vector f = e.multiply(quantityOfForce);

        return new Force(f.x, f.y, f.z);
    }
}

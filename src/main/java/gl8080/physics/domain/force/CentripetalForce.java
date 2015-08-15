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
        double rx = this.center.x - p.x;
        double ry = this.center.y - p.y;
        double rz = this.center.z - p.z;
        double r = Math.sqrt(rx*rx + ry*ry + rz*rz);
        
        Velocity v = target.getVelocity();
        double vv = v.x*v.x + v.y*v.y + v.z*v.z;
        
        double m = target.getMass().quantity;
        
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

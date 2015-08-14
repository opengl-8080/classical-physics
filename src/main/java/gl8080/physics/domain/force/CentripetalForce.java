package gl8080.physics.domain.force;

import java.util.Objects;

import gl8080.physics.domain.ActingForce;
import gl8080.physics.domain.Physical;
import gl8080.physics.domain.primitive.Force;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.domain.primitive.Vector;

public class CentripetalForce implements ActingForce {
    
    private final Point center;
    private final double quantityOfForce;
    
    public CentripetalForce(Point center, double quantity) {
        Objects.requireNonNull(center);
        this.center = center;
        this.quantityOfForce = quantity;
    }

    @Override
    public Force getForce(Physical target) {
        Point p = target.getLocation();
        
        // po ベクトルを求め
        Vector po = Vector.create(p, this.center);
        // 正規化し
        Vector e = po.normalize();
        // 力の大きさを掛ける
        Vector f = e.multiply(this.quantityOfForce);
        
        return new Force(f.x, f.y, f.z);
    }
}

package gl8080.physics.domain.force;

import java.util.Objects;
import java.util.Set;

import gl8080.physics.domain.ActingForce;
import gl8080.physics.domain.Physical;
import gl8080.physics.domain.World;
import gl8080.physics.domain.primitive.Force;
import gl8080.physics.domain.primitive.Vector;

public class UniversalGravitation implements ActingForce {
    
    private static final double G = 6.67 * Math.pow(10.0, -11.0);
    private final World world;
    
    public UniversalGravitation(World world) {
        Objects.requireNonNull(world);
        this.world = world;
    }

    @Override
    public Force getForce(Physical target) {
        Set<Physical> others = this.world.getPhysicalsWithout(target);
        
        return others.stream()
                     .map(other -> this.calcForce(target, other))
                     .reduce(Force.ZERO, (f1, f2) -> f1.add(f2));
    }
    
    private Force calcForce(Physical me, Physical other) {
        Vector vector = Vector.create(me.getLocation(), other.getLocation());
        double rr = vector.x*vector.x + vector.y*vector.y + vector.z*vector.z;
        double m = me.getMass().quantity;
        double M = other.getMass().quantity;
        
        double F = G * M * m / rr;
        
        Vector f = vector.normalize().multiply(F);
        
        return new Force(f.x, f.y, f.z);
    }
}

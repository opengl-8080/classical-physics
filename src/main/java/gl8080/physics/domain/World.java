package gl8080.physics.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import gl8080.physics.domain.physical.TransactionalPhysical;

public class World {
    
    private Set<TransactionalPhysical> physicals = new HashSet<>();
    private Set<PhysicalLaw> physicalLaws = new HashSet<>();
    
    void next(double sec) {
        this.physicals.forEach(physical -> {
            this.physicalLaws.forEach(law -> {
                law.apply(physical, sec);
            });
        });
        
        this.physicals.forEach(physical -> {
            physical.commit();
        });
    }
    
    public void addPhysical(Physical physical) {
        Objects.requireNonNull(physical);
        this.physicals.add(new TransactionalPhysical(physical));
    }
    
    public void addPhysicalLaws(PhysicalLaw law) {
        Objects.requireNonNull(law);
        this.physicalLaws.add(law);
    }

    public Set<Physical> getPhysicalsWithout(Physical target) {
        return this.physicals
                    .stream()
                    .filter(p -> p != target)
                    .collect(Collectors.toSet());
    }
}

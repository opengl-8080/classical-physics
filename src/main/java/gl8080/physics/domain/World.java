package gl8080.physics.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class World {
    
    private Set<Physical> physicals = new HashSet<>();
    private Set<PhysicalLaw> physicalLaws = new HashSet<>();
    
    void next(double sec) {
        this.physicals.forEach(physical -> {
            this.physicalLaws.forEach(law -> {
                law.apply(physical, sec);
            });
        });
    }
    
    public void addPhysical(Physical physical) {
        Objects.requireNonNull(physical);
        this.physicals.add(physical);
    }
    
    public void addPhysicalLaws(PhysicalLaw law) {
        Objects.requireNonNull(law);
        this.physicalLaws.add(law);
    }
}

package gl8080.physics.domain.dummy;

import gl8080.physics.domain.Physical;
import gl8080.physics.domain.PhysicalLaw;

public class DummyLaw implements PhysicalLaw {
    
    private int i = 0;
    
    @Override
    public void apply(Physical physical, double sec) {
        System.out.println((i++) + " : " + physical);
    }
}

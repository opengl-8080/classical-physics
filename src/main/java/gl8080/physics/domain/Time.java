package gl8080.physics.domain;

import java.util.Objects;
import java.util.stream.IntStream;

public class Time {

    private final World world;
    
    public Time(World world) {
        Objects.requireNonNull(world);
        this.world = world;
    }
    
    public void start() {
        IntStream.range(0, 10).forEach(i -> {
            this.world.next();
        });
    }
}

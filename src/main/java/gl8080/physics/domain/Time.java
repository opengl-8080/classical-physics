package gl8080.physics.domain;

import java.util.Objects;

public class Time {

    private final World world;
    private static final double FRAME_LATE = 1.0 / 30.0;
    private boolean isContinued = true;
    
    public Time(World world) {
        Objects.requireNonNull(world);
        this.world = world;
    }
    
    public void start() {
        while (this.isContinued) {
            this.world.next(FRAME_LATE);
            this.sleep(FRAME_LATE);
        }
    }
    
    public void stop() {
        this.isContinued = false;
    }
    
    private void sleep(double sec) {
        try {
            Thread.sleep((long)(sec * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package gl8080.physics.domain;

import java.util.Objects;

public class Time {

    private final World world;
    private static final double FRAME_LATE = 1.0 / 30.0;
    private boolean isContinued = true;
    private int speed;
    private Runnable tick = () -> {};
    
    public Time(World world, int speed) {
        Objects.requireNonNull(world);
        this.world = world;
        this.speed = speed;
    }
    
    public Time(World world) {
        this(world, 1);
    }
    
    public void start() {
        while (this.isContinued) {
            long s = System.currentTimeMillis();
            
            for (int i=0; i<this.speed; i++) {
                this.world.next(FRAME_LATE);
            }
            
            long time = System.currentTimeMillis() - s;

            this.tick.run();
            
            if (FRAME_LATE * 1000 < time) {
                // 計算に sleep する時間以上かかっている場合は、 sleep 省略
                continue;
            }
            
            this.sleep(FRAME_LATE);
        }
    }
    
    public void setTick(Runnable tick) {
        Objects.requireNonNull(tick);
        this.tick = tick;
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

package gl8080.physics.domain.physical;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import gl8080.physics.domain.Physical;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.domain.primitive.Velocity;

public class Ball implements Physical {
    
    private Point location;
    private Velocity velocity;
    private Set<Consumer<Point>> locationListeners = new HashSet<>();
    
    public Ball(Point location, Velocity velocity) {
        Objects.requireNonNull(location);
        Objects.requireNonNull(velocity);
        this.location = location;
        this.velocity = velocity;
    }

    @Override
    public Point getLocation() {
        return this.location;
    }
    
    @Override
    public void setLocation(Point location) {
        Objects.requireNonNull(location);
        this.location = location;
        this.locationListeners.forEach(listener -> listener.accept(location));
    }

    @Override
    public Velocity getVelocity() {
        return this.velocity;
    }
    
    public void addLocationListener(Consumer<Point> listener) {
        Objects.requireNonNull(listener);
        this.locationListeners.add(listener);
    }
}

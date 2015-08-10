package gl8080.physics.view.shape;

import java.util.function.Consumer;

import gl8080.physics.domain.physical.Ball;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.view.Content;
import javafx.scene.Node;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;

public class BallShape implements Content, Consumer<Point> {
    
    private Sphere sphere;
    private Translate translate = new Translate();

    public BallShape(Ball ball, double radius) {
        this.sphere = new Sphere(radius);
        this.sphere.getTransforms().add(this.translate);
        ball.addLocationListener(this);
        this.translate(ball.getLocation());
    }
    
    public void translate(Point point) {
        this.translate.setX(point.x);
        this.translate.setY(point.y);
        this.translate.setZ(point.z);
    }
    
    @Override
    public Node getNode() {
        return this.sphere;
    }

    @Override
    public void accept(Point location) {
        this.translate(location);
    }
}

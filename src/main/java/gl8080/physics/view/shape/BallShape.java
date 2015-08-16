package gl8080.physics.view.shape;

import gl8080.physics.domain.physical.Ball;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.view.Content;
import gl8080.physics.view.ViewPoint;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;

public class BallShape implements Content {
    
    private Sphere sphere;
    private Translate translate = new Translate();
    private Ball ball;

    public BallShape(Ball ball, double radius, Color color) {
        this.sphere = new Sphere(radius);
        this.sphere.setMaterial(new PhongMaterial(color));
        this.sphere.getTransforms().add(this.translate);
        this.translate(ball.getLocation());
        this.ball = ball;
    }

    public BallShape(Ball ball, double radius) {
        this(ball, radius, Color.WHITE);
    }
    
    public void translate(Point point) {
        ViewPoint viewPoint = ViewPoint.of(point);
        this.translate.setX(viewPoint.x);
        this.translate.setY(viewPoint.y);
        this.translate.setZ(viewPoint.z);
    }
    
    @Override
    public Node getNode() {
        return this.sphere;
    }

    @Override
    public void refresh() {
        Point location = this.ball.getLocation();
        this.translate(location);
    }
}

package gl8080.physics.view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;

public class Axis implements Content {
    private static final double AXIS_THICKNESS = 0.5;
    private static final double PLANE_THICKNESS = 0.1;

    private Group group = new Group();
    
    public Axis(double length) {
        this.createAxis(length);
        this.createPlane(length);
    }
    
    private void createAxis(double length) {
        Box x = new Box(length, AXIS_THICKNESS, AXIS_THICKNESS);
        x.setMaterial(new PhongMaterial(Color.BLUE));
        x.setTranslateX(length / 2.0);
        
        Box y = new Box(AXIS_THICKNESS, length, AXIS_THICKNESS);
        y.setMaterial(new PhongMaterial(Color.RED));
        y.setTranslateY(length / 2.0);
        
        Box z = new Box(AXIS_THICKNESS, AXIS_THICKNESS, length);
        z.setMaterial(new PhongMaterial(Color.GREEN));
        z.setTranslateZ(length / 2.0);
        
        this.group.getChildren().addAll(x, y, z);
    }
    
    private void createPlane(double length) {
        Box xzPlane = new Box(length, PLANE_THICKNESS, length);
        xzPlane.setDrawMode(DrawMode.LINE);
        xzPlane.setTranslateX(length / 2.0);
        xzPlane.setTranslateZ(length / 2.0);
        
        Box xyPlane = new Box(length, length, PLANE_THICKNESS);
        xyPlane.setDrawMode(DrawMode.LINE);
        xyPlane.setTranslateX(length / 2.0);
        xyPlane.setTranslateY(length / 2.0);
        
        Box yzPlane = new Box(PLANE_THICKNESS, length, length);
        yzPlane.setDrawMode(DrawMode.LINE);
        yzPlane.setTranslateY(length / 2.0);
        yzPlane.setTranslateZ(length / 2.0);
        
        this.group.getChildren().addAll(xzPlane, xyPlane, yzPlane);
    }

    @Override
    public Node getNode() {
        return this.group;
    }

    @Override
    public void refresh() {
    }
}

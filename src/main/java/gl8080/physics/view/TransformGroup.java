package gl8080.physics.view;

import gl8080.physics.domain.Point;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class TransformGroup {

    private Group rootGroup = new Group();
    private Group ajustCenterGroup = new Group();
    private Rotate rotateX = new Rotate(0.0, Rotate.X_AXIS);
    private Rotate rotateY = new Rotate(0.0, Rotate.Y_AXIS);
    private Translate translate = new Translate();

    public TransformGroup(Point center) {
        this.ajustCenter(center.x, center.y, center.z);
        this.rootGroup.getChildren().add(this.ajustCenterGroup);
        this.rootGroup.getTransforms().addAll(rotateX, rotateY, translate);
    }
    
    private void ajustCenter(double centerX, double centerY, double centerZ) {
        this.ajustCenterGroup.setTranslateX(centerX);
        this.ajustCenterGroup.setTranslateY(centerY);
        this.ajustCenterGroup.setTranslateZ(centerZ);
    }
    
    public void add(Node node) {
        this.ajustCenterGroup.getChildren().add(node);
    }
    
    public void rotate(double dx, double dy) {
        double newX = this.rotateX.getAngle() + dx;
        double newY = this.rotateY.getAngle() + dy;
        
        if (newX < -90.0) {
            newX = -90.0;
        } else if (90.0 < newX) {
            newX = 90.0;
        }
        
        this.rotateX.setAngle(newX);
        this.rotateY.setAngle(newY);
    }
    
    Group getGroup() {
        return this.rootGroup;
    }
}

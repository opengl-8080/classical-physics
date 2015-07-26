package gl8080.physics.view;

import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;

public class Camera {
    private PerspectiveCamera camera;
    
    public Camera() {
        this.camera = new PerspectiveCamera(true);
        this.camera.setFarClip(1000.0);
        
        Rotate rotateZ = new Rotate(180.0, Rotate.Z_AXIS);
        Rotate rotateY = new Rotate(180.0, Rotate.Y_AXIS);
        this.camera.getTransforms().addAll(rotateZ, rotateY);
    }
    
    PerspectiveCamera getCamera() {
        return this.camera;
    }
    
    public void translate(double dx, double dy, double dz) {
        this.camera.setTranslateX(this.camera.getTranslateX() + dx);
        this.camera.setTranslateY(this.camera.getTranslateY() + dy);
        this.camera.setTranslateZ(this.camera.getTranslateZ() + dz);
    }
    
    public void moveBackAndFront(double dz) {
        this.translate(0.0, 0.0, dz);
    }
}

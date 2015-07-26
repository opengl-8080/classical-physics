package gl8080.physics.view;

import gl8080.physics.domain.Point;
import gl8080.physics.view.MousePosition.Difference;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Space {
    
    private SubScene mainView;
    private TransformGroup transform;
    private MousePosition mousePosition = new MousePosition();
    
    public Space(double size, Camera camera) {
        this.transform = new TransformGroup(this.createCenter(size));
        this.transform.rotate(30, -30);
        
        this.mainView = new SubScene(this.transform.getGroup(), 1200, 750);
        this.mainView.setFill(Color.WHITE);

        this.mainView.addEventHandler(MouseEvent.MOUSE_PRESSED, this.mousePosition::save);
        this.mainView.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::handleMouseDrag);
        
        this.mainView.setCamera(camera.getCamera());
        camera.moveBackAndFront(size * 2.5);
    }
    
    private Point createCenter(double size) {
        return new Point(-size / 2.0, -size / 2.0, -size / 2.0);
    }
    
    private void handleMouseDrag(MouseEvent e) {
        Difference diff = this.mousePosition.difference(e);
        
        if (e.isPrimaryButtonDown()) {
            this.transform.rotate(-diff.y * 0.1, -diff.x * 0.1);
        }
    }

    public void add(Content content) {
        this.transform.add(content.getNode());
    }
    
    public SubScene getSubScene() {
        return this.mainView;
    }
}

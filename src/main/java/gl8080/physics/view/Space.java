package gl8080.physics.view;

import java.util.HashSet;
import java.util.Set;

import gl8080.physics.view.MousePosition.Difference;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Space {
    
    private SubScene mainView;
    private TransformGroup transform;
    private Set<Content> contents = new HashSet<>();
    private MousePosition mousePosition = new MousePosition();
    private Camera camera;
    
    public Space(double size, Camera camera) {
        this.transform = new TransformGroup(this.createCenter(size));
        this.transform.rotate(30, -30); // 初期アングルを設定（ちょっと斜め上から見る感じ）
        
        this.mainView = new SubScene(this.transform.getGroup(), 1200, 750, true, SceneAntialiasing.BALANCED);
        this.mainView.setFill(Color.WHITE);

        this.mainView.addEventHandler(MouseEvent.MOUSE_PRESSED, this.mousePosition::save);
        this.mainView.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::handleMouseDrag);
        
        this.mainView.setCamera(camera.getCamera());
        camera.moveBackAndFront(size * 2.5);
        this.camera = camera;
    }
    
    private ViewPoint createCenter(double size) {
        return new ViewPoint(-size / 2.0, -size / 2.0, -size / 2.0);
    }
    
    private void handleMouseDrag(MouseEvent e) {
        Difference diff = this.mousePosition.difference(e);
        
        if (e.isPrimaryButtonDown()) { // 左ドラッグに合わせて回転
            this.transform.rotate(-diff.y * 0.1, -diff.x * 0.1);
        } else if (e.isSecondaryButtonDown()) { // 右ドラッグでカメラの Z 軸移動
            this.camera.moveBackAndFront(-diff.y * 0.1);
        }
    }

    public void add(Content content) {
        this.transform.add(content.getNode());
        this.contents.add(content);
    }
    
    public SubScene getSubScene() {
        return this.mainView;
    }

    public void refresh() {
        this.contents.forEach(Content::refresh);
    }
}

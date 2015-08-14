package gl8080.physics.view.shape;

import gl8080.physics.view.Content;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class SampleShape implements Content {
    
    private MeshView meshView;
    
    public SampleShape() {
        this.meshView = new MeshView();
        
        TriangleMesh mesh = new TriangleMesh();
        mesh.getPoints().addAll(0, 0, 10, 50, 0, 10, 25, 50, 10);
        mesh.getFaces().addAll(0, 0, 1);
        
        this.meshView.setMesh(mesh);
        this.meshView.setMaterial(new PhongMaterial(Color.RED));
    }
    
    @Override
    public Node getNode() {
        return this.meshView;
    }

}

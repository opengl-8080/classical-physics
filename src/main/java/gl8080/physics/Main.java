package gl8080.physics;

import gl8080.physics.view.Axis;
import gl8080.physics.view.Camera;
import gl8080.physics.view.Space;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        Scene root = new Scene(createContent());
        primaryStage.setScene(root);
        primaryStage.show();
    }

    public Parent createContent() throws Exception {
        final double size = 100;
        Space space = new Space(size, new Camera());
        space.add(new Axis(size));
        
        return new Group(space.getSubScene());
    }
}

package gl8080.physics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gl8080.physics.domain.Time;
import gl8080.physics.domain.World;
import gl8080.physics.domain.law.LawOfMotion;
import gl8080.physics.domain.physical.Ball;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.domain.primitive.Velocity;
import gl8080.physics.view.Axis;
import gl8080.physics.view.Camera;
import gl8080.physics.view.Space;
import gl8080.physics.view.shape.BallShape;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private ExecutorService service = Executors.newSingleThreadExecutor();
    private Time time;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        Scene root = new Scene(createContent());
        primaryStage.setScene(root);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(e -> {
            this.time.stop();
            this.service.shutdown();
        });
    }

    public Parent createContent() throws Exception {
        // ボールを作る
        Point location = new Point(10, 10, 10); // 初期位置
        Velocity velocity = new Velocity(5.0, 2.0, 7.0); // 速度
        Ball ball = new Ball(location, velocity);
        
        // 実験空間の用意
        final double size = 100;
        Space space = new Space(size, new Camera());
        space.add(new Axis(size));
        
        // ボールのシェイプを作り、空間に追加する
        BallShape ballShape = new BallShape(ball, 1.0);
        space.add(ballShape);
        
        // 世界を作り、ボールと物理法則を追加する
        World world = new World();
        world.addPhysical(ball);
        world.addPhysicalLaws(new LawOfMotion());
        
        // 時の流れをスタートさせる
        this.time = new Time(world);
        
        this.service.execute(() -> {
            this.time.start();
        });
        
        return new Group(space.getSubScene());
    }
}

package gl8080.physics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gl8080.physics.domain.ActingForce;
import gl8080.physics.domain.PhysicalLaw;
import gl8080.physics.domain.Time;
import gl8080.physics.domain.World;
import gl8080.physics.domain.force.Tension;
import gl8080.physics.domain.law.LawOfMotion;
import gl8080.physics.domain.physical.Ball;
import gl8080.physics.domain.primitive.Mass;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.domain.primitive.Velocity;
import gl8080.physics.view.Axis;
import gl8080.physics.view.Camera;
import gl8080.physics.view.Space;
import gl8080.physics.view.shape.BallLocus;
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
        Mass mass = new Mass(10.0); // 質量
        Point location = new Point(80.0, 40.0, 20.0); // 初期位置
        Velocity velocity = Velocity.ZERO; // 速度
        Ball ball = new Ball(mass);
        ball.setLocation(location);
        ball.setVelocity(velocity);
        
        // 実験空間の用意
        final double size = 100;
        Space space = new Space(size, new Camera());
        space.add(new Axis(size));
        
        // ボールのシェイプを作り、空間に追加する
        BallShape ballShape = new BallShape(ball, 1.0);
        space.add(ballShape);

//        // 物理法則を作り、力に向心力を設定
//        Point center = new Point(50.0, 50.0, 50.0);
//        ActingForce actingForce = new CentripetalForce(center);
//        PhysicalLaw law = new LawOfMotion(actingForce);
        
        // 物理法則を作り、力に張力を設定
        Point center = new Point(50.0, 100.0, 50.0);
        ActingForce actingForce = new Tension(center);
        PhysicalLaw law = new LawOfMotion(actingForce);
        
        // 軌跡
        BallLocus locus = BallLocus.create(law).historySize(20).radius(0.5).build();
        space.add(locus);

        // 世界を作り、ボールと物理法則を追加する
        World world = new World();
        world.addPhysical(ball);
        world.addPhysicalLaws(locus);
        
        // 時の流れをスタートさせる
        Thread.sleep(500); // 何故かマウスドラッグが効かなくなることがある
        this.time = new Time(world);
        
        this.service.execute(() -> {
            this.time.start();
        });
        
        return new Group(space.getSubScene());
    }
}

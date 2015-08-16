package gl8080.physics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gl8080.physics.domain.ActingForce;
import gl8080.physics.domain.PhysicalLaw;
import gl8080.physics.domain.Time;
import gl8080.physics.domain.World;
import gl8080.physics.domain.force.UniversalGravitation;
import gl8080.physics.domain.law.LawOfMotion;
import gl8080.physics.domain.physical.Ball;
import gl8080.physics.domain.primitive.Mass;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.domain.primitive.Velocity;
import gl8080.physics.view.Axis;
import gl8080.physics.view.Camera;
import gl8080.physics.view.Space;
import gl8080.physics.view.ViewPoint;
import gl8080.physics.view.shape.BallLocus;
import gl8080.physics.view.shape.BallShape;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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
        final double size = 100;
        
        // 一辺が 30,000,000km になるように縮尺を設定
        ViewPoint.setRate(size, 30_000_000);
        
        // 実験空間の用意
        Space space = new Space(size, new Camera());
        space.add(new Axis(size));

        // 世界を作り、ボールと物理法則を追加する
        World world = new World();

        // 恒星
        createBall()
            .mass(1.0 * Math.pow(10, 30))
            .locationKm(15_000_000, 15_000_000, 15_000_000)
            .velocityKm(0, 0, 0)
            .radius(5.0)
            .color(Color.RED)
            .appendTo(world, space);
        
        // 惑星
        createBall()
            .mass(1.8986 * Math.pow(10, 27))
            .locationKm(30_000_000, 15_000_000, 15_000_000)
            .velocityKm(0, 0, -67)
            .radius(0.5)
            .color(Color.BLUE)
            .appendTo(world, space);
        
        // 衛星
        createBall()
            .mass(8.9 * Math.pow(10, 22))
            .locationKm(29_600_000, 15_000_000, 15_000_000)
            .velocityKm(0, 0, -50)
            .radius(0.4)
            .color(Color.YELLOW)
            .appendTo(world, space);
        
        // 物理法則を作り、力に万有引力を設定
        ActingForce actingForce = new UniversalGravitation(world);
        PhysicalLaw law = new LawOfMotion(actingForce);
        
        // 軌跡
        BallLocus locus = BallLocus.create(law).historySize(100).color(Color.GRAY).interval(100000).radius(0.2).build();
        space.add(locus);
        world.addPhysicalLaws(locus);
        
        // 時の流れをスタートさせる
        this.time = new Time(world, 50000);
        this.time.setTick(() -> {
            space.refresh();
        });
        
        this.service.execute(() -> {
            try {
                Thread.sleep(500); // 何故かマウスドラッグが効かなくなることがある
            } catch (Exception e) {
                e.printStackTrace();
            } 
            this.time.start();
        });
        
        return new Group(space.getSubScene());
    }
    
    private static BallAppender createBall() {
        return new BallAppender();
    }
    
    static class BallAppender {
        private Mass mass = new Mass(10.0);
        private Point location = Point.ORIGIN;
        private Velocity velocity = Velocity.ZERO;
        private double radius = 1.0;
        private Color color = Color.WHITE;
        
        public BallAppender mass(double mass) {
            this.mass = new Mass(mass);
            return this;
        }
        
        public BallAppender location(double x, double y, double z) {
            this.location = new Point(x, y, z);
            return this;
        }
        
        public BallAppender locationKm(double x, double y, double z) {
            return this.location(x * 1000.0, y * 1000.0, z * 1000.0);
        }
        
        public BallAppender velocity(double x, double y, double z) {
            this.velocity = new Velocity(x, y, z);
            return this;
        }
        
        public BallAppender velocityKm(double x, double y, double z) {
            return this.velocity(x * 1000.0, y * 1000.0, z * 1000.0);
        }
        
        public BallAppender radius(double radius) {
            this.radius = radius;
            return this;
        }
        
        public BallAppender color(Color color) {
            this.color = color;
            return this;
        }
        
        public void appendTo(World world, Space space) {
            Ball ball = new Ball(this.mass);
            ball.setLocation(this.location);
            ball.setVelocity(this.velocity);
            
            BallShape shape = new BallShape(ball, this.radius, this.color);
            
            world.addPhysical(ball);
            space.add(shape);
        }
    }
}

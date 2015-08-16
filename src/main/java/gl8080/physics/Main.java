package gl8080.physics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gl8080.physics.domain.ActingForce;
import gl8080.physics.domain.PhysicalLaw;
import gl8080.physics.domain.Time;
import gl8080.physics.domain.World;
import gl8080.physics.domain.force.CentripetalForce;
import gl8080.physics.domain.force.DummyForce;
import gl8080.physics.domain.force.EarthGravity;
import gl8080.physics.domain.force.Tension;
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
        
//        Parent parent = this.firstLawOfMotion(); // 等速直線運動
//        Parent parent = this.secondLawOfMotion(); // 放物線運動
//        Parent parent = this.circle(); // 円運動
//        Parent parent = this.tension(); // 振り子
//        Parent parent = this.universalGravitation1(); // 万有引力（冥王星 - カロン）
//        Parent parent = this.universalGravitation2(); // 万有引力（彗星）
        Parent parent = this.universalGravitation3(); // 万有引力（恒星 - 惑星 - 衛星）
        
        Scene root = new Scene(parent);
        primaryStage.setScene(root);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(e -> {
            this.time.stop();
            this.service.shutdown();
        });
    }

    /**
     * 運動の第１法則（等速直線運動）
     */
    public Parent firstLawOfMotion() throws Exception {
        final double size = 100;
        
        // 実験空間の用意
        Space space = new Space(size, new Camera());
        space.add(new Axis(size));

        // 世界を作り、ボールと物理法則を追加する
        World world = new World();

        // ボール
        createBall()
            .location(80, 10, 10)
            .velocity(-5.0, 3.0, 6.0)
            .color(Color.BLUE)
            .appendTo(world, space);
        
        // 物理法則を作り、力はダミーを設定
        ActingForce actingForce = new DummyForce();
        PhysicalLaw law = new LawOfMotion(actingForce);
        
        // 軌跡
        BallLocus locus = BallLocus.create(law).build();
        space.add(locus);
        world.addPhysicalLaws(locus);
        
        // 時の流れをスタートさせる
        this.startTime(world, space);
        
        return new Group(space.getSubScene());
    }

    /**
     * 運動の第２法則（運動方程式）
     */
    public Parent secondLawOfMotion() throws Exception {
        final double size = 100;
        
        // 実験空間の用意
        Space space = new Space(size, new Camera());
        space.add(new Axis(size));

        // 世界を作り、ボールと物理法則を追加する
        World world = new World();

        // ボール
        createBall()
            .mass(10.0)
            .location(0.0, 0.0, 100.0)
            .velocity(12.5, 40.0, -12.5)
            .color(Color.BLUE)
            .appendTo(world, space);
        
        // 世界を作り、ボールと物理法則を追加する
        ActingForce actingForce = new EarthGravity();
        PhysicalLaw law = new LawOfMotion(actingForce);
        
        // 軌跡
        BallLocus locus = BallLocus.create(law).build();
        space.add(locus);
        world.addPhysicalLaws(locus);
        
        // 時の流れをスタートさせる
        this.startTime(world, space);
        
        return new Group(space.getSubScene());
    }

    /**
     * 円運動
     */
    public Parent circle() throws Exception {
        final double size = 100;
        
        // 実験空間の用意
        Space space = new Space(size, new Camera());
        space.add(new Axis(size));

        // 世界を作り、ボールと物理法則を追加する
        World world = new World();

        // ボール
        createBall()
            .mass(10.0)
            .location(50.0, 80.0, 25.0)
            .velocity(20.0, 0.0, 0.0)
            .color(Color.BLUE)
            .appendTo(world, space);
        
        // 物理法則を作り、力に向心力を設定
        Point center = new Point(50.0, 50.0, 50.0);
        ActingForce actingForce = new CentripetalForce(center);
        PhysicalLaw law = new LawOfMotion(actingForce);
        
        // 軌跡
        BallLocus locus = BallLocus.create(law).build();
        space.add(locus);
        world.addPhysicalLaws(locus);
        
        // 時の流れをスタートさせる
        this.startTime(world, space);
        
        return new Group(space.getSubScene());
    }

    /**
     * 振り子
     */
    public Parent tension() throws Exception {
        final double size = 100;
        
        // 実験空間の用意
        Space space = new Space(size, new Camera());
        space.add(new Axis(size));

        // 世界を作り、ボールと物理法則を追加する
        World world = new World();

        // ボール
        createBall()
            .mass(10.0)
            .location(80.0, 40.0, 20.0)
            .color(Color.BLUE)
            .appendTo(world, space);
        
        // 物理法則を作り、力に張力を設定
        Point center = new Point(50.0, 100.0, 50.0);
        ActingForce actingForce = new Tension(center);
        PhysicalLaw law = new LawOfMotion(actingForce);
        
        // 軌跡
        BallLocus locus = BallLocus.create(law).build();
        space.add(locus);
        world.addPhysicalLaws(locus);
        
        // 時の流れをスタートさせる
        this.startTime(world, space);
        
        return new Group(space.getSubScene());
    }
    
    /**
     * 万有引力（冥王星 - カロン）
     */
    public Parent universalGravitation1() throws Exception {
        final double size = 100;
        
        // 一辺が 50,000 km になるように縮尺を設定
        ViewPoint.setRate(size, 50_000.0);
        
        // 実験空間の用意
        Space space = new Space(size, new Camera());
        space.add(new Axis(size));

        // 世界を作り、ボールと物理法則を追加する
        World world = new World();

        // 冥王星
        createBall()
            .mass(1.3 * Math.pow(10, 22))
            .locationKm(25000, 25000, 25000)
            .velocityKm(0, 0, -0.02)
            .radius(2.0)
            .color(Color.RED)
            .appendTo(world, space);

        // カロン
        createBall()
            .mass(1.52 * Math.pow(10, 21))
            .locationKm(25000 + 19571, 25000, 25000)
            .velocityKm(0, 0, 0.185)
            .radius(1.0)
            .color(Color.BLUE)
            .appendTo(world, space);
        
        // 物理法則を作り、力に万有引力を設定
        ActingForce actingForce = new UniversalGravitation(world);
        PhysicalLaw law = new LawOfMotion(actingForce);
        
        // 軌跡
        BallLocus locus = BallLocus.create(law)
                                   .historySize(100)
                                   .interval(100000)
                                   .radius(0.5)
                                   .build();
        space.add(locus);
        world.addPhysicalLaws(locus);
        
        // 時の流れをスタートさせる
        this.startTime(world, space, 50000);
        
        return new Group(space.getSubScene());
    }
    
    /**
     * 万有引力（彗星）
     */
    public Parent universalGravitation2() throws Exception {
        final double size = 100;
        
        // 一辺が 50,000 km になるように縮尺を設定
        ViewPoint.setRate(size, 50_000.0);
        
        // 実験空間の用意
        Space space = new Space(size, new Camera());
        space.add(new Axis(size));

        // 世界を作り、ボールと物理法則を追加する
        World world = new World();

        // 恒星
        createBall()
            .mass(1.0 * Math.pow(10, 23))
            .locationKm(25000, 25000, 25000)
            .velocityKm(0, 0, 0)
            .radius(5.0)
            .color(Color.RED)
            .appendTo(world, space);

        // 彗星
        createBall()
            .mass(1.0 * Math.pow(10, 10))
            .locationKm(50000, 50000, 50000)
            .velocityKm(-0.1, 0, 0.2)
            .radius(1.0)
            .color(Color.BLUE)
            .appendTo(world, space);
        
        // 物理法則を作り、力に万有引力を設定
        ActingForce actingForce = new UniversalGravitation(world);
        PhysicalLaw law = new LawOfMotion(actingForce);
        
        // 軌跡
        BallLocus locus = BallLocus.create(law)
                                   .historySize(100)
                                   .interval(100000)
                                   .radius(0.5)
                                   .build();
        space.add(locus);
        world.addPhysicalLaws(locus);
        
        // 時の流れをスタートさせる
        this.startTime(world, space, 50000);
        
        return new Group(space.getSubScene());
    }
    
    /**
     * 万有引力（恒星 - 惑星 - 衛星）
     */
    public Parent universalGravitation3() throws Exception {
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
        BallLocus locus = BallLocus.create(law)
                                   .historySize(100)
                                   .color(Color.GRAY)
                                   .interval(100000)
                                   .radius(0.2)
                                   .build();
        space.add(locus);
        world.addPhysicalLaws(locus);
        
        // 時の流れをスタートさせる
        this.startTime(world, space, 50000);
        
        return new Group(space.getSubScene());
    }
    
    private void startTime(World world, Space space) {
        this.startTime(world, space, 1);
    }
    
    private void startTime(World world, Space space, int speed) {
        this.time = new Time(world, speed);
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

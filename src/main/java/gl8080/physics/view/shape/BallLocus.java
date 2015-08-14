package gl8080.physics.view.shape;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

import gl8080.physics.domain.Physical;
import gl8080.physics.domain.PhysicalLaw;
import gl8080.physics.domain.primitive.Point;
import gl8080.physics.view.Content;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class BallLocus implements Content, PhysicalLaw {

    private int historySzie;
    private Color color;
    private double radius;
    private int interval;
    
    private PhysicalLaw law;
    private Group group = new Group();
    private Deque<Sphere> spheres = new ArrayDeque<>();
    private int count;

    public static BallLocusBuilder create(PhysicalLaw law) {
        return new BallLocusBuilder(law);
    }
    
    @Override
    public Node getNode() {
        return this.group;
    }

    @Override
    public void apply(Physical ball, double sec) {
        this.count++;
        
        if (this.count % this.interval == 0) {
            this.drawLocus(ball);
            this.count = 0;
        } 
        
        this.law.apply(ball, sec);
    }
    
    private void drawLocus(Physical ball) {
        // Main スレッド以外で UI を操作するとエラーになるので、 Platform.runLater() を使ってメインスレッドで処理を実行する
        Platform.runLater(() -> {
            Sphere sphere = this.createSphere(ball);
            this.spheres.addLast(sphere);
            this.group.getChildren().add(sphere);
            
            if (this.historySzie < this.spheres.size()) {
                Sphere removed = this.spheres.removeFirst();
                this.group.getChildren().remove(removed);
            }
        });
    }
    
    private Sphere createSphere(Physical ball) {
        Sphere sphere = new Sphere(this.radius);
        
        Point location = ball.getLocation();
        sphere.setTranslateX(location.x);
        sphere.setTranslateY(location.y);
        sphere.setTranslateZ(location.z);
        
        Material material = new PhongMaterial(this.color);
        sphere.setMaterial(material);
        
        return sphere;
    }
    
    private BallLocus() {}
    
    public static class BallLocusBuilder {
        private BallLocus locus = new BallLocus();
        
        public BallLocusBuilder(PhysicalLaw law) {
            Objects.requireNonNull(law);
            
            this.locus.law = law;
            this.locus.radius = 1.0;
            this.locus.color = Color.YELLOW;
            this.locus.historySzie = 50;
            this.locus.interval = 10;
        }

        public BallLocusBuilder radius(double radius) {
            if (radius < 0.0) {
                throw new IllegalArgumentException();
            }
            this.locus.radius = radius;
            return this;
        }
        
        public BallLocusBuilder color(Color color) {
            Objects.requireNonNull(color);
            this.locus.color = color;
            return this;
        }
        
        public BallLocusBuilder interval(int interval) {
            if (interval <= 0) {
                throw new IllegalArgumentException();
            }
            this.locus.interval = interval;
            return this;
        }

        public BallLocusBuilder historySize(int historySize) {
            if (historySize <= 0) {
                throw new IllegalArgumentException();
            }
            this.locus.historySzie = historySize;
            return this;
        }

        public BallLocus build() {
            return this.locus;
        }
    }
}

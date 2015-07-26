package gl8080.physics.view;

import javafx.scene.input.MouseEvent;

public class MousePosition {

    private double x;
    private double y;
    
    public void save(MouseEvent e) {
        this.x = e.getSceneX();
        this.y = e.getSceneY();
    }
    
    public Difference difference(MouseEvent e) {
        double nowX = e.getSceneX();
        double nowY = e.getSceneY();
        
        double dx = this.x - nowX;
        double dy = this.y - nowY;
        
        this.x = nowX;
        this.y = nowY;
        
        return new Difference(dx, dy);
    }
    
    public static class Difference {
        public final double x;
        public final double y;
        
        private Difference(double dx, double dy) {
            this.x = dx;
            this.y = dy;
        }
    }
}

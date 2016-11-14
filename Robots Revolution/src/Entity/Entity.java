package Entity;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Entity extends Pane{
    private int x;
    private int y;

    public Entity() {
        setX(x);
        setY(y);
    }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
}

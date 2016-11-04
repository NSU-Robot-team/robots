package Entity;

import javafx.scene.layout.Pane;

public class Furniture extends Entity {
    private int X;
    private int Y;

    public Furniture(int x, int y){
        setX(x); setY(y);
    }

    @Override
    public void setX(int x) {
        this.X = x;
    }

    @Override
    public void setY(int y) {
        this.Y = y;
    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public int getY() {
        return Y;
    }
}

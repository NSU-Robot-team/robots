package Entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Robot extends Entity{
    public ImageView imgv;
    private int X;
    private int Y;

    public Robot(int x, int y) {
        super();
    }

    public Robot(String way){
        imgv = new ImageView(new Image(getClass().getResourceAsStream(way)));
        imgv.setFitHeight(60);
        imgv.setFitWidth(60);
        getChildren().addAll(imgv);
    }

    public void setImg(String way){
        imgv = new ImageView(new Image(getClass().getResourceAsStream(way)));
        imgv.setFitHeight(60);
        imgv.setFitWidth(60);
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

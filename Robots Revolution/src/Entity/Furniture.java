package Entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Furniture extends Pane implements Entity {
    public ImageView imgv;
    private int X;
    private int Y;

    public Furniture(int x, int y){
        super();
    }

    public Furniture(String way, int RECTANGLE_SIZE){
        imgv = new ImageView(new Image(getClass().getResourceAsStream(way)));
        imgv.setFitHeight(RECTANGLE_SIZE-10);
        imgv.setFitWidth(RECTANGLE_SIZE-10);
        getChildren().addAll(imgv);
    }

    public void setImg(String way, int RECTANGLE_SIZE){
        imgv = new ImageView(new Image(getClass().getResourceAsStream(way)));
        imgv.setFitHeight(RECTANGLE_SIZE-10);
        imgv.setFitWidth(RECTANGLE_SIZE-10);
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
    public int getCurrentX() {
        return X;
    }

    @Override
    public int getCurrentY() {
        return Y;
    }
}

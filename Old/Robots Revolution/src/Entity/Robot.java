package Entity;

import javafx.scene.image.ImageView;

public class Robot  extends ImageView implements Entity{
    //public ImageView imgv;
    private int X;
    private int Y;

    public Robot(int x, int y) {
        super();
    }

    public Robot(String way, int RECTANGLE_SIZE){
        super(way);
        //imgv = new ImageView(new Image(getClass().getResourceAsStream(way)));
        setFitHeight(RECTANGLE_SIZE-10);
        setFitWidth(RECTANGLE_SIZE-10);
        //getChildren().addAll(imgv);
    }

   /* public void setImg(String way, int RECTANGLE_SIZE){
        imgv = new ImageView(new Image(getClass().getResourceAsStream(way)));
        imgv.setFitHeight(RECTANGLE_SIZE-10);
        imgv.setFitWidth(RECTANGLE_SIZE-10);
    }
    */

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

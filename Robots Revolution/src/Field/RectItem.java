package Field;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Created by Sersh on 10.11.2016.
 */
public class RectItem extends Pane{
    private int currentCountEntity = 0;
    private Rectangle Cell;
    private Item item;

    public RectItem(Rectangle rect){
        item = new Item(Integer.toString(currentCountEntity),20,20);
        item.setOpacity(0);
        Cell = rect;
        this.getChildren().addAll(Cell,item);
    }
    public Rectangle getCell(){
        return Cell;
    }
    public void setCount(int x){
        currentCountEntity = x;
        item.setText(Integer.toString(currentCountEntity));
        if(currentCountEntity!=0){
            item.setOpacity(1);
        }
        else {
            item.setOpacity(0);
        }
    }
    public int getCount(){
        return currentCountEntity;
    }
}

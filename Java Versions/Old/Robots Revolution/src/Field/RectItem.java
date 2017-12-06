package Field;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Sersh on 10.11.2016.
 */
public class RectItem extends Pane{
    private int currentCountEntity = 0;
    private int currentCountFurniture = 0;
    private Rectangle Cell;
    private Item entityItem;
    private Item furnitureItem;

    public RectItem(Rectangle rect){
        entityItem = new Item(Integer.toString(currentCountEntity),20,20);

        furnitureItem = new Item(Integer.toString(currentCountFurniture),20,20);
        furnitureItem.setTranslateX(48);

        furnitureItem.setOpacity(0);
        entityItem.setOpacity(0);

        Cell = rect;
        this.getChildren().addAll(Cell,entityItem,furnitureItem);
    }
    public Rectangle getCell(){
        return Cell;
    }
    public void setCount(int x){
        currentCountEntity = x;
        entityItem.setText(Integer.toString(currentCountEntity));
        if(currentCountEntity!=0){
            entityItem.setOpacity(1);
        }
        else {
            entityItem.setOpacity(0);
        }
    }

    public void setFurnitureCount(int x){
        currentCountFurniture = x;
        furnitureItem.setText(Integer.toString(currentCountFurniture));
        if(currentCountFurniture!=0){
            furnitureItem.setOpacity(1);
        }
        else {
            furnitureItem.setOpacity(0);
        }
    }

    public int getFurnitureCount(){
        return currentCountFurniture;
    }

    public int getCount(){
        return currentCountEntity;
    }
}

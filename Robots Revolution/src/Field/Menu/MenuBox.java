package Field.Menu;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Sersh on 19.11.2016.
 */
public class MenuBox extends Pane {
    static SubMenu subMenu;
    Rectangle rect;
    public MenuBox(SubMenu curSubMenu){
        subMenu = curSubMenu;
        Rectangle rect = new Rectangle(700,500,Color.BLACK);
        subMenu.setTranslateX(100);
        subMenu.setTranslateY(100);
        getChildren().addAll(rect,subMenu);
    }

    public void setSubMenu(SubMenu curSubMenu){
        getChildren().remove(subMenu);
        subMenu = curSubMenu;
        getChildren().add(subMenu);
    }



}

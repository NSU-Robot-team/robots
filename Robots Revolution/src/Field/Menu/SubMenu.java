package Field.Menu;

import javafx.scene.layout.VBox;

/**
 * Created by Sersh on 19.11.2016.
 */
public class SubMenu extends VBox {
    public SubMenu(MenuItem...menuItems){
        setSpacing(15);
        for(MenuItem Item: menuItems){
            this.getChildren().addAll(Item);
        }
    }
}

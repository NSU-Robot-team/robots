package Field;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;

/**
 * Created by Sersh on 02.11.2016.
 */
public class MenuRuls extends GridPane {
    LinkedList<Button> buttonList = new LinkedList<>();
    Item item = new Item("", 70,30);

    public MenuRuls(){
        buttonList.add(addButton("Up"));
        buttonList.add(addButton("Down"));
        buttonList.add(addButton("Right"));
        buttonList.add(addButton("Left"));

        buttonList.add(addButton("Next"));
        buttonList.add(addButton("Prev"));

        buttonList.add(addButton("miss"));

        this.add(item,0,0);
        this.add(buttonList.get(5),1,0);
        this.add(buttonList.get(4),2,0);
        buttonList.get(6).setOpacity(0);
        this.add(buttonList.get(6),1,1);

        //this.setGridLinesVisible(true);
        this.add(buttonList.get(0),1,2);
        this.add(buttonList.get(1),1,3);
        this.add(buttonList.get(2),2,3);
        this.add(buttonList.get(3),0,3);

        this.setRowIndex(buttonList.get(0),2);
    }

    public LinkedList<Button> getButtonList(){
        return buttonList;
    }

    public void setText(String str){
        //this.getChildren().remove(item);
        item.setText(str);
        //this.add(item,0,0);
    }

    public Button addButton(String title){
        Button btn = new Button(title);
        btn.setPrefSize(70,30);
        return btn;
    }
}

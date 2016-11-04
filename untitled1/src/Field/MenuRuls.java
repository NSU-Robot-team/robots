package Field;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;

/**
 * Created by Sersh on 02.11.2016.
 */
public class MenuRuls extends GridPane {
    LinkedList<Button> buttonList = new LinkedList<>();

    public MenuRuls(){
        buttonList.add(addButton("Up"));
        buttonList.add(addButton("Down"));
        buttonList.add(addButton("Right"));
        buttonList.add(addButton("Left"));

        this.add(buttonList.get(0),1,1);
        this.add(buttonList.get(1),1,2);
        this.add(buttonList.get(2),2,2);
        this.add(buttonList.get(3),0,2);
    }

    public LinkedList<Button> getButtonList(){
        return buttonList;
    }

    public Button addButton(String title){
        Button btn = new Button(title);
        btn.setPrefSize(70,30);
        return btn;
    }
}

package Field;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

        buttonList.add(addButton("miss1"));
        buttonList.add(addButton("miss2"));

        buttonList.add(addButton("Start"));

        Label label1 = new Label("Выберите ро");
        Label label2 = new Label("бота");

        this.add(label1,0,0);
        this.add(label2,1,0);

        this.add(item,0,1);
        this.add(buttonList.get(5),1,1);
        this.add(buttonList.get(4),2,1);

        buttonList.get(6).setOpacity(0);
        buttonList.get(7).setOpacity(0);

        this.add(buttonList.get(6),1,2);
        this.add(buttonList.get(7),1,5);



        //this.setGridLinesVisible(true);
        this.add(buttonList.get(0),1,3);
        this.add(buttonList.get(1),1,4);
        this.add(buttonList.get(2),2,4);
        this.add(buttonList.get(3),0,4);
        this.add(buttonList.get(8),1,6);

        //this.setRowIndex(buttonList.get(0),4);
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

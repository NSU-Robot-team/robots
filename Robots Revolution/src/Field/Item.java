package Field;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by Sersh on 05.11.2016.
 */
public class Item extends StackPane {
    private Rectangle rectangle = new Rectangle(200,20, Color.WHITE);
    private Text text = new Text();

    public Item(String name){
        rectangle.setStroke(Color.BLACK);

        text.setText(name);
        text.setFont(Font.font("Arial", FontWeight.BOLD,14));

        setAlignment(Pos.CENTER);
        getChildren().addAll(rectangle,text);
    }

    public Item(String name, int x,int y){
        Rectangle rectangle = new Rectangle(x,y, Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        text.setText(name);
        text.setFont(Font.font("Arial", FontWeight.BOLD,14));

        setAlignment(Pos.CENTER);
        getChildren().addAll(rectangle,text);
    }

    public void setText(String s){
        text.setText(s);
        System.out.println(text.getText());
    }
}

package Field.Menu;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Created by Sersh on 19.11.2016.
 */
public class MenuItem extends StackPane {
    public MenuItem(String name){
        Rectangle rectangle = new Rectangle(200,20, Color.WHITE);
        Text text = new Text(name);
        rectangle.setOpacity(0.5);
        text.setFont(Font.font("Arial", FontWeight.BOLD,14));
        text.setFill(Color.WHITE);

        setAlignment(Pos.CENTER);
        getChildren().addAll(rectangle,text);

        FillTransition fillTran = new FillTransition(Duration.seconds(0.5),rectangle);

        this.setOnMouseEntered(event -> {
            fillTran.setFromValue(Color.DARKGRAY);
            fillTran.setToValue(Color.DARKGOLDENROD);
            fillTran.setCycleCount(Animation.INDEFINITE);
            fillTran.setAutoReverse(true);
            fillTran.play();
        });

        this.setOnMouseExited(event -> {
            fillTran.stop();
            rectangle.setFill(Color.WHITE);
        });

    }
}

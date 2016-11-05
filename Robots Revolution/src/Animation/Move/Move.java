package Animation.Move;

import Animation.Animation;
import javafx.animation.TranslateTransition;
import Entity.*;

/**
 * Created by Sersh on 05.11.2016.
 */
public class Move implements Animation {
    public static void doCommand(boolean direction,double length, Entity entity){
        TranslateTransition tt = new TranslateTransition(javafx.util.Duration.millis(200), entity);
        if(direction)
            tt.setByY(length);
        else
            tt.setByX(length);
        tt.play();
    }
}

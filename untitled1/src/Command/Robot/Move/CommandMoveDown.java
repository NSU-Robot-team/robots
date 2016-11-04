package Command.Robot.Move;

import Entity.*;
import Field.*;
import javafx.animation.TranslateTransition;

public class CommandMoveDown implements CommandMove {
    @Override
    public void doCommand(Table table, Entity ent) {
        boolean n = ent.getY()<table.getRectList().size()-1? true:false;
        if(n) {
            ent.setY(ent.getY() + 1);
            TranslateTransition tt = new TranslateTransition(javafx.util.Duration.millis(200), ent);
            tt.setByY(table.RECTANGLE_SIZE + table.getRectList().get(0).get(0).getStrokeWidth());
            tt.play();
        }
        else System.out.println("Error, board is end.");
    }
}

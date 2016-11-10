package Command.Robot.Move;

import Animation.Move.Move;
import Entity.*;
import Field.Table;
import javafx.animation.TranslateTransition;

public class CommandMoveLeft implements CommandMove {

    double RECTANGLE_SIZE = 0;
    public boolean direction = false;
    public CommandMoveLeft(double SIZE){
        RECTANGLE_SIZE = SIZE;
    }

    public boolean doCommand(Entity ent) {
        boolean n = ent.getX()>0? true:false;
        if(n) {
            ent.setX(ent.getX() - 1);
            return true;
        }
        else {
            System.out.println("Error, board is end.");
            return false;
        }
    }

    public boolean getDir(){
        return direction;
    }

    @Override
    public void doCommand(Table table, Entity ent) {
        boolean n = ent.getX()>0? true:false;
        if(n) {
            ent.setX(ent.getX() - 1);
            TranslateTransition tt = new TranslateTransition(javafx.util.Duration.millis(200), ent);
            tt.setByX(-(table.RECTANGLE_SIZE + table.getRectList().get(0).get(0).getStrokeWidth()));
            tt.play();
        }
        else System.out.println("Error, board is end.");
    }

    public String getName(){
        return "Left";
    }

}

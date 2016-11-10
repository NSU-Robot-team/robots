package Command.Robot.Move;

import Animation.Move.Move;
import Entity.*;
import Field.*;
import javafx.animation.TranslateTransition;

public class CommandMoveDown implements CommandMove {
    int lengthY = 0;
    double RECTANGLE_SIZE = 0;
    public boolean direction = true;
    public CommandMoveDown(int Y, double SIZE){
        lengthY = Y;
        RECTANGLE_SIZE = SIZE;
    }

    public boolean doCommand(Entity ent) {
        boolean n = ent.getY()<lengthY-1? true:false;
        if(n) {
            ent.setY(ent.getY() + 1);
            //Move.doCommand(true,RECTANGLE_SIZE,ent);
            /*Move a = (new Move());
            a.doCommand1(true,RECTANGLE_SIZE,ent);*/
            return true;
        }
        else {
            System.out.println("Error, board is end.");
            return false;
        }
    }

    public String getName(){
        return "Down";
    }

    public boolean getDir(){
        return direction;
    }

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

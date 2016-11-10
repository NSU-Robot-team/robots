package Command.Robot.Move;

import Animation.Move.Move;
import Entity.Entity;
import Field.Table;
import javafx.animation.TranslateTransition;

public class CommandMoveRight implements CommandMove {
    int lengthX = 0;
    double RECTANGLE_SIZE = 0;
    public boolean direction = false;

    public CommandMoveRight(int X, double SIZE){
        lengthX = X;
        RECTANGLE_SIZE = SIZE;
    }

    public boolean getDir(){
        return direction;
    }

    public boolean doCommand(Entity ent) {
        boolean n = ent.getX()<lengthX-1? true:false;
        if(n) {
            ent.setX(ent.getX() + 1);
            //Move.doCommand(false,RECTANGLE_SIZE,ent);

            return true;
        }
        else {
            System.out.println("Error, board is end.");
            return false;
        }
    }

    @Override
    public void doCommand(Table table, Entity ent) {
        boolean n = ent.getX()<table.getRectList().get(0).size()-1? true:false;
        if(n) {
            ent.setX(ent.getX() + 1);
            TranslateTransition tt = new TranslateTransition(javafx.util.Duration.millis(200), ent);
            tt.setByX(table.RECTANGLE_SIZE + table.getRectList().get(0).get(0).getStrokeWidth());
            tt.play();
        }
        else System.out.println("Error, board is end.");
    }

    public String getName(){
        return "Right";
    }

}

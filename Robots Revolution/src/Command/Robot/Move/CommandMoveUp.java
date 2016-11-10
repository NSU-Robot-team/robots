package Command.Robot.Move;

import Entity.Entity;
import Field.Table;
import javafx.animation.TranslateTransition;

public class CommandMoveUp implements CommandMove {

    double RECTANGLE_SIZE = 0;
    public boolean direction = true;
    public CommandMoveUp(double SIZE){
        RECTANGLE_SIZE = SIZE;
    }

    @Override
    public void doCommand(Table table, Entity ent) {
        boolean n = ent.getY()>0? true:false;
        if(n) {
            ent.setY(ent.getY() - 1);
            TranslateTransition tt = new TranslateTransition(javafx.util.Duration.millis(200), ent);
            tt.setByY(-(table.RECTANGLE_SIZE + table.getRectList().get(0).get(0).getStrokeWidth()));
            tt.play();
        }
        else System.out.println("Error, board is end.");
    }

    public boolean getDir(){
        return direction;
    }

    public boolean doCommand(Entity ent) {
        boolean n = ent.getY()>0? true:false;
        if(n) {
            ent.setY(ent.getY() - 1);
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
        return "Up";
    }
}

package Command.Robot.Move;

import Entity.Entity;
import Field.Table;
import javafx.animation.TranslateTransition;

public class CommandMoveRight implements CommandMove {
    int lengthX = 0;

    public CommandMoveRight(int X){
        lengthX = X;
    }

    public boolean doCommand(Entity ent) {
        boolean n = ent.getX()<lengthX-1? true:false;
        if(n) {
            ent.setX(ent.getX() + 1);
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

}

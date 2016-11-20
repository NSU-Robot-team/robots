package Command.Robot.Move;

import Entity.*;

public class CommandMoveDown implements CommandMove {
    int lengthY = 0;
    double RECTANGLE_SIZE = 0;
    public boolean direction = true;

    public CommandMoveDown(int Y, double SIZE){
        lengthY = Y;
        RECTANGLE_SIZE = SIZE;
    }

    @Override
    public boolean doCommand(Entity ent) {
        boolean n = ent.getCurrentX()<lengthY-1? true:false;
        if(n) {
            ent.setX(ent.getCurrentX() + 1);
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

}

package Command.Robot.Move;

import Entity.*;

public class CommandMoveLeft implements CommandMove {

    double RECTANGLE_SIZE = 0;
    public boolean direction = false;

    public CommandMoveLeft(double SIZE){
        RECTANGLE_SIZE = SIZE;
    }

    @Override
    public boolean doCommand(Entity ent) {
        boolean n = ent.getCurrentY()>0? true:false;
        if(n) {
            ent.setY(ent.getCurrentY() - 1);
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

    public String getName(){
        return "Left";
    }

}

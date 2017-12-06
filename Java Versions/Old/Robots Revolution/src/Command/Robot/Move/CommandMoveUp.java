package Command.Robot.Move;

import Entity.Entity;

public class CommandMoveUp implements CommandMove {

    double RECTANGLE_SIZE = 0;
    public boolean direction = true;

    public CommandMoveUp(double SIZE){
        RECTANGLE_SIZE = SIZE;
    }

    @Override
    public boolean doCommand(Entity ent) {
        boolean n = ent.getCurrentX()>0? true:false;
        if(n) {
            ent.setX(ent.getCurrentX() - 1);
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
        return "Up";
    }
}

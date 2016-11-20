package Command.Robot.Move;

import Entity.Entity;

public class CommandMoveRight implements CommandMove {
    int lengthX = 0;
    double RECTANGLE_SIZE = 0;
    public boolean direction = false;

    public CommandMoveRight(int X, double SIZE){
        lengthX = X;
        RECTANGLE_SIZE = SIZE;
    }

    @Override
    public boolean doCommand(Entity ent) {
        boolean n = ent.getCurrentY()<lengthX-1? true:false;
        if(n) {
            ent.setY(ent.getCurrentY() + 1);
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
        return "Right";
    }

}

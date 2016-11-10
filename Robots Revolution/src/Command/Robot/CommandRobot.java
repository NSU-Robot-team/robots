package Command.Robot;

import Command.Command;
import Entity.*;
import Field.Table;

public interface CommandRobot extends Command{
    void doCommand(Table table, Entity ent);
    String getName();
    boolean doCommand(Entity ent);
    public boolean getDir();
}


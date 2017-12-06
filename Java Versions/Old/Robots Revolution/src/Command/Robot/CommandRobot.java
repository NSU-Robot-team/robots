package Command.Robot;

import Command.Command;
import Entity.*;
import Field.Table;

public interface CommandRobot extends Command{
    String getName();
    boolean doCommand(Entity ent);
    boolean getDir();
}


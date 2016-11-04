package Command.Robot;

import Command.Command;
import Entity.*;
import Field.Table;

public interface CommandRobot extends Command{
    public  void doCommand(Table table, Entity ent);
}


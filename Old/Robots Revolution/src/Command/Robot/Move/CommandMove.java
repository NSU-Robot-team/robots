package Command.Robot.Move;

import Command.Robot.CommandRobot;
import Entity.Entity;
import Field.Table;

public interface CommandMove extends CommandRobot {
    boolean doCommand(Entity ent);
    String getName();
    boolean getDir();
}

package Command.Robot.Move;

import Command.Robot.CommandRobot;
import Entity.Entity;
import Field.Table;

public interface CommandMove extends CommandRobot {
    void doCommand(Table table, Entity ent);
    boolean doCommand(Entity ent);
    String getName();
    public boolean getDir();
}

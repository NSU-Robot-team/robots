package Command;

import Entity.*;
import Field.Table;

public interface Command {
    void doCommand(Table table, Entity ent);
    String getName();
    boolean doCommand(Entity ent);
    public boolean getDir();
}

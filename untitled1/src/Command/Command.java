package Command;

import Entity.*;
import Field.Table;

public interface Command {
    public  abstract void doCommand(Table table, Entity ent);
}

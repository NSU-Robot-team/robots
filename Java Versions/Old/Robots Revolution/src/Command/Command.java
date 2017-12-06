package Command;

import Entity.*;
import Field.Table;

public interface Command {
    String getName();
    boolean doCommand(Entity ent);
    boolean getDir();
}

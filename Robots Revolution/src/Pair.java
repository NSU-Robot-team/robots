import Command.Command;
import Entity.Entity;

import java.util.LinkedList;

public class Pair {
    Command com;
    LinkedList<Entity> ent = new LinkedList<>();

    Pair(Command com, Entity ent) {
        this.com = com;
        this.ent = ent;
    }
}
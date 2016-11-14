import Command.Command;
import Entity.Entity;

import java.util.LinkedList;

public class Pair {
    Entity ent;
    LinkedList<Command> comList = new LinkedList<>();

    Pair(Command com, Entity ent) {
        this.comList.add(com);
        this.ent = ent;
    }

    Pair(Entity ent) {
        this.ent = ent;
    }

    void addCommand(Command com){
        this.comList.add(com);
    }

    void removeCommand(int index){
        this.comList.remove(index);
    }

    void removeCommand(Command com){
        this.comList.remove(com);
    }
}
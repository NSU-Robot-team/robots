import Command.Command;
import Entity.*;

import java.util.LinkedList;

public class Pair {
    Robot ent;
    LinkedList<Command> comList = new LinkedList<>();

    Pair(Command com, Robot ent) {
        this.comList.add(com);
        this.ent = ent;
    }

    Pair(Robot ent) {
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
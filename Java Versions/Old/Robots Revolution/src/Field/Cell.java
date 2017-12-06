package Field;

import Entity.*;

import java.util.LinkedList;

public class Cell {

    private int x;
    private int y;
    private int val;
    private State state;
    LinkedList<Entity> contains;

    public Cell (int x, int y){
        setX(x);
        setY(y);
        val = 0;
        state = State.EMPTY;
        contains = new LinkedList<Entity>();
    }

    public void addNewContains (Entity ent) {
        this.contains.add(ent);
        ++val;
        if(ent.getClass() == Robot.class){
            state = State.ROBOT_IDLE;
        }
    }
    public void removeContains (Entity ent) {
        this.contains.remove(ent);
        --val;
        state = State.EMPTY;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public char getVal() {
        switch (state){
            case EMPTY:
                return '.';
            case ROBOT_IDLE:
                return 'R';
            case ROBOT_WORKING:
                return 'W';
            case FURNITURE:
                return 'F';
            default:
                return '?';
        }
    }
    public void setVal(int val) {this.val = val;}

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}

enum State {
      EMPTY, ROBOT_IDLE, ROBOT_WORKING, FURNITURE
}
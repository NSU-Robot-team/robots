package Field;

import Entity.Entity;

import java.util.LinkedList;

public class Grid {
    public Grid(int x, int y){
        grid = new LinkedList<>();

        for (int i = 0; i < x; i++) {
            grid.add(new LinkedList<>());
            for (int j = 0; j < y; j++) {
                grid.get(i).add(new Cell(i,j));
            }
        }
        this.sizeX = x;
        this.sizeY = y;
    }

    public Cell get(int x, int y) {
        return grid.get(x).get(y);
    }
    public char getVal(int x, int y){
        return grid.get(x).get(y).getVal();
    }

    public void addNewContains (Entity ent) {
        int x = ent.getX();
        int y = ent.getY();
        this.get(x,y).addNewContains(ent);
    }

    public void removeContains (Entity ent) {
        int x = ent.getX();
        int y = ent.getY();
        this.get(x,y).removeContains(ent);
    }

    private LinkedList<LinkedList<Cell>> grid;
    private int sizeX;
    private int sizeY;

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}

import Command.Command;
import Command.Robot.Move.CommandMoveDown;
import Command.Robot.Move.CommandMoveLeft;
import Command.Robot.Move.CommandMoveRight;
import Command.Robot.Move.CommandMoveUp;
import Entity.*;
import Field.*;

import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.Thread.sleep;

public class Core {
    Grid grid;
    LinkedList<Entity> list;
    ArrayList<ArrayList<StateVar>> stateGrid;

    void sleepSec(int sec){
        try {
            sleep(sec * 500);
        } catch(Exception ignored) {}
    }

    public static void main(String[] args) {
        Core core = new Core();
        Grid grid = new Grid(10,10);
        LinkedList<Entity> list = new LinkedList<Entity>();
        Robot rob;
        rob = new Robot(0,0);
        list.add(rob);
        rob = new Robot(0,9);
        list.add(rob);
        rob = new Robot(9,9);
        list.add(rob);
        rob = new Robot(9,0);
        list.add(rob);
        core.start(grid, list);
    }

    public void start(Grid grid, LinkedList<Entity> list) {
        this.grid = grid;
        this.list = list;
        stateGrid = new ArrayList<>();
        for (int i = 0; i < 10; ++i){
            stateGrid.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                StateVar stateVar = new StateVar(State.EMPTY);
                stateGrid.get(i).add(stateVar);
            }
        }

        printGrid();
        sleepSec(1);
        list.forEach(grid::addNewContains);
        updGrid();
        printGrid();
        sleepSec(1);

        Entity rob0 = list.get(0);
        Entity rob1 = list.get(1);
        Entity rob2 = list.get(2);
        Entity rob3 = list.get(3);

        LinkedList<Pair> toDoList = new LinkedList<>();

        /*Command left = new CommandMoveLeft();
        //Command right = new CommandMoveRight();
        //Command down = new CommandMoveDown();
        Command up = new CommandMoveUp();

        toDoList.add(new Pair(up, rob0));
        toDoList.add(new Pair(right, rob1));
        toDoList.add(new Pair(down, rob2));
        toDoList.add(new Pair(left, rob3));
        doTurns(toDoList, 9);
        toDoList.clear();
        toDoList.add(new Pair(right, rob0));
        toDoList.add(new Pair(down, rob1));
        toDoList.add(new Pair(left, rob2));
        toDoList.add(new Pair(up, rob3));
        doTurns(toDoList, 9);
        toDoList.clear();
        toDoList.add(new Pair(down, rob0));
        toDoList.add(new Pair(left, rob1));
        toDoList.add(new Pair(up, rob2));
        toDoList.add(new Pair(right, rob3));
        doTurns(toDoList, 9);
        toDoList.clear();
        toDoList.add(new Pair(left, rob0));
        toDoList.add(new Pair(up, rob1));
        toDoList.add(new Pair(right, rob2));
        toDoList.add(new Pair(down, rob3));
        doTurns(toDoList, 9);
        toDoList.clear();*/
    }


    void doTurn(LinkedList<Pair> toDoList){
        for(Pair elem : toDoList){
            //elem.com.doCommand(elem.ent);
        }
        updGrid();
        printGrid();
        sleepSec(1);
    }

    void doTurns(LinkedList<Pair> toDoList, int count){
        for (int i = 0; i < count; i++) {
            doTurn(toDoList);
        }
    }

    void printGrid(){
        int sizeX = stateGrid.get(0).size();
        int sizeY = stateGrid.size();
        System.out.println();
        for (int i = 0; i < 2 * sizeX + 2; i++) {
            System.out.print('*');
        }
        System.out.println();
        for (int i = sizeY - 1; i >= 0; --i) {
            System.out.print('*');
            for (int j = 0; j < sizeX; j++) {
                System.out.print(stateGrid.get(j).get(i).getValue() + " ");
            }
            System.out.println('*');
        }
        for (int i = 0; i < 2 * sizeX + 2; i++) {
            System.out.print('*');
        }
        System.out.println();
    }

    public void updGrid(){
        for (ArrayList<StateVar> varList : stateGrid)
            for (StateVar var : varList)
                var.setState(State.EMPTY);
        for(Entity ent : list){
            int x = ent.getX();
            int y = ent.getY();
            if(ent.getClass() == Robot.class)
                stateGrid.get(x).get(y).setState(State.ROBOT_IDLE);
        }
    }

    class StateVar{
        State state;
        StateVar(State state){
            this.state = state;
        }

        void setState(State state){
            this.state = state;
        }

        char getValue() {
            return State.getValue(this.state);
        }

    }
    enum State {
        EMPTY, ROBOT_IDLE, ROBOT_WORKING, FURNITURE;

        public static char getValue(State state) {
            switch (state) {
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
    }
}


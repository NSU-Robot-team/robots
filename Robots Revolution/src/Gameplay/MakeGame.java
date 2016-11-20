package Gameplay;

import Command.Command;
import Command.Robot.Move.CommandMoveDown;
import Command.Robot.Move.CommandMoveLeft;
import Command.Robot.Move.CommandMoveRight;
import Command.Robot.Move.CommandMoveUp;
import Field.Item;
import Field.MenuRuls;
import Field.RectItem;
import Field.Table;
import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.LinkedList;

/**
 * Created by Sersh on 20.11.2016.
 */
public class MakeGame {
    int robotCount = 0;
    int stepCount = 0;
    Item item = new Item("");
    double moveLength;
    CommandMoveUp up;
    CommandMoveDown down;
    CommandMoveRight right;
    CommandMoveLeft left;
    BorderPane borderPane;

    Table table;
    MenuRuls menuRuls;
    LinkedList<Pair> pairs;

    public MakeGame(){
        borderPane = new BorderPane();
        pairs = new LinkedList<>();
        table = new Table();
        table.setAlignment(Pos.CENTER);

        moveLength = table.RECTANGLE_SIZE + table.getRectList().get(0).get(0).getCell().getStrokeWidth();

        up = new CommandMoveUp(-moveLength);
        down = new CommandMoveDown(table.getRectList().size(), moveLength);
        right = new CommandMoveRight(table.getRectList().get(0).size(), moveLength);
        left = new CommandMoveLeft(-moveLength);

        for(int i =0; i<table.getRobotList().size();i++){
            pairs.add(new Pair(table.getRobotList().get(i)));
        }
        menuRuls = new MenuRuls();
        menuRuls.setAlignment(Pos.CENTER);
        makeMenuRulesForStrategy(menuRuls, table, pairs);
    }

    public BorderPane getPaneForGame(){
        borderPane.setRight(menuRuls);
        borderPane.setTop(item);
        //table.addRobot("robot.png",1,0);
        //borderPane.setCenter(table);
        return borderPane;

    }

    public BorderPane getPaneForSet(){
        borderPane.getChildren().removeAll(menuRuls,item);
        for(int i=0; i<table.getRectList().size(); i++){
            for(int j=0; j<table.getRectList().get(0).size(); j++){
                int finalJ = j;
                int finalI = i;
                table.getRectList().get(i).get(j).setOnMouseClicked(event -> {
                    table.addRobot("smollRobot.png", finalI, finalJ);
                    pairs.add(new Pair(table.getRobotList().getLast()));
                    System.out.println(finalI+" "+finalJ);
                });
            }
        }
        borderPane.setCenter(table);
        return borderPane;
    }

    public BorderPane getPaneForSetFurn(){
        borderPane.getChildren().removeAll(menuRuls,item);
        for(int i=0; i<table.getRectList().size(); i++){
            for(int j=0; j<table.getRectList().get(0).size(); j++){
                int finalJ = j;
                int finalI = i;
                table.getRectList().get(i).get(j).setOnMouseClicked(event -> {
                    table.addFurniture("stop.png",finalI,finalJ);
                });
            }
        }
        borderPane.setCenter(table);
        return borderPane;

    }



    private void makeMenuRulesForStrategy(MenuRuls menuRuls, Table table, LinkedList<Pair> pairs) {
        menuRuls.setText(String.valueOf(robotCount));
        /* Chose robot (right)*/
        menuRuls.getButtonList().get(4).setOnAction(event -> {
            if (robotCount < table.getRobotList().size() - 1) {
                ++robotCount;
                menuRuls.setText(String.valueOf(robotCount));
                item.setText("");

                if(pairs.get(robotCount).comList.size()!=0)
                    item.setText(pairs.get(robotCount).comList.get(0).getName());
                else return;

                for (int i = 1; i < pairs.size(); i++) {
                    item.setText(item.getText() + "," + pairs.get(robotCount).comList.get(i).getName());
                }

            }   else System.out.println("Robot's end.");
        });
        /* Chose robot (left)*/
        menuRuls.getButtonList().get(5).setOnAction(event -> {
            boolean currentItemEmpty = true;
            if (robotCount > 0) {
                --robotCount;
                menuRuls.setText(String.valueOf(robotCount));
                item.setText("");

                if(pairs.get(robotCount).comList.size()!=0)
                    item.setText(pairs.get(robotCount).comList.get(0).getName());
                else return;

                for (int i = 1; i < pairs.size(); i++) {
                    item.setText(item.getText() + "," + pairs.get(robotCount).comList.get(i).getName());
                }

            } else System.out.println("Robot's end.");
        });

        menuRuls.getButtonList().get(0).setOnAction(event -> {
            //pairs.add(new Pair(up, table.getRobotList().get(robotCount)));
            pairs.get(robotCount).addCommand(up);
            if (!item.getText().equals(""))
                item.setText(item.getText() + "," + up.getName());
            else
                item.setText(up.getName());
        });
        menuRuls.getButtonList().get(1).setOnAction(event -> {
            //pairs.add(new Pair(down, table.getRobotList().get(robotCount)));
            pairs.get(robotCount).addCommand(down);
            if (!item.getText().equals(""))
                item.setText(item.getText() + "," + down.getName());
            else
                item.setText(down.getName());
        });
        menuRuls.getButtonList().get(2).setOnAction(event -> {
            //pairs.add(new Pair(right, table.getRobotList().get(robotCount)));
            pairs.get(robotCount).addCommand(right);
            if (!item.getText().equals(""))
                item.setText(item.getText() + "," + right.getName());
            else
                item.setText(right.getName());
        });
        menuRuls.getButtonList().get(3).setOnAction(event -> {
            //pairs.add(new Pair(left, table.getRobotList().get(robotCount)));
            pairs.get(robotCount).addCommand(left);
            if (!item.getText().equals(""))
                item.setText(item.getText() + "," + left.getName());
            else
                item.setText(left.getName());
        });
        /*Start button*/
        menuRuls.getButtonList().get(8).setOnAction(event -> {

            if(stepCount!=0)
                return;

            SequentialTransition seqT = new SequentialTransition();
            LinkedList<TranslateTransition> trLinkedList = new LinkedList<TranslateTransition>();
            int maxCommand = findMaxCommand(pairs);
            int currentlyX, currentlyY;
            for(int i=0; i<maxCommand;i++){
                for(int j=0; j<table.getRobotList().size();j++) {
                    if(pairs.get(j).comList.size()>i) {
                        currentlyX = pairs.get(j).ent.getCurrentX();
                        currentlyY = pairs.get(j).ent.getCurrentY();
                        if (checkClash(pairs.get(j), table, i) && pairs.get(j).comList.get(i).doCommand(pairs.get(j).ent)) {
                            makeStepAnimation(trLinkedList,table,pairs.get(j),i,currentlyX,currentlyY);
                            seqT.getChildren().addAll(trLinkedList.getLast(), new FillTransition(Duration.millis(30),
                                    table.getRectList().get(pairs.get(j).ent.getCurrentX()).get(pairs.get(j).ent.getCurrentY()).getCell() ,(Color) table.getRectList().get(pairs.get(j).ent.getCurrentX()).get(pairs.get(j).ent.getCurrentY()).getCell().getFill(), Color.RED));
                        }
                        else break;
                    }

                }
            }
            seqT.play();
            item.setText("");
            for(int j=0; j<table.getRobotList().size();j++){
                pairs.get(j).comList.clear();
            }
        });

        /*
        * it's <- step
        * */
        menuRuls.getButtonList().get(9).setOnAction(event -> {
            SequentialTransition seqT = new SequentialTransition();
            LinkedList<TranslateTransition> trLinkedList = new LinkedList<TranslateTransition>();
            int maxCommand = findMaxCommand(pairs);
            int currentlyX, currentlyY;


            if(stepCount>0) {
                --stepCount;
                menuRuls.setStep(stepCount);
            }
            else return;

            if(stepCount>=maxCommand)
                return;

            for(int j=0; j<table.getRobotList().size();j++) {
                if(pairs.get(j).comList.size()>stepCount) {
                    currentlyX = pairs.get(j).ent.getCurrentX();
                    currentlyY = pairs.get(j).ent.getCurrentY();
                    pairs.get(j).comList.set(stepCount,reverseCommand(pairs.get(j).comList.get(stepCount)));
                    if (checkClash(pairs.get(j), table, stepCount) && pairs.get(j).comList.get(stepCount).doCommand(pairs.get(j).ent)) {
                        makeStepAnimation(trLinkedList,table,pairs.get(j),stepCount,currentlyX,currentlyY);
                        seqT.getChildren().addAll(trLinkedList.getLast(), new FillTransition(Duration.millis(30),
                                table.getRectList().get(pairs.get(j).ent.getCurrentX()).get(pairs.get(j).ent.getCurrentY()).getCell() ,(Color) table.getRectList().get(pairs.get(j).ent.getCurrentX()).get(pairs.get(j).ent.getCurrentY()).getCell().getFill(), Color.RED));
                        pairs.get(j).comList.set(stepCount,reverseCommand(pairs.get(j).comList.get(stepCount)));
                    }
                    else break;
                }
            }
            seqT.play();
        });

         /*
        * it's -> step
        * */
        menuRuls.getButtonList().get(10).setOnAction(event -> {
            SequentialTransition seqT = new SequentialTransition();
            LinkedList<TranslateTransition> trLinkedList = new LinkedList<TranslateTransition>();
            int maxCommand = findMaxCommand(pairs);
            int currentlyX, currentlyY;
            if(stepCount>=maxCommand)
                return;



            for(int j=0; j<table.getRobotList().size();j++) {
                if(pairs.get(j).comList.size()>stepCount) {
                    currentlyX = pairs.get(j).ent.getCurrentX();
                    currentlyY = pairs.get(j).ent.getCurrentY();
                    if (checkClash(pairs.get(j), table, stepCount) && pairs.get(j).comList.get(stepCount).doCommand(pairs.get(j).ent)) {
                        makeStepAnimation(trLinkedList,table,pairs.get(j),stepCount,currentlyX,currentlyY);
                        seqT.getChildren().addAll(trLinkedList.getLast(), new FillTransition(Duration.millis(30),
                                table.getRectList().get(pairs.get(j).ent.getCurrentX()).get(pairs.get(j).ent.getCurrentY()).getCell() ,(Color) table.getRectList().get(pairs.get(j).ent.getCurrentX()).get(pairs.get(j).ent.getCurrentY()).getCell().getFill(), Color.RED));
                    }
                    else break;
                }
            }
            seqT.play();
            ++stepCount;
            menuRuls.setStep(stepCount);
        });
    }

    boolean checkClash(Pair pairs, Table table, int i) {
        System.out.println("Check "+pairs.ent.getCurrentX()+" "+pairs.ent.getCurrentY());
        if (pairs.comList.get(i) == up) {

            if(pairs.ent.getCurrentX()==0 || table.getRectList().get(pairs.ent.getCurrentX()-1).get(pairs.ent.getCurrentY()).getCount()!=0 ||
                    table.getRectList().get(pairs.ent.getCurrentX()-1).get(pairs.ent.getCurrentY()).getFurnitureCount()!=0){
                System.out.println("It's clash");
                return false;
            }
        }
        if(pairs.comList.get(i) == down){
            if(pairs.ent.getCurrentX()==(table.getRectList().size()-1) || table.getRectList().get(pairs.ent.getCurrentX()+1).get(pairs.ent.getCurrentY()).getCount()!=0 ||
                    table.getRectList().get(pairs.ent.getCurrentX()+1).get(pairs.ent.getCurrentY()).getFurnitureCount()!=0){
                System.out.println("It's clash");
                return false;
            }
        }
        if(pairs.comList.get(i) == left){
            if(pairs.ent.getCurrentY()==0 || table.getRectList().get(pairs.ent.getCurrentX()).get(pairs.ent.getCurrentY()-1).getCount()!=0 ||
                    table.getRectList().get(pairs.ent.getCurrentX()).get(pairs.ent.getCurrentY()-1).getFurnitureCount()!=0){
                System.out.println("It's clash");
                return false;
            }
        }
        if(pairs.comList.get(i) == right){
            if(pairs.ent.getCurrentY()==(table.getRectList().get(0).size()-1) || table.getRectList().get(pairs.ent.getCurrentX()).get(pairs.ent.getCurrentY()+1).getCount()!=0 ||
                    table.getRectList().get(pairs.ent.getCurrentX()).get(pairs.ent.getCurrentY()+1).getFurnitureCount()!=0){
                System.out.println("It's clash");
                return false;
            }
        }
        return true;
    }

    Command reverseCommand(Command com){
        if(com==up)
            return down;
        if(com==down)
            return up;
        if(com==left)
            return right;
        if(com==right)
            return left;

        return null;
    }

    int findMaxCommand(LinkedList<Pair> pair){
        int result = 0;
        for(int i=0; i<pair.size();i++){
            if( pair.get(i).comList.size()>result){
                result = pair.get(i).comList.size();
            }
        }
        return result;
    }

    public void makeStepAnimation(LinkedList<TranslateTransition> trLinkedList, Table table, Pair pairs, int i, int currentlyX, int currentlyY){

        trLinkedList.add(new TranslateTransition(javafx.util.Duration.millis(500),
                (Node) pairs.ent));

        table.getRectList().get(pairs.ent.getCurrentX()).get(pairs.ent.getCurrentY()).setCount(
                table.getRectList().get(pairs.ent.getCurrentX()).get(pairs.ent.getCurrentY()).getCount()+1);

        /*horizontally or vertically*/
        if (pairs.comList.get(i).getDir()) {
            if (pairs.comList.get(i) == up) {
                trLinkedList.getLast().setByY(-moveLength);
                table.getRectList().get(currentlyX).get(currentlyY).setCount(
                        table.getRectList().get(currentlyX).get(currentlyY).getCount()-1);

            }
            else {
                trLinkedList.getLast().setByY(moveLength);
                table.getRectList().get(currentlyX).get(currentlyY).setCount(
                        table.getRectList().get(currentlyX).get(currentlyY).getCount()-1);
            }
        } else {
            if (pairs.comList.get(i) == left) {
                trLinkedList.getLast().setByX(-moveLength);
                table.getRectList().get(currentlyX).get(currentlyY).setCount(
                        table.getRectList().get(currentlyX).get(currentlyY).getCount()-1);
            }
            else {
                trLinkedList.getLast().setByX(moveLength);
                table.getRectList().get(currentlyX).get(currentlyY).setCount(
                        table.getRectList().get(currentlyX).get(currentlyY).getCount()-1);
            }
        }

    }

}

import Command.Robot.Move.CommandMoveDown;
import Command.Robot.Move.CommandMoveLeft;
import Command.Robot.Move.CommandMoveRight;
import Command.Robot.Move.CommandMoveUp;
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import Field.*;
import java.util.LinkedList;

/**
 * Created by Sersh on 02.11.2016.
 */
public class Main extends Application {
    int robotCount = 0;
    Item item = new Item("This is Item");
    double moveLength;
    CommandMoveUp up;
    CommandMoveDown down;
    CommandMoveRight right;
    CommandMoveLeft left;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Application");
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);

        LinkedList<Pair> pairs = new LinkedList<>();

        BorderPane borderPane = new BorderPane();
        Table table = new Table();
        table.setAlignment(Pos.CENTER);

        moveLength = table.RECTANGLE_SIZE + table.getRectList().get(0).get(0).getStrokeWidth();

        up = new CommandMoveUp(-moveLength);
        down = new CommandMoveDown(table.getRectList().size(),
                moveLength);
        right = new CommandMoveRight(table.getRectList().get(0).size(),
                moveLength);
        left = new CommandMoveLeft(-moveLength);

        MenuRuls menuRuls = new MenuRuls();
        menuRuls.setAlignment(Pos.CENTER);
        makeMenuRulesForStrategy(menuRuls,table,pairs);


        borderPane.setCenter(table);
        borderPane.setRight(menuRuls);

        borderPane.setTop(item);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

    private void makeMenuRulesForStrategy(MenuRuls menuRuls, Table table, LinkedList<Pair> pairs){
        menuRuls.setText(String.valueOf(robotCount));
        /* Chose robot (right)*/
        menuRuls.getButtonList().get(4).setOnAction(event -> {
            boolean currentItemEmpty = true;
            if(robotCount<table.getRobotList().size()-1) {
                ++robotCount;
                menuRuls.setText(String.valueOf(robotCount));
                item.setText("");
                for(int i =0;i<pairs.size();i++){
                    if(pairs.get(i).ent==table.getRobotList().get(robotCount)){
                        if(!currentItemEmpty)
                            item.setText(item.getText()+","+pairs.get(i).com.getName());
                        else {
                            item.setText(pairs.get(i).com.getName());
                            currentItemEmpty = false;
                        }
                    }
                }
                currentItemEmpty = true;
            }else System.out.println("Robot's end.");
        });
        /* Chose robot (left)*/
        menuRuls.getButtonList().get(5).setOnAction(event -> {
            boolean currentItemEmpty = true;
            if(robotCount>0) {
                --robotCount;
                menuRuls.setText(String.valueOf(robotCount));
                item.setText("");
                for(int i =0;i<pairs.size();i++){
                    if(pairs.get(i).ent==table.getRobotList().get(robotCount)){
                        if(!currentItemEmpty)
                            item.setText(item.getText()+","+pairs.get(i).com.getName());
                        else {
                            item.setText(item.getText()+pairs.get(i).com.getName());
                            currentItemEmpty = false;
                        }
                    }
                }
                currentItemEmpty = true;
            }else System.out.println("Robot's end.");
        });

        menuRuls.getButtonList().get(0).setOnAction(event -> {
            pairs.add(new Pair(up,table.getRobotList().get(robotCount)));
            if(!item.getText().equals(""))
                item.setText(item.getText()+","+up.getName());
            else
                item.setText(up.getName());
        });
        menuRuls.getButtonList().get(1).setOnAction(event -> {
            pairs.add(new Pair(down,table.getRobotList().get(robotCount)));
            if(!item.getText().equals(""))
                item.setText(item.getText()+","+down.getName());
            else
                item.setText(down.getName());
        });
        menuRuls.getButtonList().get(2).setOnAction(event -> {
            pairs.add(new Pair(right,table.getRobotList().get(robotCount)));
            if(!item.getText().equals(""))
                item.setText(item.getText()+","+right.getName());
            else
                item.setText(right.getName());
        });
        menuRuls.getButtonList().get(3).setOnAction(event -> {
            pairs.add(new Pair(left,table.getRobotList().get(robotCount)));
            if(!item.getText().equals(""))
                item.setText(item.getText()+","+left.getName());
            else
                item.setText(left.getName());
        });
        /*Start*/
        menuRuls.getButtonList().get(8).setOnAction(event -> {
            SequentialTransition seqT = new SequentialTransition();
            LinkedList<TranslateTransition> trLinkedList = new LinkedList<TranslateTransition>();
            TranslateTransition tt;
            for(int i=0;i<pairs.size();i++) {
                if (pairs.get(i).com.doCommand(pairs.get(i).ent)) {
                    trLinkedList.add(new TranslateTransition(javafx.util.Duration.millis(500),
                            pairs.get(i).ent));
                    /*Up or Down*/
                    if(pairs.get(i).com.getDir()){

                        if(pairs.get(i).com ==up)
                            trLinkedList.getLast().setByY(-moveLength);
                        else
                            trLinkedList.getLast().setByY(moveLength);

                    }
                    else{

                        if(pairs.get(i).com ==left)
                            trLinkedList.getLast().setByX(-moveLength);
                        else
                            trLinkedList.getLast().setByX(moveLength);

                    }
                    seqT.getChildren().addAll(trLinkedList.getLast());
                }else {
                    break;
                }
            }
            seqT.play();
            item.setText("");
            pairs.clear();
        });
    }
}



import Animation.Move.Move;
import Command.Robot.Move.CommandMoveDown;
import Command.Robot.Move.CommandMoveLeft;
import Command.Robot.Move.CommandMoveRight;
import Command.Robot.Move.CommandMoveUp;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import Field.*;
import Entity.*;
import Command.*;

/**
 * Created by Sersh on 02.11.2016.
 */
public class Main extends Application {
    CommandMoveUp up;
    CommandMoveDown down;
    CommandMoveRight right;
    CommandMoveLeft left;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Application");
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);

        BorderPane borderPane = new BorderPane();
        Table table = new Table();
        table.setAlignment(Pos.CENTER);

        up = new CommandMoveUp();
        down = new CommandMoveDown(table.getRectList().size());
        right = new CommandMoveRight(table.getRectList().get(0).size());
        left = new CommandMoveLeft();

        MenuRuls menuRuls = new MenuRuls();
        menuRuls.setAlignment(Pos.CENTER);
        makeMenuRuls(menuRuls,table);

        borderPane.setCenter(table);
        borderPane.setRight(menuRuls);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

    void makeMenuRuls(MenuRuls menuRuls, Table table){
        menuRuls.getButtonList().get(0).setOnAction(event -> {
            if(up.doCommand(table.getRobotList().get(0))){
                Move.doCommand(true,-(table.RECTANGLE_SIZE + table.getRectList().get(0).get(0).getStrokeWidth()),
                        table.getRobotList().get(0));
            }
        });
        menuRuls.getButtonList().get(1).setOnAction(event -> {
            if(down.doCommand(table.getRobotList().get(0))){
                Move.doCommand(true,(table.RECTANGLE_SIZE + table.getRectList().get(0).get(0).getStrokeWidth()),
                        table.getRobotList().get(0));
            }
        });
        menuRuls.getButtonList().get(2).setOnAction(event -> {
            if(right.doCommand(table.getRobotList().get(0))) {
                Move.doCommand(false,table.RECTANGLE_SIZE + table.getRectList().get(0).get(0).getStrokeWidth(),
                        table.getRobotList().get(0));
            }
        });
        menuRuls.getButtonList().get(3).setOnAction(event -> {
            if(left.doCommand(table.getRobotList().get(0))) {
                Move.doCommand(false,-(table.RECTANGLE_SIZE + table.getRectList().get(0).get(0).getStrokeWidth()),
                        table.getRobotList().get(0));
            }
        });
    }
}

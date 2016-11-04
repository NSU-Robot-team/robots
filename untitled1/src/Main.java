

import Command.Robot.Move.CommandMoveDown;
import Command.Robot.Move.CommandMoveLeft;
import Command.Robot.Move.CommandMoveRight;
import Command.Robot.Move.CommandMoveUp;
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
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Application");
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);

        CommandMoveUp up = new CommandMoveUp();
        CommandMoveDown down = new CommandMoveDown();
        CommandMoveRight right = new CommandMoveRight();
        CommandMoveLeft left = new CommandMoveLeft();

        BorderPane borderPane = new BorderPane();
        Table table = new Table();
        table.setAlignment(Pos.CENTER);

        MenuRuls menuRuls = new MenuRuls();
        menuRuls.setAlignment(Pos.CENTER);

        menuRuls.getButtonList().get(0).setOnAction(event -> {
            up.doCommand(table,table.getRobotList().get(0));
        });
        menuRuls.getButtonList().get(1).setOnAction(event -> {
            down.doCommand(table,table.getRobotList().get(0));
        });
        menuRuls.getButtonList().get(2).setOnAction(event -> {
            right.doCommand(table,table.getRobotList().get(0));
        });
        menuRuls.getButtonList().get(3).setOnAction(event -> {
            left.doCommand(table,table.getRobotList().get(0));
        });

        borderPane.setCenter(table);

        borderPane.setRight(menuRuls);
        Scene scene = new Scene(borderPane);


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}

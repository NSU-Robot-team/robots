import Command.Command;
import Command.Robot.Move.CommandMoveDown;
import Command.Robot.Move.CommandMoveLeft;
import Command.Robot.Move.CommandMoveRight;
import Command.Robot.Move.CommandMoveUp;
import Entity.Entity;
import Field.Menu.MenuBox;
import Field.Menu.MenuItem;
import Gameplay.*;
import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import Field.*;
import javafx.util.Duration;

import java.util.LinkedList;

/**
 * Created by Sersh on 02.11.2016.
 */
public class Main extends Application {
    int robotCount = 0;
    int stepCount = 0;
    Item item = new Item("This is Item");
    double moveLength;
    CommandMoveUp up;
    CommandMoveDown down;
    CommandMoveRight right;
    CommandMoveLeft left;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Robolution");
        primaryStage.setWidth(716);
        primaryStage.setHeight(530);

        primaryStage.setMaxWidth(716);
        primaryStage.setMaxHeight(530);

        StackPane root = new StackPane();
        Scene scene = new Scene(root);

        MakeGame game = new MakeGame();
        //MakeMenu.DoIt(game);

        root.getChildren().addAll(game.getPaneForSet());
        root.getChildren().addAll(MakeMenu.DoIt(scene,game, root));
        System.out.println("2 = "+2+2);
        //MakeMenu.DoIt(root);


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

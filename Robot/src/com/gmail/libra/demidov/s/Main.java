package com.gmail.libra.demidov.s;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLs/sample.fxml"));
        root.setId("background");
        primaryStage.setTitle("Robot");
        Scene scene = new Scene(root, 750, 600);
        scene.getStylesheets().add(getClass().getResource("/com/gmail/libra/demidov/s/OtherFile/segmented.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

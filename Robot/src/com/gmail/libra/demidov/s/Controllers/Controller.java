package com.gmail.libra.demidov.s.Controllers;

import com.gmail.libra.demidov.s.Model.ObjectModel;
import com.gmail.libra.demidov.s.Object.Heirs.Fork;
import com.gmail.libra.demidov.s.Object.Heirs.Philosopher;
import com.gmail.libra.demidov.s.Object.Heirs.Table;
import com.gmail.libra.demidov.s.Object.MyObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextArea TextField;
    @FXML
    TextArea Config;
    @FXML
    BorderPane Board;

    private ObjectModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        model = new ObjectModel(new ArrayList<>());

        TextField.setText("AddTable\n" +
                "AddFork\n" +
                "AddFork\n" +
                "AddFork\n" +
                "AddFork\n" +
                "AddPhilosopher\n" +
                "5\n" +
                "GrabRightFork\n" +
                "GrabLeftFork\n" +
                "Eat\n" +
                "PutRightFork\n" +
                "PutLeftFork");

        Board.setOnMouseClicked(event -> {
            System.out.println(event.getX()+" "+event.getSceneY());
        });
    }

    public void Create(){

        ArrayList<String> lines = new ArrayList<>();
        for (String line : TextField.getText().split("\\n")) lines.add(line);

        for(int i=0; i<lines.size();i++){
            if(LineHandler(lines.get(i))){
                i+=1;
                int k = Integer.valueOf(lines.get(i));

                int t = model.getObjectList().size()-1;
                for(int j = 0;j<k;j++){
                    i+=1;
                    LinHandler(lines.get(i),t);
                }
            }
        }
    }

    public void HungerGame() throws InterruptedException {
        ArrayList<Philosopher> list = model.getPhilosophers();

        Config.setText("Start new iteration\n");
        for(Philosopher philosopher: list){
            if(philosopher.getSteps().size()!=philosopher.getPlanStep())
                philosopher.getSteps().get(philosopher.getPlanStep()).makeStep();
        }
        Config.setText(Config.getText()+"\nEnd iteration\n\n");

    }

    private void LinHandler(String line, int t){
        if(line.contains("GrabRightFork")){
            ((Philosopher)model.getObjectList().get(t)).getSteps().add(new Step() {
                @Override
                public void makeStep() {
                    Config.setText(Config.getText()+"\n"+((Philosopher)model.getObjectList().get(t)).GrabRightFork(model.getForks())+"\n");
                }});
        }
        if(line.contains("GrabLeftFork")){
            ((Philosopher)model.getObjectList().get(t)).getSteps().add(new Step() {
                @Override
                public void makeStep() {
                    Config.setText(Config.getText()+"\n"+((Philosopher)model.getObjectList().get(t)).GrabLeftFork(model.getForks())+"\n");
                }});
        }
        if(line.contains("PutLeftFork")){
            ((Philosopher)model.getObjectList().get(t)).getSteps().add(new Step() {
                @Override
                public void makeStep() {
                    Config.setText(Config.getText()+"\n"+((Philosopher)model.getObjectList().get(t)).PutLeftFork(model.getForks())+"\n");
                }});
        }
        if(line.contains("PutRightFork")){
            ((Philosopher)model.getObjectList().get(t)).getSteps().add(new Step() {
                @Override
                public void makeStep() {
                    Config.setText(Config.getText()+"\n"+((Philosopher)model.getObjectList().get(t)).PutRightFork(model.getForks())+"\n");
                }});
        }
        if(line.contains("Eat")){
            ((Philosopher)model.getObjectList().get(t)).getSteps().add(new Step() {
                @Override
                public void makeStep() {
                    Config.setText(Config.getText()+"\n"+((Philosopher)model.getObjectList().get(t)).Eat(model.getForks())+"\n");
                }});
        }
    }

    private boolean LineHandler(String line){
        if(line.contains("AddTable")){

            model.getObjectList().add(new Table());
            model.getObjectList().get(model.getObjectList().size()-1).getImgV().setTranslateY(120);
            model.getObjectList().get(model.getObjectList().size()-1).getImgV().setTranslateX(-30);
            Board.setCenter(model.getObjectList().get(model.getObjectList().size()-1).getImgV());
        }
        if(line.contains("RemoveTable")){
            MyObject tmp = model.getElement("Table");
            if(tmp == null) return false;
            else {
                Board.getChildren().remove(tmp.getImgV());
                model.getObjectList().remove(tmp);
            }
        }
        if(line.contains("AddFork")){
            model.getObjectList().add(new Fork(model.getPosX(),model.getPosY(),model.getCount("Fork")));
            Board.getChildren().add(model.getObjectList().get(model.getObjectList().size()-1).getImgV());
        }
        if(line.contains("RemoveFork")){
            MyObject tmp = model.getElement("Fork");
            if(tmp == null) return false;
            else {
                Board.getChildren().remove(tmp.getImgV());
                model.getObjectList().remove(tmp);
            }
        }
        if(line.contains("AddPhilosopher")){
            Philosopher pr = new Philosopher(model.getPosX(),model.getPosY(),model.getCount("Philosopher"));
            model.getObjectList().add(pr);
            Board.getChildren().add(model.getObjectList().get(model.getObjectList().size()-1).getImgV());

            return true;
        }
        if(line.contains("RemovePhilosopher")){
            MyObject tmp = model.getElement("Philosopher");
            if(tmp == null) return false;
            else {
                Board.getChildren().remove(tmp.getImgV());
                model.getObjectList().remove(tmp);
            }
        }
        return false;
    }


}

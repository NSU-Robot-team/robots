package com.gmail.libra.demidov.s.Object.Heirs;

import com.gmail.libra.demidov.s.Controllers.Step;
import com.gmail.libra.demidov.s.Object.MyObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by Sersh on 29.04.2017.
 */
public class Philosopher extends MyObject {
    String name = "Philosopher";
    private ImageView imgV = new ImageView(new Image(getClass().getResource(
            "/com/gmail/libra/demidov/s/OtherFile/Philosopher.png").toExternalForm()));

    int planStep = 0;
    int PhilosopherNum;
    int timeForFood = 1;

    int forkCount = 0;

    private ArrayList<Step> steps = new ArrayList<>();

    public Philosopher(int X,int Y, int n) {
        PhilosopherNum = n;
        imgV.setFitHeight(78);
        imgV.setFitWidth(60);
        X-=20;
        if(n == 0){
            imgV.setTranslateX(X - 120);
            imgV.setTranslateY(Y + 110);
        }
        if(n == 1){
            imgV.setTranslateX(X - 120);
            imgV.setTranslateY(Y - 110);
        }
        if(n == 2){
            imgV.setTranslateX(X + 120);
            imgV.setTranslateY(Y - 110);
        }
        if(n == 3){
            imgV.setTranslateX(X + 120);
            imgV.setTranslateY(Y + 110);
        }
    }

    public int getPlanStep() {
        return planStep;
    }

    public void setPlanStep(int planStep) {
        this.planStep = planStep;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getTimeForFood() {
        return timeForFood;
    }

    public void setTimeForFood(int timeForFood) {
        this.timeForFood = timeForFood;
    }

    @Override
    public ImageView getImgV() {
        return imgV;
    }

    @Override
    public void setImgV(ImageView imgV) {
        this.imgV = imgV;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public String GrabLeftFork(ArrayList<Fork> forks){
        try {
            forks.get((PhilosopherNum+1)%4).setBusy(true,PhilosopherNum);
        } catch (Exception e) {
            return "Philosopher "+ PhilosopherNum+ " is loser. He have "+forkCount+" forks";
        }
        planStep+=1;
        forkCount+=1;
        return "Philosopher "+ PhilosopherNum+ " have "+forkCount+" forks";
    }

    public String GrabRightFork(ArrayList<Fork> forks){
        try {
            forks.get((PhilosopherNum)%4).setBusy(true,PhilosopherNum);
        } catch (Exception e) {
            return "Philosopher "+ PhilosopherNum + " is loser. He have "+forkCount+" forks";
        }
        planStep+=1;
        forkCount+=1;
        return "Philosopher "+ PhilosopherNum + " have "+forkCount+" forks";
    }

    public String PutLeftFork(ArrayList<Fork> forks){
        try {
            forks.get((PhilosopherNum+1)%4).setBusy(false,PhilosopherNum);
        } catch (Exception e) {
            return "Philosopher "+ PhilosopherNum+ " is loser. He have "+forkCount+" forks";
        }
        planStep+=1;
        forkCount-=1;
        return "Philosopher "+ PhilosopherNum + " put fork and have "+forkCount+" forks";
    }

    public String PutRightFork(ArrayList<Fork> forks){
        try {
            forks.get((PhilosopherNum)%4).setBusy(false,PhilosopherNum);
        } catch (Exception e) {
            return "Philosopher "+ PhilosopherNum + " is loser. He have "+forkCount+" forks";
        }
        planStep+=1;
        forkCount-=1;
        return "Philosopher "+ PhilosopherNum + " put fork and have "+forkCount+" forks";
    }

    public String Eat(ArrayList<Fork> forks){
        try {
           // forks.get(PhilosopherNum%4).setBusy(false,PhilosopherNum);
            //forks.get((PhilosopherNum+1)%4).setBusy(false,PhilosopherNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        planStep+=1;
        return "Now Philosopher "+ PhilosopherNum+ " is fed.";
    }
}

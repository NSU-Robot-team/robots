package com.gmail.libra.demidov.s.Object.Heirs;

import com.gmail.libra.demidov.s.Object.MyObject;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Created by Sersh on 29.04.2017.
 */
public class Fork extends MyObject {
    private String name = "Fork";
    private ImageView imgV = new ImageView(new Image(getClass().getResource(
            "/com/gmail/libra/demidov/s/OtherFile/fork.png").toExternalForm()));

    int owner = -1;

    private Boolean busy = false;

    public Fork(int X,int Y, int n) {
        imgV.setFitHeight(60);
        imgV.setFitWidth(20);
        if(n == 0){
            imgV.setTranslateX(X);
            imgV.setTranslateY(Y + 120);
        }
        if(n == 1){
            imgV.setTranslateX(X - 120);
            imgV.setTranslateY(Y);
        }
        if(n == 2){
            imgV.setTranslateX(X);
            imgV.setTranslateY(Y - 120);
        }
        if(n == 3){
            imgV.setTranslateX(X + 120);
            imgV.setTranslateY(Y);
        }

    }

    public Boolean getBusy() {
        return busy;
    }

    public void setBusy(Boolean busy,  int PhilosopherNum) throws Exception {
        if(PhilosopherNum != owner && owner!=-1 && this.busy){
            throw new Exception();
        }
        if(busy)
            imgV.setRotate(imgV.getRotate() + 90);
        else
            imgV.setRotate(imgV.getRotate() - 90);
        this.owner = PhilosopherNum;
        this.busy = busy;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ImageView getImgV() {
        return imgV;
    }

    @Override
    public void setImgV(ImageView imgV) {
        this.imgV = imgV;
    }
}

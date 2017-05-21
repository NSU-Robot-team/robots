package com.gmail.libra.demidov.s.Object.Heirs;

import com.gmail.libra.demidov.s.Object.MyObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Sersh on 29.04.2017.
 */
public class Table extends MyObject{
    String name = "Table";
    private ImageView imgV = new ImageView(new Image(getClass().getResource(
            "/com/gmail/libra/demidov/s/OtherFile/kair1.png").toExternalForm()));

    public Table() {
       // imgV.setTranslateX(300);
       // imgV.setTranslateY(100);

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

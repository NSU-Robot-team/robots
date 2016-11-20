package Field.Menu;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Created by Sersh on 19.11.2016.
 */
public class MenuBox extends Pane {
    SubMenu subMenu;
    ImageView imgv;
    ImageView imgvG;
    public MenuBox(SubMenu curSubMenu){
        subMenu = curSubMenu;
        imgv = new ImageView(new Image(getClass().getResourceAsStream("scene.png")));
        imgvG = new ImageView(new Image(getClass().getResourceAsStream("sceneG.png")));

        subMenu.setTranslateX(100);
        subMenu.setTranslateY(100);
        getChildren().addAll(imgvG,imgv,subMenu);
        
        FadeTransition ft = new FadeTransition(Duration.seconds(1), imgv);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(Animation.INDEFINITE);
        ft.setAutoReverse(true);

        ft.play();
    }

    public void setSubMenu(SubMenu curSubMenu){
        getChildren().remove(subMenu);
        subMenu = curSubMenu;
        subMenu.setTranslateX(100);
        subMenu.setTranslateY(100);
        getChildren().add(subMenu);
    }
}

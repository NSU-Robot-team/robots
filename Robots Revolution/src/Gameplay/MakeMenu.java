package Gameplay;

import Field.Menu.MenuBox;
import Field.Menu.MenuItem;
import Field.Menu.SubMenu;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * Created by Sersh on 20.11.2016.
 */
public class MakeMenu {
    public MakeMenu(){}
    public static MenuBox DoIt(Scene scene, MakeGame game, StackPane root){

        MenuItem startItem = new MenuItem("Старт");
        MenuItem exitItem = new MenuItem("Выход");

        SubMenu subMenu = new SubMenu(startItem,exitItem);
        MenuBox menuBox = new MenuBox(subMenu);

        startItem.setOnMouseClicked(event -> {
            game.getPaneForGame();
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5),menuBox);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(event1 -> {
                menuBox.setVisible(false);
            });
            ft.play();
        });

        exitItem.setOnMouseClicked(event -> {
            System.exit(0);
        });


        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ESCAPE){
                FadeTransition ft = new FadeTransition(Duration.seconds(0.5),menuBox);
                if(!menuBox.isVisible()){
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();
                    menuBox.setVisible(true);

                }else {
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(event1 -> {
                        menuBox.setVisible(false);
                    });
                    ft.play();
                }
            }
        });
        return menuBox;
    }
}

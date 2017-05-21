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
        MenuItem settingItem = new MenuItem("Расстановка");
        MenuItem exitItem = new MenuItem("Выход");

        MenuItem setRobItem = new MenuItem("Расставить роботов");
        MenuItem setFurItem = new MenuItem("Расставить препятсвия");
        MenuItem backItem = new MenuItem("Назад");

        SubMenu startSubMenu = new SubMenu(startItem,settingItem,exitItem);
        SubMenu settingMenu = new SubMenu(setRobItem,setFurItem,backItem);
        MenuBox menuBox = new MenuBox(startSubMenu);

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

        settingItem.setOnMouseClicked(event -> {
            menuBox.setSubMenu(settingMenu);
        });

        exitItem.setOnMouseClicked(event -> {
            System.exit(0);
        });

        setRobItem.setOnMouseClicked(event -> {
            game.getPaneForSet();
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5),menuBox);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(event1 -> {
                menuBox.setVisible(false);
            });
            ft.play();
        });

        setFurItem.setOnMouseClicked(event -> {
            game.getPaneForSetFurn();
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5),menuBox);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setOnFinished(event1 -> {
                menuBox.setVisible(false);
            });
            ft.play();
        });

        backItem.setOnMouseClicked(event -> {
            menuBox.setSubMenu(startSubMenu);
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

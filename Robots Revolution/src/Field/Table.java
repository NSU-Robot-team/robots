package Field;

import Entity.Furniture;
import javafx.animation.TranslateTransition;
import Entity.Robot;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Sersh on 02.11.2016.
 */
public class Table extends GridPane {
    LinkedList<LinkedList<RectItem>> rectList =  new LinkedList<>();
    LinkedList<Robot> robotList =  new LinkedList<>();
    LinkedList<Furniture> furnitureList =  new LinkedList<>();
    public static final int RECTANGLE_SIZE = 70;
    Pane pane = new Pane();

    public Table(){
        makeBoard(rectList);
        //addRobot("robot.png",0,0);
        //addRobot("robot.png",1,2);
        //addFurniture("stop.png",0,3);
        //this.getChildren().addAll(robotList);
        //this.getChildren().addAll(furnitureList);
        //this.getChildren().add(pane);
    }

    public void addRobot(String way, int x, int y){
        Robot rob = new Robot(way,RECTANGLE_SIZE);
        rob.setTranslateX((RECTANGLE_SIZE+2)*y+7);
        rob.setTranslateY((RECTANGLE_SIZE+2)*x);
        rob.setX(x);
        rob.setY(y);
        rectList.get(x).get(y).setCount(rectList.get(x).get(y).getCount()+1);
        robotList.add(rob);
        this.getChildren().addAll(robotList.getLast());
    }

    public void addFurniture(String way, int x, int y){
        Furniture fur = new Furniture(way,RECTANGLE_SIZE);
        fur.imgv.setTranslateX((RECTANGLE_SIZE+2)*y+5);
        fur.imgv.setTranslateY((RECTANGLE_SIZE+2)*x+5);
        fur.setX(x);
        fur.setY(y);
        rectList.get(x).get(y).setFurnitureCount(rectList.get(x).get(y).getFurnitureCount()+1);
        furnitureList.add(fur);
        this.getChildren().addAll(furnitureList.getLast());
    }

    public LinkedList<LinkedList<RectItem>> getRectList(){
        return  rectList;
    }

    public LinkedList<Robot> getRobotList(){
        return  robotList;
    }

    public LinkedList<Furniture> getFurnitureList(){
        return  furnitureList;
    }

    private Rectangle addRectangle(RectItem old){
        Random rand = new Random();
        Rectangle rect = new Rectangle(RECTANGLE_SIZE,RECTANGLE_SIZE,
                new Color(rand.nextFloat(),rand.nextFloat()/ 2f,rand.nextFloat()/ 2f,rand.nextFloat()));
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(2);
        rect.setOnMouseClicked(event -> {
            rect.setFill(Color.LIGHTSEAGREEN);
        });

        return rect;
    }

    private Rectangle addRectangle(){
        Rectangle rect = new Rectangle(RECTANGLE_SIZE,RECTANGLE_SIZE, Color.RED);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(2);

        rect.setOnMouseClicked(event -> {
            rect.setFill(Color.LIGHTSEAGREEN);
        });

        return rect;
    }

    private void makeBoard(LinkedList<LinkedList<RectItem>> rectList){
        for(int i=0;i<3;i++) {
            rectList.add(new LinkedList<>());
            rectList.get(i).add(new RectItem(addRectangle()));
            for (int j = 0; j < 4; j++) {
                rectList.get(i).add(new RectItem(addRectangle(rectList.get(i).get(j))));
                this.add(rectList.get(i).get(j),j,i);
            }

            this.add(rectList.get(i).getLast(),rectList.getLast().size(),i);
        }
    }
}

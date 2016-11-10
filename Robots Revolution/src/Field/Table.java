package Field;

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
    public static final int RECTANGLE_SIZE = 70;

    public Table(){
        makeBoard(rectList);
        addRobot("zayats-robot.png",0,0);
        addRobot("zayats-robot.png",1,2);
        this.getChildren().addAll(robotList);
    }

    public void addRobot(String way, int x, int y){
        Robot rob = new Robot(way,RECTANGLE_SIZE);
        rob.imgv.setTranslateX((RECTANGLE_SIZE+2)*y+15);
        rob.imgv.setTranslateY((RECTANGLE_SIZE+2)*x+15);
        rob.setX(x);
        rob.setY(y);
        rectList.get(x).get(y).setCount(rectList.get(x).get(y).getCount()+1);
        robotList.add(rob);
    }

    public LinkedList<LinkedList<RectItem>> getRectList(){
        return  rectList;
    }

    public LinkedList<Robot> getRobotList(){
        return  robotList;
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

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
    LinkedList<LinkedList<Rectangle>> rectList =  new LinkedList<>();
    LinkedList<Robot> robotList =  new LinkedList<>();
    public static final int RECTANGLE_SIZE = 70;

    public Table(){
        makeBoard(rectList);
        addRobot("zayats-robot.png",0,0);
        this.getChildren().addAll(robotList);
    }

    public void addRobot(String way, int x, int y){
        Robot rob = new Robot(way);
        rob.imgv.setTranslateX((RECTANGLE_SIZE+2)*x+5);
        rob.imgv.setTranslateY((RECTANGLE_SIZE+2)*y+5);
        rob.setX(x);
        rob.setY(y);
        robotList.add(rob);
    }

    public LinkedList<LinkedList<Rectangle>> getRectList(){
        return  rectList;
    }

    public LinkedList<Robot> getRobotList(){
        return  robotList;
    }

    public void moveRight(Robot rb) {
        boolean n = rb.getX()<rectList.get(0).size()-1? true:false;
        if(n) {
            rb.setX(rb.getX() + 1);
            TranslateTransition tt = new TranslateTransition(javafx.util.Duration.millis(200), rb);
            tt.setByX(RECTANGLE_SIZE + rectList.get(0).get(0).getStrokeWidth());
            tt.play();
        }
        else System.out.println("Error, board is end.");
    }

    public void moveLeft(Robot rb) {
        boolean n = rb.getX()>0? true:false;
        if(n) {
            rb.setX(rb.getX() - 1);
            TranslateTransition tt = new TranslateTransition(javafx.util.Duration.millis(200), rb);
            tt.setByX(-(RECTANGLE_SIZE + rectList.get(0).get(0).getStrokeWidth()));
            tt.play();
        }
        else System.out.println("Error, board is end.");
    }

    public void moveUp(Robot rb) {
        boolean n = rb.getY()>0? true:false;
        if(n) {
            rb.setY(rb.getY() - 1);
            TranslateTransition tt = new TranslateTransition(javafx.util.Duration.millis(200), rb);
            tt.setByY(-(RECTANGLE_SIZE + rectList.get(0).get(0).getStrokeWidth()));
            tt.play();
        }
        else System.out.println("Error, board is end.");
    }

    public void moveDown(Robot rb) {
        boolean n = rb.getY()<rectList.size()-1? true:false;
        if(n) {
            rb.setY(rb.getY() + 1);
            TranslateTransition tt = new TranslateTransition(javafx.util.Duration.millis(200), rb);
            tt.setByY(RECTANGLE_SIZE + rectList.get(0).get(0).getStrokeWidth());
            tt.play();
        }
        else System.out.println("Error, board is end.");
    }

    private Rectangle addRectangle(Rectangle old){
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

    private void makeBoard(LinkedList<LinkedList<Rectangle>> rectList){
        for(int i=0;i<3;i++) {
            rectList.add(new LinkedList<>());
            rectList.get(i).add(addRectangle());
            for (int j = 0; j < 4; j++) {
                rectList.get(i).add(addRectangle(rectList.get(i).get(j)));
                this.add(rectList.get(i).get(j),j,i);
            }

            this.add(rectList.get(i).getLast(),rectList.getLast().size(),i);
        }
    }
}

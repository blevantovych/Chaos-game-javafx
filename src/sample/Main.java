package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;

public class Main extends Application {
    final float rectSize = 1.0f;

    public int[] getMidPoint(int x1, int y1, int x2, int y2) {
        int midX = Math.abs(x1 - x2) / 2 + Math.min(x1, x2);
        int midY = Math.abs(y1 - y2) / 2 + Math.min(y1, y2);
        int[] midPoint = {midX, midY};
        return midPoint;
    }

    public void addRect(int [] point, List<Rectangle> rectangles) {
        Rectangle rectangle = new Rectangle();

        rectangle.setX(point[0]);
        rectangle.setY(point[1]);
        rectangle.setWidth(rectSize);
        rectangle.setHeight(rectSize);
        rectangles.add(rectangle);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        int width = 600;
        int height = 600;

        List<Rectangle> rectangles = new ArrayList<>();
        int[] pointA = {width / 2, 0}; // go to this point when randomNum is 0
        int[] pointB = {width, height}; // go to this point when randomNum is 1
        int[] pointC = {0, height}; // go to this point when randomNum is 2

        addRect(pointA, rectangles);
        addRect(pointB, rectangles);
        addRect(pointC, rectangles);

        int x = ThreadLocalRandom.current().nextInt(0,  width);
        int y = ThreadLocalRandom.current().nextInt(0,  height);

        Group root = new Group();

        for (int i = 0; i < 100000; i++) {
            Rectangle rectangle = new Rectangle();

            int randomNum = ThreadLocalRandom.current().nextInt(0,  3);
//            System.out.println(randomNum);
            int[] midPoint = new int[2];
            if (randomNum == 0) {
                midPoint = getMidPoint(x, y, pointA[0], pointA[1]);
            } else if (randomNum == 1) {
                midPoint = getMidPoint(x, y, pointB[0], pointB[1]);
            } else if (randomNum == 2) {
                midPoint = getMidPoint(x, y, pointC[0], pointC[1]);
            }

            x = midPoint[0];
            y = midPoint[1];


            rectangle.setX(x);
            rectangle.setY(y);
            rectangle.setWidth(rectSize);
            rectangle.setHeight(rectSize);
            rectangles.add(rectangle);

        }
        root.getChildren().addAll(rectangles);

        //Creating a scene object
        Scene scene = new Scene(root, width, height);

        //Setting title to the Stage
        primaryStage.setTitle("Chaos Game");

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package chromeDino;

import javafx.geometry.Rectangle2D;
import javafx.scene.robot.Robot;
import javafx.stage.Screen;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.input.*;
import java.lang.Thread;

public class chromeDino extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws InterruptedException{
        Thread.sleep(2000);
        Robot robot = new Robot();

        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        int x = (int)(0.5*bounds.getWidth());
        int y = (int)(0.5*bounds.getHeight()+15);

        robot.mouseMove((int)bounds.getWidth()/2,(int)bounds.getHeight()/2);
        robot.mouseMove(x,y);

        robot.keyPress(KeyCode.SPACE);
        robot.keyRelease(KeyCode.SPACE);
        //~650 ms from the middle 
        //Gray threshold is about 242
        while(true){//play game 
            if(robot.getPixelColor(x, y).getRed()*255<100){
                Thread.sleep(400);
                robot.keyPress(KeyCode.SPACE);
                robot.keyRelease(KeyCode.SPACE);
            }
            // System.out.println(robot.getPixelColor(x, y).getRed()*255);
        }

    }
}

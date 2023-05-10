package _8ball;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.*;
import java.nio.file.*;

public class magic8ball extends Application{
    private static String[]answers;
    private Label ans = new Label();
    public static void main(String[] args) {
        String contents = "";
        try{
            contents = Files.readString(Paths.get("Javafix/_8ball/ball.txt"));
        }catch(IOException e){
            System.out.println(e);
            System.exit(0);
        }
        answers = contents.split("\n"); //Works
        
        launch(args);
    }

    public String newAnswer(String[]answers){
        return(answers[(int)Math.floor(Math.random()*(20))]);
    }

    @Override
    public void start(Stage primaryStage){
        Label greeting = new Label();
        greeting.setText("Press the 8Ball");
        
        ImageView img = new ImageView("Javafix/_8ball/8ball.jpg");
        img.setFitHeight(80);
        img.setPreserveRatio(true);
        //Create Button
        Button btn = new Button();
        btn.setPrefSize(80,80);
        btn.setGraphic(img);
        btn.setOnAction(e -> ans.setText(newAnswer(answers)));

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(greeting,btn,ans);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);
        
        Scene scene = new Scene(vBox, 400, 400);

        primaryStage.setTitle("8Ball");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

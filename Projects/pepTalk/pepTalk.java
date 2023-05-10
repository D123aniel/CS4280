package pepTalk;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets; 


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class pepTalk extends Application {
    private static String []arr1 = {"Champ,","Fact:","Everyone says","Dang...","Check it:","Just saying...","Superstar,","Tiger,","Self,","Know this:","News alert:","Girl,","Ace,","Rockstar,","Sister,","Listen up:"};
    private static String []arr2 = {"the mere idea of you","your soul","your hair today","everything you do","your personal style","every thought you have","that sparkle in your eye","your presence here","what you got oing on","the essential you","your life's journey","that saucy personality","your DNA","that brain of yours","the way you roll","your shining heart"};
    private static String []arr3 = {"has serious game.","rains magic.","deserves a Nobel Prize.","raises the roof.","makes miracles.","is paying off big time.","shows mad skills.","just shimmers.","is a national treasure.","gets the part hopping.","is the next big thing.","roars like a lion.","is made of diamonds.","makes everything alright.","is like, so totally awesome.","keeps our dreams alive."};
    private static Label response = new Label();
    public static void main(String[] args) {
        launch(args);
    }

    public String genResponse(String one, String two, String three){
        return one + " " + two +" "+three;
    }

    public void start(Stage primaryStage){
        Label greeting = new Label("Pep Talk Generator");

        //Dropdown box 1
        ComboBox<String> comboBox1 = new ComboBox<String>();
        for(int i=0;i<arr1.length;i++){
            comboBox1.getItems().add(arr1[i]);
        }
        comboBox1.setEditable(true); // allow user to enter custom response
        comboBox1.setOnAction(e -> System.out.println(comboBox1.getValue()));
        comboBox1.setPromptText("Option 1");
        //Dropdown box 2
        ComboBox<String> comboBox2 = new ComboBox<String>();
        for(int i=0;i<arr1.length;i++){
            comboBox2.getItems().add(arr2[i]);
        }
        comboBox2.setEditable(true); // allow user to enter custom response
        comboBox2.setOnAction(e -> System.out.println(comboBox2.getValue()));
        //Dropdown box 3
        ComboBox<String> comboBox3 = new ComboBox<String>();
        for(int i=0;i<arr1.length;i++){
            comboBox3.getItems().add(arr3[i]);
        }
        comboBox3.setEditable(true); // allow user to enter custom response
        comboBox3.setOnAction(e -> System.out.println(comboBox3.getValue()));

        //Text labels for dropdown boxes
        Label op1 = new Label();
        Label op2 = new Label();
        Label op3 = new Label();
        op1.setText("Option 1");
        op2.setText("Option 2");
        op3.setText("Option 3");

        //GridPane
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(300,100);
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        gridPane.setAlignment(Pos.CENTER); 

        gridPane.add(op1, 0,0);
        gridPane.add(comboBox1,0,1);
        gridPane.add(op2,1,0);
        gridPane.add(comboBox2,1,1);
        gridPane.add(op3,2,0);
        gridPane.add(comboBox3,2,1);

        String musicFile = "Javafix/pepTalk/hypeboy.mp3";
        File file = new File(musicFile);
        Media hype = new Media(file.toURI().toString());
        MediaPlayer play = new MediaPlayer(hype);


        Button btn = new Button();
        btn.setText("Generate Response");
        btn.setOnAction(e -> response.setText(comboBox1.getValue()+" "+comboBox2.getValue()+" "+comboBox3.getValue()));
        btn.setOnAction(e -> play.play());

        //Layout
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(greeting,gridPane, btn, response);

        //Scene
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setTitle("Pep Talk Generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

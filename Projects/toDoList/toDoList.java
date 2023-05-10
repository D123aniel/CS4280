package toDoList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.Pos; 
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import java.time.LocalDate;

public class toDoList extends Application{

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage){
        Label greeting = new Label("To Do List");
        greeting.setFont(new Font("Lucida Calligraphy",20));

        Label askDate = new Label("Select your date"); 
        Label askText = new Label("Enter Task: ");

        TextField task = new TextField();

        DatePicker date = new DatePicker();
        date.setOnAction(e->{
            LocalDate d = date.getValue();
        });

        VBox tasksList = new VBox(15);
        tasksList.setAlignment(Pos.BASELINE_LEFT);
        tasksList.setPadding(new Insets(0,0,0,15));

        Button addButton = new Button("Add Task");
        addButton.setOnAction(e->{
            HBox item = new HBox(15); //a single task

            Button delete = new Button("Delete task"); //Delete Button
            delete.setOnAction((event)->{
                tasksList.getChildren().remove(item);
            });
            Label txt = new Label(task.getText());
            Label d = new Label(date.getValue().toString());
            item.getChildren().addAll(txt,d,delete);
            tasksList.getChildren().add(item);
        });

        GridPane pane = new GridPane();
        pane.setVgap(5); 
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);
        pane.add(askText,0,0);
        pane.add(task,1,0);
        pane.add(askDate,0,1);
        pane.add(date,1,1);

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(layout,400,500);
        Color color = Color.rgb(160,198,255);
        Background background = new Background(new BackgroundFill(color,null,null));
        layout.setBackground(background);

        layout.getChildren().addAll(greeting,pane,addButton,tasksList);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    
}

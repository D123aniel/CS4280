package colorMixer;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets; 
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class colorMixer extends Application{
    Background background; 
    Label rgbStr = new Label("RGB: 255.0, 255.0, 255.0");
    Label hsbStr = new Label("HSB: 0.0, 0.0, 100.0");
    Label hexStr = new Label("Hex code: ffffff");
    Label one = new Label("Red");
    Label two = new Label("Green");
    Label three = new Label("Blue");
    Color color = Color.rgb(255,255,255,0);;

    public static void main(String[] args) {
        launch(args);
    }

    public double calcLum(Color color){ //0-255
        return color.getRed()*0.2126*255 + color.getGreen()*.7152*255 + color.getBlue()*0.0722*255;
    }

    public String rgbToHex(Color color){
        int red = (int)(color.getRed()*255);
        int green = (int)(color.getGreen()*255);
        int blue = (int)(color.getBlue()*255);
        return Integer.toHexString(red)+Integer.toHexString(green)+Integer.toHexString(blue);
    }

    public void changeTxt(Color color){
        if(calcLum(color)>127){
            one.setTextFill(Color.BLACK);
            two.setTextFill(Color.BLACK);
            three.setTextFill(Color.BLACK);
            rgbStr.setTextFill(Color.BLACK);
            hsbStr.setTextFill(Color.BLACK);
            hexStr.setTextFill(Color.BLACK);
        }else{
            one.setTextFill(Color.WHITE);
            two.setTextFill(Color.WHITE);
            three.setTextFill(Color.WHITE);
            rgbStr.setTextFill(Color.WHITE);
            hsbStr.setTextFill(Color.WHITE);
            hexStr.setTextFill(Color.WHITE);
        }
        rgbStr.setText("RGB: "+color.getRed()*255 +", "+ color.getGreen()*255 +", "+ color.getBlue()*255);
        hsbStr.setText("HSB: "+color.getHue()+", "+color.getSaturation()*100+", "+color.getBrightness()*100);
        hexStr.setText("Hex code: "+rgbToHex(color));
    }

    public void start(Stage primaryStage){
        Label greeting = new Label("Please Select Mixer Mode");

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        
        background = new Background(new BackgroundFill(color,null,null));

        ComboBox<String> mode = new ComboBox<String>();
        mode.setPromptText("Select Mode");
        mode.getItems().add("RGB");
        mode.getItems().add("HSB");

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(300,100);
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        gridPane.setAlignment(Pos.CENTER); 

        Slider slideOne = new Slider(0,255.0,255); //Red
        Slider slideTwo = new Slider(0,255.0,255); //Blue
        Slider slideThree = new Slider(0,255.0,255); //Green

        gridPane.add(one,0,0);
        gridPane.add(two,0,1);
        gridPane.add(three,0,2);
        gridPane.add(slideOne,1,0);
        gridPane.add(slideTwo,1,1);
        gridPane.add(slideThree,1,2);

        GridPane pane2 = new GridPane();
        pane2.setMinSize(100,100);
        pane2.setPadding(new Insets(10, 10, 10, 10)); 
        pane2.setVgap(5); 
        pane2.setHgap(5);       
        pane2.setAlignment(Pos.CENTER); 

        pane2.add(rgbStr,0,0);
        pane2.add(hsbStr,0,1);
        pane2.add(hexStr,0,2);

        mode.setOnAction(e-> {
            if(mode.getValue().equals("RGB")){
                one.setText("Red");two.setText("Green");three.setText("Blue");
            }
            else if(mode.getValue().equals("HSB")){
                one.setText("Hue");two.setText("Saturation");three.setText("Brightness");
            }
        });
        //Red and Hue
        slideOne.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number>observable, Number oldValue, Number newValue){
                if(mode.getValue().equals("HSB")){
                    color = Color.hsb(slideOne.getValue()/255*360,color.getSaturation(),color.getBrightness());
                }
                else if(mode.getValue().equals("RGB")){
                    color = Color.rgb((int)slideOne.getValue(), (int)slideTwo.getValue(),(int)slideThree.getValue());
                }
                background = new Background(new BackgroundFill(color, null, null));
                layout.setBackground(background);
                changeTxt(color);
            }
        });
        //Green and Saturation
        slideTwo.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number>observable, Number oldValue, Number newValue){
                if(mode.getValue().equals("HSB")){
                    color = Color.hsb(color.getHue(),slideTwo.getValue()/255,color.getBrightness());
                }
                else if(mode.getValue().equals("RGB")){
                    color = Color.rgb((int)slideOne.getValue(), (int)slideTwo.getValue(),(int)slideThree.getValue());
                }
                background = new Background(new BackgroundFill(color, null, null));
                layout.setBackground(background);
                changeTxt(color);
            }
        });
        //Blue and Brightness
        slideThree.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number>observable, Number oldValue, Number newValue){
                if(mode.getValue().equals("HSB")){
                    color = Color.hsb(color.getHue(),color.getSaturation(),slideThree.getValue()/255);
                }
                else if(mode.getValue().equals("RGB")){
                    color = Color.rgb((int)slideOne.getValue(), (int)slideTwo.getValue(),(int)slideThree.getValue());
                }
                background = new Background(new BackgroundFill(color, null, null));
                layout.setBackground(background);
                changeTxt(color);
            }
        });

        layout.getChildren().addAll(greeting,mode,gridPane,pane2);
        background = new Background(new BackgroundFill(color,null,null));
        layout.setBackground(background);
        
        Scene scene = new Scene(layout,600,600);
        primaryStage.setTitle("Color Mixer");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

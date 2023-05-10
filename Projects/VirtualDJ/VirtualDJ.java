import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.io.File;

public class VirtualDJ extends Application {
    
    private Button playButton;
    private Slider volumeSlider;
    private Slider tempoSlider;
    private MediaPlayer mediaPlayer;
    private Circle light;

    public static void main(String[] args) {
        launch(args);
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        playButton = new Button("Play");
        volumeSlider = new Slider();
        tempoSlider = new Slider();

        light = new Circle(50, Color.YELLOW);
        light.setEffect(new InnerShadow());
        
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(light.radiusProperty(), 50)),
            new KeyFrame(Duration.seconds(0.5), new KeyValue(light.radiusProperty(), 60)),
            new KeyFrame(Duration.seconds(1.0), new KeyValue(light.radiusProperty(), 50))
        );
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        File file = new File("VirtualDJ/zeroNewJeans.mp3");
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        volumeSlider = new Slider();
        tempoSlider = new Slider();
        
        mediaPlayer.setOnReady(() -> {
            mediaPlayer.play();
        });
        
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            mediaPlayer.setVolume(newValue.doubleValue());
        });
        System.out.println(mediaPlayer.getVolume());
        
        tempoSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            mediaPlayer.setRate(newValue.doubleValue() / 100.0);
        });
        
        VBox root = new VBox(10);
        root.getChildren().addAll(playButton, volumeSlider, tempoSlider);
        
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}

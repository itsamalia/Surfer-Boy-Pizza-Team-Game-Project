package application;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Main class which kicks off the entire application by loading in Title Scene
 * of the game. *
 * 
 * CS3443-004 - Fall 2022
 *
 * @author Danny Ghrist (kda458)
 * 
 *
 */
public class Main extends Application {

    MediaPlayer mediaPlayer;

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        try {

            // Load custom Stranger Things font.
            Font.loadFont(getClass().getResource("fonts/BenguiatBold.ttf").toExternalForm(), 10.0);

            // Give access to the other controllers to this primaryStage.
            stage = primaryStage;
            primaryStage.setTitle("Surfer Boy Pizza!!!");

            // Add icon to the title bar of the Stage.
            Image icon = new Image("application/images/icon.png");
            primaryStage.getIcons().add(icon);

            // Connect to the FXML (contains our layout) and load it in
            Parent root = FXMLLoader.load(Main.class.getResource("view/MainView.fxml"));

            // Put the layout onto the scene
            Scene scene = new Scene(root);

            // Set the scene on the stage
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Amalia's edits for Stranger Things music/mp3 to play
     */
    public void music() {
        String s = "src/application/audio/StrangerThingsThemeSong.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        // Media h = new
        // Media(getClass().getResource("application/audio/StrangerThingsThemeSong.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(h);
//    	mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
//    	mediaPlayer.setVolume(0.1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

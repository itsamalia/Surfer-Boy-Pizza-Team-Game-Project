package application.controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * Controller for the MainView FXML Scene which will be the title screen.
 * 
 * CS3443-004 - Fall 2022
 *
 * @author Danny Ghrist (kda458)
 * @author Caleb Pierce
 * @author Sarah Halverson
 * @author Amalia Talijancic
 * @author Carlos Martinez
 * 
 */
public class MainController implements EventHandler<ActionEvent>, Initializable {

    @FXML
    AnchorPane titlePane;

    @FXML
    ImageView logoImg, pizzaTruck, pixelArgyle;

    @FXML
    Button buttonPushed, pizzaStartButton, optionsButton, exitButton;

    MediaPlayer mediaPlayer, mediaPlayerSFX, mediaBackground;

    @FXML
    MediaView backgroundMedia;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane.setBottomAnchor(pizzaTruck, 50.0);
        AnchorPane.setBottomAnchor(pixelArgyle, 25.0);
        AnchorPane.setBottomAnchor(backgroundMedia, 0.0);
        // TODO: DELETE THIS TEST CODE BEFORE FINALIZING
//        AnchorPane.setTopAnchor(logoImg, 100.0);
//        AnchorPane.setLeftAnchor(logoImg, 800 - logoImg.getFitWidth());
//        AnchorPane.setRightAnchor(logoImg, 800 - logoImg.getFitWidth());  
        music();

        // Create a new Media object to play the background video.
        String mediaURL = "src/application/videos/mainMenuBackground.mp4";
        Media media1 = new Media(Paths.get(mediaURL).toUri().toString());
        mediaBackground = new MediaPlayer(media1);
        mediaBackground.setAutoPlay(true);
        backgroundMedia.setMediaPlayer(mediaBackground);

    }

    /**
     * Determines which button was pressed (if we end up having multiple buttons),
     * and loads the view for that corresponding button.
     * 
     * @param event Listens for button push event (ActionEvent)
     */
    @Override
    public void handle(ActionEvent event) {
        try {

            // Determine which button was pressed.
            Button buttonPushed = (Button) event.getSource();

            String newScene = "";

            // Determines which button was pushed and loads that FXML Scene.
            if (buttonPushed.getId().equals("pizzaStartButton")) {
                newScene = "Mission.fxml";
                playSound("buttonclick");
            } else if (buttonPushed.getId().equals("optionsButton")) {
                // TODO: Update this to a new options view once implemented.
                playSound("buttonclick");
                newScene = null;
                System.out.println("OPTIONS TO BE IMPLEMENTED SOON...");
            } else if (buttonPushed.getId().equals("exitButton")) {
                playSound("buttonclick");
                newScene = null;
            } else if (buttonPushed.getId().equals(null)) {
                System.out.println("IT'S ALL WRONG, WHAT HAVE YOU DONE!!!");
            }

            // Exit the program if the scene button clicked on is null.
            if (newScene == null) {
             //   Platform.exit();
            	Stage stage = (Stage) exitButton.getScene().getWindow();
                stage.close();
            } else {
                // Connect to the FXML (contains our layout) and load it in.
                Parent root = FXMLLoader.load(Main.class.getResource("view/" + newScene));

                // Put the layout onto the scene.
                Scene scene = new Scene(root);

                // Set the scene on the stage that was created in Main.java.
                Main.stage.setScene(scene);
                Main.stage.show();

                mediaPlayer.stop();
            }

        } catch (

        Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Event listener to play a sound effect for when a user hovers over a button.
     */
    public void buttonEntered() {
        playSound("buttonhover");
    }

    /**
     * Amalia's edits for Stranger Things music/mp3 to play. Creates a new Media
     * object to play the background music.
     */
    public void music() {
        String s = "src/application/audio/StrangerThingsThemeSong.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        // Media(getClass().getResource("application/audio/StrangerThingsThemeSong.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
        // mediaPlayer.setStartTime(Duration.seconds(0));
        // mediaPlayer.setAutoPlay(true);
    }

    /**
     * Creates a new Media object to play sound effects.
     * 
     * @param soundName The name of the sound effect audio (String)
     */
    public void playSound(String soundName) {
        String s = "src/application/audio/" + soundName + ".mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayerSFX = new MediaPlayer(h);
        mediaPlayerSFX.play();
        // mediaPlayer.setStartTime(Duration.seconds(0));
        // mediaPlayer.setAutoPlay(true);
    }
}
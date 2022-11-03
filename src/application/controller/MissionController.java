package application.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import application.Main;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class MissionController implements EventHandler<ActionEvent>, Initializable {

    @FXML
    private MediaView media;
    @FXML
    private MediaPlayer mediaBackground, mediaSFX;

    @FXML
    private Button buttonPushed, gameStartButton;

    @FXML
    private Label contextLabel, missionLabel;

    /**
     * Everything to be initialized upon the initial loading of the Scene (i.e,
     * animations, music, timers etc...)
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String mediaURL = "src/application/videos/missionBackground.mp4";
        Media media1 = new Media(Paths.get(mediaURL).toUri().toString());
        mediaBackground = new MediaPlayer(media1);
        mediaBackground.setAutoPlay(true);
        mediaBackground.setCycleCount(-1);
        media.setMediaPlayer(mediaBackground);

        animateText(missionLabel, "YOUR MISSION: Deliver (X) amount of pizzas in (X) amount of time.");

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

            // Determines which button was pushed and loads that FXML Scene.
            Button buttonPushed = (Button) event.getSource();

            String newScene = "";

            if (buttonPushed.getId().equals("gameStartButton")) {
                //newScene = "GameView.fxml";
            	newScene = "LoadingView.fxml";
                playSound("mindfighton");
                mediaBackground.stop();
                try {
                    setCursor("normalSelect");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else if (buttonPushed.getId().equals(null)) {
                System.out.println("IT'S ALL WRONG, WHAT HAVE YOU DONE!!!");
            }

            // Connect to the FXML (contains our layout) and load it in.
            Parent root = FXMLLoader.load(Main.class.getResource("view/" + newScene));

            // Put the layout onto the scene.
            Scene scene = new Scene(root);

            // Set the scene on the stage that was created in Main.java.
            Main.stage.setScene(scene);
            Main.stage.show();
            try {
                setCursor("normalSelect");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Event Listener to handle the background video for the scene.
     * 
     * @param event (ActionEvent)
     */
    public void handleVideo(ActionEvent event) {
        try {
            String media = "src/application/videos/missionBackground.mp4";
            Media h = new Media(Paths.get(media).toUri().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(h);
            mediaPlayer.setAutoPlay(true);

            // Media media1 = new Media ("src/application/videos/ArgyleMission.mp4");
            // MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
            // mediaPlayer1.setAutoPlay(true);
            // MediaView mediaView = new MediaView(mediaPlayer1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Event listener to play a sound effect for when a user hovers over a button.
     */
    public void handleButtonEntered() {
        playSound("buttonhover");
        try {
            setCursor("normalClick");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Event Listener for when a button is exited.
     */
    public void handleButtonExit() {
        try {
            setCursor("normalSelect");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the custom cursor.
     * 
     * @param imageName The name of the image file of the cursor. (String)
     * @throws FileNotFoundException (Exception)
     */
    public void setCursor(String imageName) throws FileNotFoundException {
        Image myImage = new Image(new FileInputStream("src/application/images/" + imageName + ".png"));
        ImageCursor cursor = new ImageCursor(myImage, 0, 0);
        Scene scene = Main.stage.getScene();
        scene.getRoot().setCursor(cursor);
    }

    /**
     * Animates the contents of a Label's text and animates it.
     * 
     * @param lbl          The Label to set the text in (Label)
     * @param stringToType The String to type to be animated(String)
     */
    public void animateText(Label lbl, String stringToType) {
        String content = stringToType;
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(6000));
            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                lbl.setText(content.substring(0, n));
            }
        };
        animation.play();
    }

    /**
     * Plays the background music for the Scene.
     */
    public void playMusic(String musicName) {
        String s = "src/application/audio/" + musicName + ".mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        // Media(getClass().getResource("application/audio/StrangerThingsThemeSong.mp3").toExternalForm());
        // media = new MediaPlayer(h);
        // media.play();
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
        // Media(getClass().getResource("application/audio/StrangerThingsThemeSong.mp3").toExternalForm());
        mediaSFX = new MediaPlayer(h);
        mediaSFX.play();
        // mediaPlayer.setStartTime(Duration.seconds(0));
        // mediaPlayer.setAutoPlay(true);
    }
}

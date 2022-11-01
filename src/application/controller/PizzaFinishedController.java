package application.controller;

import java.nio.file.Paths;

import application.Main;
import javafx.application.Platform;
//import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

/**
 * Allows user to either go back home or go back to the GameView.fxml and make
 * another pizza.
 * 
 * @author Amalia Talijancic
 * @author Danny Ghrist (kda458)
 *
 */

public class PizzaFinishedController implements EventHandler<ActionEvent> {

    @FXML
    Button homeButton, morePizzaButton, exitButton;

    @FXML
    Text hoorayPizzaFinishedText;

    @FXML
    ImageView winArgyle;

    @FXML
    MediaPlayer mediaSFX;

    /**
     * @author - Amalia's edits Here's a handle for the Home Button meant for the
     *         Game View
     * @author Danny Ghrist (kda458): I deleted the extra method as I have a
     *         combined method that can handle moving wherever the user needs to go
     *         determined by which button they press. I incorporated your home
     *         button here for brevity.
     */
    @Override
    public void handle(ActionEvent event) {
        try {

            // Determine which button was pressed.
            Button buttonPushed = (Button) event.getSource();

            String newScene = "";

            // Determines which button was pushed and loads that FXML Scene.
            if (buttonPushed.getId().equals("homeButton")) {
                newScene = "MainView.fxml";
                playSound("buttonclick");
            } else if (buttonPushed.getId().equals("morePizzaButton")) {
                newScene = "GameView.fxml";
                playSound("buttonclick");
            } else if (buttonPushed.getId().equals("exitButton")) {
                playSound("buttonclick");
                newScene = null;
            } else if (buttonPushed.getId().equals(null)) {
                System.out.println("IT'S ALL WRONG, WHAT HAVE YOU DONE!!!");
            }

            if (newScene == null) {
                Platform.exit();
            } else {
                // Connect to the FXML (contains our layout) and load it in.
                Parent root = FXMLLoader.load(Main.class.getResource("view/" + newScene));

                // Put the layout onto the scene.
                Scene scene = new Scene(root);

                // Set the scene on the stage that was created in Main.java.
                Main.stage.setScene(scene);
                Main.stage.show();
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

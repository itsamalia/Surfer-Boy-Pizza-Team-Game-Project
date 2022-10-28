package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.MediaView;

public class MissionController implements EventHandler<ActionEvent> {

    @FXML
    MediaView media;

    @FXML
    Button buttonPushed, gameStartButton;

    @FXML
    Label contextLabel;

    @Override
    public void handle(ActionEvent event) {
        try {

            // Determines which button was pushed and loads that FXML Scene.
            Button buttonPushed = (Button) event.getSource();

            String newScene = "";

            if (buttonPushed.getId().equals("gameStartButton")) {
                newScene = "GameView.fxml";
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleVideo(ActionEvent event) {
        try {
//			String media = "src/application/videos/ArgyleMission.mp4";
//	    	Media h = new Media(Paths.get(media).toUri().toString());
//	    	MediaPlayer mediaPlayer = new MediaPlayer(h);
//	    	mediaPlayer.setAutoPlay(true);

//			Media media = new Media ("src/application/videos/ArgyleMission.mp4");
//			MediaPlayer mediaPlayer = new MediaPlayer(media);
//			mediaPlayer.setAutoPlay(true);
//			MediaView mediaView = new MediaView(mediaPlayer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

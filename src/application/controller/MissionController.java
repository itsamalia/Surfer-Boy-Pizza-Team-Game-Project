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
    MediaView media;
    @FXML
    MediaPlayer mediaBackground, mediaSFX;

    @FXML
    Button buttonPushed, gameStartButton;

    @FXML
    Label contextLabel, missionLabel;

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

    @Override
    public void handle(ActionEvent event) {
        try {

            // Determines which button was pushed and loads that FXML Scene.
            Button buttonPushed = (Button) event.getSource();

            String newScene = "";

            if (buttonPushed.getId().equals("gameStartButton")) {
                newScene = "GameView.fxml";
                playSound2("mindfighton");
                mediaBackground.stop();
		    	try {
					setCursor("normalSelect");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public void buttonEntered() {
        playSound("buttonhover");
    	try {
			setCursor("normalClick");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void buttonExit()
    {
    	try {
			setCursor("normalSelect");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    
    public void playSound2(String soundName) {
        String s = "src/application/audio/" + soundName + ".mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        // Media(getClass().getResource("application/audio/StrangerThingsThemeSong.mp3").toExternalForm());
        mediaSFX = new MediaPlayer(h);
        mediaSFX.play();
        // mediaPlayer.setStartTime(Duration.seconds(0));
        mediaSFX.setAutoPlay(true);
    }
    
    public void setCursor(String imageName) throws FileNotFoundException
    {
    	Image myImage = new Image(new FileInputStream("src/application/images/"+imageName+".png"));
    	ImageCursor cursor = new ImageCursor(myImage, 0, 0);
    	Scene scene = Main.stage.getScene();
    	scene.getRoot().setCursor(cursor);
    }
    public void playMusic(String musicName) {
        String s = "src/application/audio/" + musicName + ".mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        // Media(getClass().getResource("application/audio/StrangerThingsThemeSong.mp3").toExternalForm());
        // media = new MediaPlayer(h);
        // media.play();
        // mediaPlayer.setStartTime(Duration.seconds(0));
        // mediaPlayer.setAutoPlay(true);
    }
}

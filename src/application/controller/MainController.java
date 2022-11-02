package application.controller;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.text.Position;

import application.Main;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    ImageView logoImg, pizzaTruck, pixelArgyle, blackFadeImg1, blackFadeImg2, blackFadeImg3, blackFadeImg4;

    @FXML
    Button buttonPushed, pizzaStartButton, optionsButton, exitButton;

    MediaPlayer mediaPlayer, mediaPlayerSFX, mediaBackground;

    @FXML
    MediaView backgroundMedia;
    
    @FXML
    Text titleText;
    
    @FXML
    VBox titleVBox;
    
    @FXML
    HBox titleHBox1, titleHBox2;
    
    private Timer timer;
    double TitleStretch;
    double startingStretch = 200;
    int secondsToStretch = 1;
    long start = System.currentTimeMillis();
    long finish = System.currentTimeMillis();
    long timeElapsed = finish - start;
    boolean areButtonsTransitioned = false;
    

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

        // Play FadeTransitions
        playFadeTransition(20, logoImg);
        //playFadeTransition(15, pizzaStartButton);
        //playFadeTransition(15, optionsButton);
        //playFadeTransition(15, exitButton);
        playFadeTransition(10, titleVBox);
        blackFadeImg1.setVisible(false);
        //blackFadeImg2.setVisible(false);
        //blackFadeImg3.setVisible(false);
        //blackFadeImg4.setVisible(false);
        playFadeTransition(15, blackFadeImg2);
        playFadeTransition(15, blackFadeImg3);
        playFadeTransition(15, blackFadeImg4);

		pizzaStartButton.setVisible(false);
		optionsButton.setVisible(false);
		exitButton.setVisible(false);
        //animateTitle(titleVBox, titleHBox1, titleHBox2);
    
        this.timer = new Timer();
        TimerTask task = new TimerTask() {

			@Override
			public void run() {
			    finish = System.currentTimeMillis();
				timeElapsed = finish - start;
				// TODO Auto-generated method stub
				//animateTitle(titleVBox, titleHBox1, titleHBox2);
				double Stretch = Duration.millis(secondsToStretch*500).toMillis()-(timeElapsed/28);
		    	if(Stretch>0)
		    	{
		    		titleVBox.setSpacing(Stretch/3);
		    		titleHBox1.setSpacing(Stretch);
		    		titleHBox2.setSpacing(Stretch);
		    	}
		    	if(Stretch <= 0)
		    	{
		    		pizzaStartButton.setVisible(true);
		    		optionsButton.setVisible(true);
		    		exitButton.setVisible(true);
		            blackFadeImg1.setVisible(true);

		    		if(!areButtonsTransitioned) {
			    		pizzaStartButton.setOpacity(0);
			    		optionsButton.setOpacity(0);
			    		exitButton.setOpacity(0);
		            playFadeTransition(6, pizzaStartButton);
		            playFadeTransition(6, optionsButton);
		            playFadeTransition(6, exitButton);
		            playFadeTransition(3, blackFadeImg1);
		            areButtonsTransitioned = true;
		    		}
		    	}
			}
                
        };
        timer.scheduleAtFixedRate(task, 0, 10);
        
    }
/*
    public void animateTitle(VBox titleVB, HBox titleHB1, HBox titleHB2)
    {
		double Stretch = Duration.millis(secondsToStretch*1000).toMillis()-(timeElapsed/1000);
    	if(Stretch>0)
    	{
    		titleVB.setSpacing(Stretch);
    		titleHB1.setSpacing(Stretch);
    		titleHB2.setSpacing(Stretch);
    	}
    }
    
    public void playAnimateTitle(double seconds, VBox titleVB, HBox titleHB1, HBox titleHB2) {

    }
    */
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
//                Platform.exit();
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

    /**
     * Apply a FadeTransition on a node given the duration in seconds.
     * 
     * @param seconds Time of the fade in seconds (Double)
     * @param node    The child node to have the transition applied to (Node)
     */
    public void playFadeTransition(double seconds, Node node) {
        FadeTransition transition = new FadeTransition(Duration.seconds(seconds), node);
        transition.setFromValue(0);
        transition.setToValue(1.0);
        transition.play();
    }
}
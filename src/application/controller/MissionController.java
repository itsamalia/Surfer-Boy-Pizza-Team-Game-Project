package application.controller;

import java.nio.file.Paths;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MissionController implements EventHandler <ActionEvent>{

	@FXML
	MediaView media;
	
	@FXML
	Button buttonPushed, gameStartButton;
	
	public void handleButton(ActionEvent event)
    {
		try
		{
		 // Determine which button was pressed.
        Button buttonPushed = (Button) event.getSource();

        String newScene = "";
        
		if (buttonPushed.getId().equals("gameStartButton")) {
            newScene = "GameView.fxml";
        } else if (buttonPushed.getId().equals(null)) {
            System.out.println("IT'S ALL WRONG, WHAT HAVE YOU DONE!!!");
        }
		
		Parent root = FXMLLoader.load(Main.class.getResource("view/" + newScene));

        Scene scene = new Scene(root);
        
        Main.stage.setScene(scene);
        Main.stage.show();
		}
		catch
			( Exception e) {
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
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}

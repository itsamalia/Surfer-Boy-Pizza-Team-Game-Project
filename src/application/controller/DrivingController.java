package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class DrivingController extends Controller implements EventHandler<ActionEvent>, Initializable {
	
	@FXML
	ImageView pizzaTruck, roadImage;
	@FXML
	Button nextSceneButton;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Path path = new Path();		
		MoveTo moveTo = new MoveTo(-200, 62);
		HLineTo line = new HLineTo(1000);		
		path.getElements().add(moveTo);
		path.getElements().add(line);				
		PathTransition transition = new PathTransition();
		transition.setNode(pizzaTruck);
		transition.setDuration(Duration.seconds(4));
		transition.setPath(path);
		transition.setCycleCount(1);
		transition.play();	
		playSound("surfsupmydude");
	}
	
	
		
	public void loadScene(String sceneName) {
        try {
            // Connect to the FXML (contains our layout) and load it in.
            Parent root = FXMLLoader.load(Main.class.getResource("view/" + sceneName));
            // Put the layout onto the scene.
            Scene scene = new Scene(root);
            // Set the scene on the stage that was created in Main.java.
            Main.stage.setScene(scene);
            Main.stage.show();
                      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void handle(ActionEvent event) {
		this.loadScene("PizzaFinished.fxml");
			
	}

}

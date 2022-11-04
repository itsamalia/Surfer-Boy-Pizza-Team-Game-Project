package application.controller;

import java.io.FileNotFoundException;

import application.Main;
//import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Allows user to either go back home, or go back to the GameView.fxml and make
 * another pizza, or exit the application.
 * 
 * @author Amalia Talijancic
 * @author Danny Ghrist (kda458)
 *
 */
public class PizzaFinishedController extends Controller implements EventHandler<ActionEvent> {

    @FXML
    private Button homeButton, morePizzaButton, exitButton;

    @FXML
    private Text hoorayPizzaFinishedText;

    @FXML
    private ImageView winArgyle;

//    @FXML
//    private MediaPlayer mediaSFX;

    /**
     * Determines which button was pressed (if we end up having multiple buttons),
     * and loads the view for that corresponding button.
     * 
     * @author Danny Ghrist (kda458): I deleted the extra method as I have a
     *         combined method that can handle moving wherever the user needs to go
     *         determined by which button they press. I incorporated your home
     *         button here for brevity.
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
                try {
                    setCursor("normalClick");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
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
}

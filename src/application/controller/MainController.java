package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
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

/**
 * Controller for the MainView FXML Scene which will be the title screen.
 * 
 * CS3443-004 - Fall 2022
 *
 * @author Danny Ghrist (kda458)
 *
 */
public class MainController implements EventHandler<ActionEvent>, Initializable {

    @FXML
    AnchorPane titlePane;

    @FXML
    ImageView logoImg;

    @FXML
    Button buttonPushed, pizzaStartButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: DELETE THIS TEST CODE BEFORE FINALIZING
//        AnchorPane.setTopAnchor(logoImg, 100.0);
//        AnchorPane.setLeftAnchor(logoImg, 800 - logoImg.getFitWidth());
//        AnchorPane.setRightAnchor(logoImg, 800 - logoImg.getFitWidth());
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

        } catch (

        Exception e) {
            e.printStackTrace();
        }
    }
}
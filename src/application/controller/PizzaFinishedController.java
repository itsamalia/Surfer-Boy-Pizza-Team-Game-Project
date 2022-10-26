package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * 
 * @author Amalia Talijancic
 *
 */

public class PizzaFinishedController implements EventHandler <ActionEvent>{
	
	@FXML 
	Button goBackHome;
	
	/**
     * @author - Amalia's edits
     * Here's a handle for the Home Button meant for the Game View
     */
	public void goBackHome(ActionEvent arg0) {
			
			try {
				Parent root = FXMLLoader.load(getClass().getResource("../view/MainView.fxml"));
				Main.stage.setScene( new Scene(root, 800, 800) );
				Main.stage.show();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

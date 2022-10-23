package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * TODO: UPDATE THIS WHEN MORE FUNCTIONALITY IS ADDED OR REMOVED.
 *
 * Controller for the GameView FXML. Allows a user to drag and drop toppings
 * from the bottom menu onto the pizza image in the center of the BorderPane.
 * 
 * CS3443-004 - Fall 2022
 *
 * @author Danny Ghrist (kda458)
 *
 */
public class GameViewController implements EventHandler<ActionEvent>, Initializable {

    @FXML
    BorderPane mainBorderPane;

    @FXML
    StackPane centerStackPane;

    @FXML
    private VBox rightVbox;

    @FXML
    private ImageView targetImage, vecnaClockImage, sauceImage, topping1Image, topping2Image, topping3Image,
            topping4Image, sauceTarget, topping1Target, topping2Target, topping3Target, topping4Target;

    @FXML
    Label pizzaLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pizzaLabel.setText("Drag the Pizza into the center box.");

        // TODO: DELETE TEST CODE BEFORE FINALIZING.
//        vecnaClockImage.setImage(
//                new Image(getClass().getResource("../images/vecna_clock_to_fit_rightVbox.png").toExternalForm()));

        // Binds img height and width to the container (rightVbox) to resize.
        vecnaClockImage.fitWidthProperty().bind(rightVbox.widthProperty());
        vecnaClockImage.fitHeightProperty().bind(rightVbox.heightProperty());
    }

    @Override
    public void handle(ActionEvent event) {
        // TODO Auto-generated method stub

    }

    /**
     * This is tied to the Event Handler onDragDetected for each of the images to be
     * dragged, and handles the start of the drag and drop process.
     * 
     * @param event Event handler for dragging pizza topping images (MouseEvent)
     */
    @FXML
    public void handleDragDetected(MouseEvent event) {
        /**
         * MainController type and handler method types: public class MainController
         * implements EventHandler<MouseEvent> makeDraggable(MouseEvent event)
         */

        // Drag was detected, start drag-and-drop gesture
        // TODO: DELETE DEBUGGING PRINT STATEMENT WHEN COMPLETE.
        System.out.println("onDragDetected");

        // Create Dragboard and ClipboardContent Objects for use when dragging.
        Dragboard db = null;
        ClipboardContent content = new ClipboardContent();

        // TODO: UPDATE THIS FOR DIFFERENT TOPPINGS TO BE DRAGGED ONTO PIZZA
        // Test which image is being dragged and set accordingly.
        if (event.getSource() == sauceImage) {
            db = sauceImage.startDragAndDrop(TransferMode.ANY);
            content.putImage(sauceImage.getImage());
        } else if (event.getSource() == topping1Image) {
            db = topping1Image.startDragAndDrop(TransferMode.ANY);
            content.putImage(topping1Image.getImage());
        } else if (event.getSource() == topping2Image) {
            db = topping2Image.startDragAndDrop(TransferMode.ANY);
            content.putImage(topping2Image.getImage());
        } else if (event.getSource() == topping3Image) {
            db = topping3Image.startDragAndDrop(TransferMode.ANY);
            content.putImage(topping3Image.getImage());
        } else if (event.getSource() == topping4Image) {
            db = topping4Image.startDragAndDrop(TransferMode.ANY);
            content.putImage(topping4Image.getImage());
        }

        // Puts an image on Dragboard
        db.setContent(content);

        // Debugging print statement to the console.
        System.out.println(content.hasImage());

        event.consume();
    }

    /**
     * This is tied to the Event Handler onDragOver for each of the target images to
     * be dragged to, and handles when a dragged object is over a target drop
     * source.
     * 
     * @param event Event handler for detecting DragOver pizza topping images
     *              (DragEvent)
     */
    public void handleDragOver(DragEvent event) {
        /* data is dragged over the target */
        System.out.println("onDragOver");

        // Accept it only if it has an Image.
        if (event.getDragboard().hasImage()) {
            // Allow for both copying and moving, whatever user chooses
            event.acceptTransferModes(TransferMode.ANY);
            event.consume();
        }
    }

    /**
     * This is tied to the Event Handler onDragEntered for the target source to be
     * dragged to, and handles when a dragged object has entered the bounds of a
     * target drop source.
     * 
     * @param event Event handler for detecting DragEntered pizza topping images
     *              (DragEvent)
     */
    @FXML
    public void handleDragEntered(DragEvent event) {
        // The drag-and-drop gesture entered the target
        System.out.println("onDragEntered");
        // Show to the user that it is an actual gesture target
        if (event.getDragboard().hasImage()) {
            // TODO: DELETE DEBUGGING PRINT STATEMENT BEFORE FINISHING
            System.out.println("Has Image and not target Image");
            pizzaLabel.setText("Now Drop The Topping On the Pizza.");
        }
        event.consume();
    }

    /**
     * This is tied to the Event Handler onDragEntered for the target source to be
     * dragged to, and handles when a dragged object has exited the bounds of a
     * target drop source.
     * 
     * @param event Event handler for detecting DragExited pizza topping images
     *              (DragEvent)
     */
    @FXML
    public void handleDragExited(DragEvent event) {
        /* the drag-and-drop gesture exited the target */
        System.out.println("onDragExited");
        /* show to the user that it is an actual gesture target */
        if (event.getDragboard().hasImage()) {
            pizzaLabel.setText("Drag The Topping Onto the Pizza.");
        }
        event.consume();
    }

    /**
     * This is tied to the Event Handler onDragDropped for the target source to be
     * dragged to, and handles when a dragged object has been dropped into a target
     * drop source.
     * 
     * @param event Event handler for detecting DragExited pizza topping images
     *              (DragEvent)
     */
    @FXML
    public void handleDroppedImage(DragEvent event) {
        if (event.getDragboard().hasImage()) {
            if (event.getGestureSource() == sauceImage) {
                sauceTarget.setImage(sauceImage.getImage());
//                sauceTarget.setOpacity(0.5);
            } else if (event.getGestureSource() == topping1Image) {
                topping1Target.setImage(topping1Image.getImage());
            } else if (event.getGestureSource() == topping2Image) {
                topping2Target.setImage(topping2Image.getImage());
            } else if (event.getGestureSource() == topping3Image) {
                topping3Target.setImage(topping3Image.getImage());
            } else if (event.getGestureSource() == topping4Image) {
                topping4Target.setImage(topping4Image.getImage());
            }
            pizzaLabel.setText("You Dropped The Topping Onto the Pizza!!!");
        }
        System.out.println("On Drag Dropped");
        event.consume();
    }
}

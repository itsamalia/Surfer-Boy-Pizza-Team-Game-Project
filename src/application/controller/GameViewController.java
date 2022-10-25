package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import application.model.Ingredient;
import application.model.Pizza;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.paint.Color;

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

    Pizza buildPizza;
    ArrayList<Label> ingredientLabels;

    @FXML
    BorderPane mainBorderPane;

    @FXML
    StackPane centerStackPane;

    @FXML
    private VBox leftVbox, rightVbox;

    @FXML
    private ImageView doughImage, vecnaClockImage, sauceImage, topping1Image, topping2Image, topping3Image,
            topping4Image, sauceTarget, topping1Target, topping2Target, topping3Target, topping4Target;

    @FXML
    Label pizzaLabel, ingredient1Label, ingredient2Label, ingredient3Label, ingredient4Label, ingredient5Label,
            ingredient6Label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO: CREATE THE PIZZA TO BUILD.
        // TODO: ***** TEST CODE MODIFY TO CREATE RANDOM PIZZA WHEN DONE TESTING *****
        this.buildPizza = new Pizza();
        this.buildPizza.setRandomIngredients();

        // Set the ingredient labels to be composed of what is in the random Array.
        // Set counter variable to 2, that is first topping label Node in leftVbox.
        this.ingredientLabels = new ArrayList<Label>();
        int i = 2;
        List<Node> leftVboxLabels = (List<Node>) leftVbox.getChildren();
        for (Ingredient ingredient : this.buildPizza.getIngredients()) {
//            System.out.println(ingredientLabels.get(i));
            Label label = (Label) leftVboxLabels.get(i);
            label.setText(ingredient.getName());
            this.ingredientLabels.add(label);
            label.setId(this.buildPizza.getIngredients().get(i - 2).getName());
            System.out.println(label.getId());
            System.out.println(this.ingredientLabels);
            System.out.println(this.ingredientLabels.get(i - 2).getId());
            i++;
        }

        // TODO: DELETE DEBUGGING PRINT STATEMENTS AFTER DONE.
//        System.out.println(pizzaSauce.isOnPizza());
//        pizzaSauce.setOnPizza(true);
//        System.out.println(pizzaSauce.isOnPizza());
//        System.out.println(this.buildPizza.getIngredients());
//        System.out.println(this.buildPizza.getIngredients().get();

        /*
         * TODO: FIGURE OUT HOW THIS WORKS SO I CAN STRIKE OUT THE LABELS OF INGREDIENTS
         * THAT HAVE BEEN PLACED.
         */
//        ingredient1Label.setStyle("-fx-strikethrough: true");

        // TODO: LOAD RANDOM TOPPINGS

        // TODO: DELETE TEST CODE BEFORE FINALIZING.
//        vecnaClockImage.setImage(
//                new Image(getClass().getResource("../images/vecna_clock_to_fit_rightVbox.png").toExternalForm()));

        // Binds img height and width to the container (rightVbox) to resize.
        // TODO: FIX OR REMOVE THIS AS IT DOESN'T LOOK RIGHT AND WON'T RESIZE BACK DOWN.
        vecnaClockImage.fitWidthProperty().bind(rightVbox.widthProperty());
        vecnaClockImage.fitHeightProperty().bind(rightVbox.heightProperty());
        pizzaLabel.setText("Drag the Pizza into the center box.");
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
//        System.out.println("onDragDetected");

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
//        System.out.println(content.hasImage());

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
//        System.out.println("onDragOver");

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
//        System.out.println("onDragEntered");
        // Show to the user that it is an actual gesture target
        if (event.getDragboard().hasImage()) {
            // TODO: DELETE DEBUGGING PRINT STATEMENT BEFORE FINISHING
//            System.out.println("Has Image and not target Image");
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
//        System.out.println("onDragExited");
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
                setToppingInfo(sauceImage, sauceTarget, this.getLabel("Pizza Sauce"),
                        this.ingredientLabels.indexOf(this.getLabel("Pizza Sauce")));
//                sauceTarget.setOpacity(0.5);
            } else if (event.getGestureSource() == topping1Image) {
                setToppingInfo(topping1Image, topping1Target, this.getLabel("Ham"),
                        this.ingredientLabels.indexOf(this.getLabel("Ham")));
            } else if (event.getGestureSource() == topping2Image) {
                setToppingInfo(topping2Image, topping2Target, this.getLabel("Mushroom"),
                        this.ingredientLabels.indexOf(this.getLabel("Mushroom")));
            } else if (event.getGestureSource() == topping3Image) {
                setToppingInfo(topping3Image, topping3Target, this.getLabel("Pineapple"),
                        this.ingredientLabels.indexOf(this.getLabel("Pineapple")));
            } else if (event.getGestureSource() == topping4Image) {
                setToppingInfo(topping4Image, topping4Target, this.getLabel("Onion"),
                        this.ingredientLabels.indexOf(this.getLabel("Onion")));
            }
            pizzaLabel.setText("You Dropped The Topping Onto the Pizza!!!");
        }
//        System.out.println("On Drag Dropped");
        if (this.buildPizza.isFinished()) {
            this.loadScene("PizzaFinished.fxml");
        }
        event.consume();
    }

    private void setToppingInfo(ImageView sourceImage, ImageView targetImage, Label ingredientLabel, int i) {

        // TODO: FIX THIS SO IT DOESN'T THROW AN ERROR WHEN THERE ARE FEWER TOPPINGS IN
        // BUILDPIZZA ARRAY.
        if (this.buildPizza.getIngredients().get(i).equals(null)) {
            System.out.println("This ingredient is null.");
        } else if (!this.buildPizza.getIngredients().get(i).isOnPizza() /* !this.pizzaSauce.isOnPizza() */) {
            targetImage.setImage(sourceImage.getImage());
            this.buildPizza.getIngredients().get(i).setOnPizza(true);
            ingredientLabel.setText("ADDED!!!");
            ingredientLabel.setTextFill(Color.NAVAJOWHITE);
        } else if (this.buildPizza.getIngredients().get(i).isOnPizza()) {
            System.out.println("target already on pizza.");
            ingredientLabel.setText("Already Added");
        }
    }

    private Label getLabel(String topping) {
        Label label = null;
        for (Node node : this.ingredientLabels) {

//            System.out.println(node.getId());
            if (node.getId() != null && node.getId().equalsIgnoreCase(topping)) {
                label = (Label) node;
            }
        }
        return label;
    }

    /**
     * Uses sceneName in the form of fxml document file name with .fxml extension to
     * load a new scene.
     * 
     * @param sceneName The fxml file name to be loaded (String)
     */
    private void loadScene(String sceneName) {
        try {
            // Connect to the FXML (contains our layout) and load it in.
            Parent root = FXMLLoader.load(Main.class.getResource("view/" + sceneName));

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
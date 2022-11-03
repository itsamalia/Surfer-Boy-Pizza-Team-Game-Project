package application.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import application.Main;
import application.model.Ingredient;
import application.model.Pizza;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * TODO: UPDATE THIS WHEN MORE FUNCTIONALITY IS ADDED OR REMOVED.
 *
 * Controller for the GameView FXML. Allows a user to drag and drop toppings
 * from the bottom menu onto the pizza image in the center of the BorderPane.
 * Upon load a random list of ingredients is shown on the left side and those
 * are the toppings the user needs to add to the pizza. The dough is shown by
 * default.
 * 
 * CS3443-004 - Fall 2022
 *
 * @author Danny Ghrist (kda458)
 *
 */
public class GameViewController implements EventHandler<ActionEvent>, Initializable {

    final static double COUNTDOWN_MINUTES = 5;

    private Pizza buildPizza;
    private ArrayList<Label> ingredientLabels;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private StackPane centerStackPane;

    @FXML
    private VBox leftVbox, rightVbox;

    @FXML
    private ImageView doughImage, vecnaClockImage, sauceImage, topping1Image, topping2Image, topping3Image,
            topping4Image, sauceTarget, topping1Target, topping2Target, topping3Target, topping4Target;

    @FXML
    private Label pizzaLabel, ingredient1Label, ingredient2Label, ingredient3Label, ingredient4Label, ingredient5Label,
            ingredient6Label;

    @FXML
    private Text countdownText;

    private MediaPlayer mediaPlayer, mediaSFX;
    private Timer timer;

    /**
     * Everything to be initialized upon the initial loading of the Scene (i.e,
     * animations, music, timers, etc...)
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        music();

        this.buildPizza = new Pizza();
        this.buildPizza.setRandomIngredients();

        // Set the ingredient labels to be composed of what is in the random Array.
        // Set counter variable to 2, that is first topping label Node in leftVbox.
        this.ingredientLabels = new ArrayList<Label>();
        // i is 2 because the first two nodes in the lefVbox are not ingredient nodes.
        int i = 2;
        List<Node> leftVboxLabels = (List<Node>) leftVbox.getChildren();
        for (Ingredient ingredient : this.buildPizza.getIngredients()) {
            Label label = (Label) leftVboxLabels.get(i);
            label.setText(ingredient.getName());
            this.ingredientLabels.add(label);
            label.setId(this.buildPizza.getIngredients().get(i - 2).getName());
            i++;
        }

        this.timer = new Timer();
        TimerTask task = new TimerTask() {

            double counter = 60 * COUNTDOWN_MINUTES;
            int minutes, seconds;

            @Override
            public void run() {
                if (counter > 0) {
                    System.out.println("counter: " + counter);
                    minutes = (int) counter / 60;
                    seconds = (int) counter % 60;
                    countdownText.setText(String.format("%d:%02d\n", minutes, seconds));
//                    System.out.printf("%d:%02d\n", minutes, seconds);
                    counter--;
                } else {
                    // TODO: Implement and switch to game over screen if timer reaches 0. Currently
                    // it just exits the program.
                    System.out.println("YOU LOSE!!! (IMPLEMENT GAME OVER SCENE TO SWITCH TO)");
                    timer.cancel();
                    System.exit(0);
                }
            }
        };

//        timer.schedule(task, 1000);
        timer.scheduleAtFixedRate(task, 0, 1000);

        // TODO: DELETE DEBUGGING PRINT STATEMENTS AFTER DONE.
//        System.out.println(pizzaSauce.isOnPizza());
//        pizzaSauce.setOnPizza(true);
//        System.out.println(pizzaSauce.isOnPizza());
//        System.out.println(this.buildPizza.getIngredients());
//        System.out.println(this.buildPizza.getIngredients().get();

        // Binds img height and width to the container (rightVbox) to resize.
        // TODO: FIX OR REMOVE THIS AS IT DOESN'T LOOK RIGHT AND WON'T RESIZE BACK DOWN.
//        vecnaClockImage.fitWidthProperty().bind(rightVbox.widthProperty());
//        vecnaClockImage.fitHeightProperty().bind(rightVbox.heightProperty());
        pizzaLabel.setText("Drag the Pizza into the center box.");
    }

    @Override
    public void handle(ActionEvent event) {
        // TODO Auto-generated method stub

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
                playSound("sauceEffect");
//                sauceTarget.setOpacity(0.5);
            } else if (event.getGestureSource() == topping1Image) {
                setToppingInfo(topping1Image, topping1Target, this.getLabel("Ham"),
                        this.ingredientLabels.indexOf(this.getLabel("Ham")));
                playSound("placeIngredientEffect");
            } else if (event.getGestureSource() == topping2Image) {
                setToppingInfo(topping2Image, topping2Target, this.getLabel("Mushroom"),
                        this.ingredientLabels.indexOf(this.getLabel("Mushroom")));
                playSound("placeIngredientEffect");
            } else if (event.getGestureSource() == topping3Image) {
                setToppingInfo(topping3Image, topping3Target, this.getLabel("Pineapple"),
                        this.ingredientLabels.indexOf(this.getLabel("Pineapple")));
                playSound("placeIngredientEffect");
            } else if (event.getGestureSource() == topping4Image) {
                setToppingInfo(topping4Image, topping4Target, this.getLabel("Onion"),
                        this.ingredientLabels.indexOf(this.getLabel("Onion")));
                playSound("placeIngredientEffect");
            }
            pizzaLabel.setText("You Dropped The Topping Onto the Pizza!!!");
        }
//        System.out.println("On Drag Dropped");
        if (this.buildPizza.isFinished()) {
            this.loadScene("PizzaFinished.fxml");
            this.timer.cancel();
            playSound("surfsupmydude");
        }
        event.consume();
    }

    /**
     * Sets the topping information for a topping and updates the label for that
     * topping to determine if it has already been added or not so that it will not
     * update the target image if the topping has already been added.
     * 
     * @param sourceImage     The image being dragged (ImageView)
     * @param targetImage     The image to be dropped into (ImageView)
     * @param ingredientLabel The Label of the item being dragged (Label)
     * @param i               Counter variable used to obtain individual Ingredient
     *                        (int)
     */
    private void setToppingInfo(ImageView sourceImage, ImageView targetImage, Label ingredientLabel, int i) {
        if (i < 0) {
            System.out.println("This ingredient doesn't need to be added to the current pizza.");
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

    /**
     * Return the Label of the specified topping to be used in updating Label text.
     * 
     * @param topping Name of the topping to find Label info (String)
     * @return The Label that the topping is currently in (Label)
     */
    private Label getLabel(String topping) {
        Label returnLabel = null;
        for (Label label : this.ingredientLabels) {

//            System.out.println(node.getId());
            if (label.getId() != null && label.getId().equalsIgnoreCase(topping)) {
                returnLabel = label;
            }
        }
        return returnLabel;
    }

    /**
     * Uses sceneName in the form of fxml document file name with .fxml extension to
     * load a new scene.
     * 
     * @param sceneName The fxml file name to be loaded (String)
     */
    public void loadScene(String sceneName) {
        try {
            // Connect to the FXML (contains our layout) and load it in.
            Parent root = FXMLLoader.load(Main.class.getResource("view/" + sceneName));
            // Put the layout onto the scene.
            Scene scene = new Scene(root);
            // Set the scene on the stage that was created in Main.java.
            Main.stage.setScene(scene);
            Main.stage.show();
            try {
                setCursor("normalSelect");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            mediaPlayer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the custom cursor.
     * 
     * @param imageName The name of the image file of the cursor. (String)
     * @throws FileNotFoundException (Exception)
     */
    public void setCursor(String imageName) throws FileNotFoundException {
        Image myImage = new Image(new FileInputStream("src/application/images/" + imageName + ".png"));
        ImageCursor cursor = new ImageCursor(myImage, 0, 0);
        Scene scene = Main.stage.getScene();
        scene.getRoot().setCursor(cursor);
    }

    /**
     * Plays the background music for the Scene.
     */
    public void music() {
        String s = "src/application/audio/VecnaClockSound.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        // Media(getClass().getResource("application/audio/StrangerThingsThemeSong.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setCycleCount(-1);
        mediaPlayer.play();
        // mediaPlayer.setVolume(100);
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
        // Media(getClass().getResource("application/audio/StrangerThingsThemeSong.mp3").toExternalForm());
        mediaSFX = new MediaPlayer(h);
        mediaSFX.play();
        // mediaPlayer.setStartTime(Duration.seconds(0));
        // mediaPlayer.setAutoPlay(true);
    }
}

package application.controller;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import application.Main;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
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
 * @author Caleb Pierce (rvt884)
 * @author Sarah Halverson
 * @author Amalia Talijancic
 * @author Carlos Martinez
 */
public class MainController extends Controller implements EventHandler<ActionEvent>, Initializable {

    @FXML
    private AnchorPane titlePane;

    // @FXML
    // ImageView logoImg, cursorReferenceNormal,/*pizzaTruck, pixelArgyle,*/
    // blackFadeImg1, blackFadeImg2, blackFadeImg3, blackFadeImg4;

    @FXML
    private ImageView logoImg, soundMessageImgView, /* pizzaTruck, pixelArgyle, */ blackFadeImg1, blackFadeImg2,
            blackFadeImg3, overallFade, lineTop, lineLeft, lineRight;

    @FXML
    private Button buttonPushed, pizzaStartButton, optionsButton, exitButton, skipButton;

    @FXML
    private MediaView backgroundMedia;

    @FXML
    private Text titleText;

    @FXML
    private Label creditsLabel;

    @FXML
    private VBox titleVBox;

    @FXML
    private HBox titleHBox1, titleHBox2;

    private Timer timer;
    private Timer startTimerToRunOnce;
    double TitleStretch;
    double startingStretch = 200;
    int secondsToStretch = 1;
    long start = System.currentTimeMillis();
    long finish = System.currentTimeMillis();
    int timeElapsed = (int) (finish - start);
    boolean areButtonsTransitioned = false;
    double lastTimeElapsed;
    double stretch = 200;
    private ArrayList<TranslateTransition> arrayOfAnimations = new ArrayList<TranslateTransition>();
    private FadeTransition fadeInSoundAnimation;
    private Timer startTimerButtons;

    /**
     * Everything to be initialized upon the initial loading of the Scene (i.e,
     * animations, music, timers etc...)
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // AnchorPane.setBottomAnchor(pizzaTruck, 50.0);
        // AnchorPane.setBottomAnchor(pixelArgyle, 25.0);
        // AnchorPane.setBottomAnchor(backgroundMedia, 0.0);

        // TODO: DELETE THIS TEST CODE BEFORE FINALIZING
//        AnchorPane.setTopAnchor(logoImg, 100.0);
//        AnchorPane.setLeftAnchor(logoImg, 800 - logoImg.getFitWidth());
//        AnchorPane.setRightAnchor(logoImg, 800 - logoImg.getFitWidth());  

        playMusic("StrangerThingsThemeSong");
        playFadeOutTransition(15, overallFade);
        playFadeOutSoundMessageTransition(5, soundMessageImgView);
        playVideo("mainMenuBackground", -1, backgroundMedia);
        playFadeInTransition(2, titleVBox);
        // Play FadeTransitions
        playFadeInTransition(20, logoImg);
        // playFadeTransition(15, pizzaStartButton);
        // playFadeTransition(15, optionsButton);
        // playFadeTransition(15, exitButton);

        blackFadeImg1.setVisible(false);
        blackFadeImg2.setVisible(false);
        blackFadeImg3.setVisible(false);
        // blackFadeImg4.setVisible(false);
        /*
         * playFadeTransition(15, blackFadeImg2); playFadeTransition(15, blackFadeImg3);
         * playFadeTransition(15, blackFadeImg4);
         */
        pizzaStartButton.setVisible(false);
        optionsButton.setVisible(false);
        exitButton.setVisible(false);

        // animateTitle(titleVBox, titleHBox1, titleHBox2);

        this.timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                /*
                 * // finish = System.currentTimeMillis(); // lastTimeElapsed = timeElapsed;
                 * //timeElapsed += 1.0;
                 * 
                 * // TODO Auto-generated method stub // animateTitle(titleVBox, titleHBox1,
                 * titleHBox2); // System.out.print("running animation\n");
                 * 
                 * // stretch = (500*((exp(-timeElapsed/3000.0)))-.1); //stretch -= .41; //
                 * System.out.print("done calculation\n");
                 * 
                 * //if (stretch > 0) { // System.out.print("set stretch\n");
                 * //titleVBox.setSpacing(stretch * 3.5); //titleHBox1.setSpacing(stretch *
                 * 1.8); //titleHBox2.setSpacing(stretch * 1.8); }else if (stretch <= 0) {
                 * pizzaStartButton.setVisible(true); optionsButton.setVisible(true);
                 * exitButton.setVisible(true); // blackFadeImg1.setVisible(true);
                 * 
                 * if (!areButtonsTransitioned && skipButton.isVisible()) {
                 * pizzaStartButton.setOpacity(0); optionsButton.setOpacity(0);
                 * exitButton.setOpacity(0); playFadeTransition(3, pizzaStartButton);
                 * playFadeTransition(3, optionsButton); playFadeTransition(3, exitButton);
                 * skipButton.setVisible(false); // playFadeTransition(3, blackFadeImg1);
                 * areButtonsTransitioned = true; } } else if (skipButton.isVisible() == false
                 * && stretch > 0) { pizzaStartButton.setOpacity(1);
                 * optionsButton.setOpacity(1); exitButton.setOpacity(1);
                 * titleVBox.setOpacity(1); }
                 */}

        };
        timer.scheduleAtFixedRate(task, 0, 30);

        this.startTimerToRunOnce = new Timer();
        TimerTask startTask = new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    setCursor("normalSelect");
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        startTimerToRunOnce.schedule(startTask, 100);

        this.startTimerButtons = new Timer();
        TimerTask startTaskButton1 = new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (!areButtonsTransitioned) {
                    pizzaStartButton.setVisible(true);
                    // optionsButton.setVisible(true);
                    // exitButton.setVisible(true);
                    playFadeInTransition(.5, pizzaStartButton);
                    // playFadeInTransition(3, optionsButton);
                    // playFadeInTransition(3, exitButton);
                    skipButton.setVisible(false);
                    overallFade.setVisible(false);
                    soundMessageImgView.setVisible(false);
                }
            }
        };
        startTimerToRunOnce.schedule(startTaskButton1, 30150);
        this.startTimerButtons = new Timer();
        TimerTask startTaskButton2 = new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (!areButtonsTransitioned) {
                    optionsButton.setVisible(true);
                    playFadeInTransition(.5, optionsButton);
                }
            }
        };
        startTimerToRunOnce.schedule(startTaskButton2, 30600 - 60);
        this.startTimerButtons = new Timer();
        TimerTask startTaskButton3 = new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (!areButtonsTransitioned) {
                    exitButton.setVisible(true);
                    playFadeInTransition(.5, exitButton);

                }
            }
        };
        startTimerToRunOnce.schedule(startTaskButton3, 31200 - 135);
        this.startTimerButtons = new Timer();
        TimerTask startTaskCredits = new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (!areButtonsTransitioned) {
                    animateText(creditsLabel,
                            "Made Possible By: Danny Ghrist, Caleb Pierce, Sarah Halverson, Amalia Talijancic, & Carlos Martinez");
                    areButtonsTransitioned = true;
                }
            }
        };
        startTimerToRunOnce.schedule(startTaskCredits, 40260);

        randomizedTranslationAnimation(titleVBox, titleHBox1, titleHBox2, -800, 800, -500, 800);
        linesAnimation();
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
                newScene = "Mission.fxml";
                playSound("youaresodead");
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
                this.timer.cancel();
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
                try {
                    setCursor("normalSelect");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
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
    public void handleButtonEntered() {
        playSound("buttonhover");
        try {
            setCursor("normalClick");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
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
     * Handles the skip introduction button to skip the introduction animation.
     */
    public void skipButtonClicked() {

        pizzaStartButton.setVisible(true);
        optionsButton.setVisible(true);
        exitButton.setVisible(true);
        stretch = .42;
        skipButton.setVisible(false);
        playSound("buttonClick");
        areButtonsTransitioned = true;
        for (int i = 0; i < arrayOfAnimations.size(); i++) {
            arrayOfAnimations.get(i).stop();
            arrayOfAnimations.get(i).setDuration(Duration.millis(1));
            arrayOfAnimations.get(i).play();
        }
        fadeInAnimation.stop();
        fadeInAnimation.setDuration(Duration.millis(1));
        fadeInAnimation.play();
        overallFade.setVisible(false);
        fadeInSoundAnimation.stop();
        fadeInSoundAnimation.setDuration(Duration.millis(1));
        fadeInSoundAnimation.play();
        soundMessageImgView.setVisible(false);
        animateText(creditsLabel,
                "Made Possible By: Danny Ghrist, Caleb Pierce, Sarah Halverson, Amalia Talijancic, & Carlos Martinez");
    }

    /**
     * 
     * 
     * @param seconds
     * @param node
     */
    public void playFadeOutSoundMessageTransition(double seconds, Node node) {
        FadeTransition transition = new FadeTransition(Duration.seconds(seconds), node);
        transition.setFromValue(1);
        transition.setToValue(0.0);
        transition.play();
        fadeInSoundAnimation = transition;
    }

    public void linesAnimation()
    {
    	TranslateTransition ttLL = new TranslateTransition(Duration.millis(30000), lineLeft);
    	TranslateTransition ttLR = new TranslateTransition(Duration.millis(30000), lineRight);
    	TranslateTransition ttLT = new TranslateTransition(Duration.millis(30000), lineTop);
    	ttLL.setToX(-40);
    	ttLL.setToY(-45);
    	ttLR.setToX(25);
    	ttLR.setToY(-45);
    	ttLT.setToX(0);
    	ttLT.setToY(63);
    	
    	ttLL.setFromX(-700);
    	ttLR.setFromX(700);
    	ttLT.setFromX(-1000);

    	arrayOfAnimations.add(ttLL);
    	arrayOfAnimations.add(ttLR);
    	arrayOfAnimations.add(ttLT);
    	ttLL.play();
    	ttLR.play();
    	ttLT.play();
    }
    /**
     * Randomized a Translation animation.
     * 
     * @param titleVB  (Vbox)
     * @param titleHB1 (HBox)
     * @param titleHB2 (HBox)
     * @param minX     (int)
     * @param maxX     (int)
     * @param minY     (int)
     * @param maxY     (int)
     */
    public void randomizedTranslationAnimation(VBox titleVB, HBox titleHB1, HBox titleHB2, int minX, int maxX, int minY,
            int maxY) {
        /*
         * ObservableList<Node> listVB = titleVB.getChildren(); for(int v = 0;
         * v<listVB.size(); v++) { TranslateTransition ttv = new
         * TranslateTransition(Duration.millis(30000), listVB.get(v));
         * arrayOfAnimations.add(ttv); int randomNum =
         * ThreadLocalRandom.current().nextInt(minX, maxX); ttv.setFromX(randomNum);
         * ttv.setToX(listVB.get(v).getTranslateX()); randomNum =
         * ThreadLocalRandom.current().nextInt(minY, maxY); ttv.setFromY(randomNum);
         * ttv.setToY(listVB.get(v).getTranslateY()); ttv.play(); }
         */
        ObservableList<Node> listH1 = titleHB1.getChildren();
        for (int h1 = 0; h1 < listH1.size(); h1++) {
            TranslateTransition ttH1 = new TranslateTransition(Duration.millis(30000), listH1.get(h1));
            arrayOfAnimations.add(ttH1);
            int randomNum = ThreadLocalRandom.current().nextInt(minX, maxX);
            ttH1.setFromX(randomNum);
            ttH1.setToX(listH1.get(h1).getTranslateX());
            randomNum = ThreadLocalRandom.current().nextInt(minY, maxY);
            ttH1.setFromY(randomNum);
            ttH1.setToY(listH1.get(h1).getTranslateY());
            ttH1.play();
        }
        ObservableList<Node> listH2 = titleHB2.getChildren();
        for (int h2 = 0; h2 < listH2.size(); h2++) {
            TranslateTransition ttH2 = new TranslateTransition(Duration.millis(30000), listH2.get(h2));
            arrayOfAnimations.add(ttH2);
            int randomNum = ThreadLocalRandom.current().nextInt(minX, maxX);
            ttH2.setFromX(randomNum);
            ttH2.setToX(listH2.get(h2).getTranslateX());
            randomNum = ThreadLocalRandom.current().nextInt(minY, maxY);
            ttH2.setFromY(randomNum);
            ttH2.setToY(listH2.get(h2).getTranslateY());
            ttH2.play();
        }
    }

    /*
     * public void animateTitle(VBox titleVB, HBox titleHB1, HBox titleHB2) { double
     * Stretch =
     * Duration.millis(secondsToStretch*1000).toMillis()-(timeElapsed/1000);
     * if(Stretch>0) { titleVB.setSpacing(Stretch); titleHB1.setSpacing(Stretch);
     * titleHB2.setSpacing(Stretch); } }
     * 
     * public void playAnimateTitle(double seconds, VBox titleVB, HBox titleHB1,
     * HBox titleHB2) { ======= >>>>>>> branch 'main' of
     * https://github.com/UTSA-CS-3443/Delivery-Service ======= >>>>>>> branch
     * 'main' of https://github.com/UTSA-CS-3443/Delivery-Service
     * 
     * /* public void animateTitle(VBox titleVB, HBox titleHB1, HBox titleHB2) {
     * double Stretch =
     * Duration.millis(secondsToStretch*1000).toMillis()-(timeElapsed/1000);
     * if(Stretch>0) { titleVB.setSpacing(Stretch); titleHB1.setSpacing(Stretch);
     * titleHB2.setSpacing(Stretch); } }
     * 
     * public void playAnimateTitle(double seconds, VBox titleVB, HBox titleHB1,
     * HBox titleHB2) {
     * 
     * }
     */

    /**
     * 
     * 
     * @param val double value (double)
     * @return (double)
     */
    public static double exp(double val) {
        final long tmp = (long) (1512775 * val + (1072693248 - 60801));
        return Double.longBitsToDouble(tmp << 32);
    }
}
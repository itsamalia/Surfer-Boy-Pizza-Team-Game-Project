package application.model;

import javafx.scene.control.Label;

/**
 * The ham topping object, extended from the Ingredient class.
 * 
 * CS3443-004 - Fall 2022
 *
 * @author Danny Ghrist (kda458)
 *
 */
public class ToppingHam extends Ingredient {

    private Label hamLabel;

    /**
     * Contructor that extends from the superclass (Ingredient) to set the name and
     * onPizza values inherited from the superclass.
     */
    public ToppingHam() {
        super("Ham");
    }

    /**
     * @return the hamLabel
     */
    public Label getHamLabel() {
        return this.hamLabel;
    }

    /**
     * @param hamLabel the hamLabel to set
     */
    public void setHamLabel(Label hamLabel) {
        this.hamLabel = hamLabel;
    }
}
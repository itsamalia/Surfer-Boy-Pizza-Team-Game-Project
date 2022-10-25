package application.model;

/**
 * The marinara sauce object, extended from the Ingredient class.
 * 
 * CS3443-004 - Fall 2022
 *
 * @author Danny Ghrist (kda458)
 *
 */
public class ToppingSauce extends Ingredient {

    /**
     * Contructor that extends from the superclass (Ingredient) to set the name and
     * onPizza values inherited from the superclass.
     */
    public ToppingSauce() {
        super("Pizza Sauce");
    }
}
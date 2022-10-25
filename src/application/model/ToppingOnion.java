package application.model;

/**
 * The onion topping object, extended from the Ingredient class.
 * 
 * CS3443-004 - Fall 2022
 *
 * @author Danny Ghrist (kda458)
 *
 */
public class ToppingOnion extends Ingredient {

    /**
     * Contructor that extends from the superclass (Ingredient) to set the name and
     * onPizza values inherited from the superclass.
     */
    public ToppingOnion() {
        super("Onion");
    }
}
package application.model;

/**
 * 
 * 
 * CS3443-004 - Fall 2022
 *
 * @author Danny Ghrist (kda458)
 *
 */
public abstract class Ingredient {

    String name;
    boolean onPizza;

    // TODO: ADD ANY NECCESSARY ITEMS TO THIS CONSTRUCTOR.
    // Constructor for Ingredients object.
    public Ingredient(String name) {
        this.name = name;
        this.onPizza = false;
    }

    /**
     * Returns the name of the ingredient
     * 
     * @return The name of the ingredient (String)
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns True if the ingredient is already on the pizza, and false if the
     * topping has not been placed on the pizza by the user yet.
     * 
     * @return If ingredient set on pizza (boolean)
     */
    public boolean isOnPizza() {
        return this.onPizza;
    }

    /**
     * @param onPizza The onPizza to set (boolean)
     */
    public void setOnPizza(boolean onPizza) {
        this.onPizza = onPizza;
    }

}
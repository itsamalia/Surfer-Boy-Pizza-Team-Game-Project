package application.model;

import java.util.ArrayList;

/**
 * Controls the actions of adding ingredients to the pizza and what ingredients
 * need to be added to a pizza by randomly selecting ingredients from the
 * ingredients ArrayList.
 * 
 * CS3443-004 - Fall 2022
 *
 * @author Danny Ghrist (kda458)
 *
 */
public class Pizza {

    ArrayList<Ingredients> ingredients;

    // TODO: ADD ANY NECCESSARY ITEMS TO THIS CONSTRUCTOR.
    /**
     * Constructor for the Pizza object. All pizzas need to start with a crust at a
     * minimum. Most likely sauce and cheese as well.
     * 
     * @param ingredients Ingredients needed for pizza (ArrayList<Ingredients>)
     */
    public Pizza(ArrayList<Ingredients> ingredients) {
        this.ingredients = new ArrayList<Ingredients>();
        this.ingredients.addAll(ingredients);
    }

}

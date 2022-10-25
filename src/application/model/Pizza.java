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

    ArrayList<Ingredient> ingredients;

    // TODO: ADD ANY NECCESSARY ITEMS TO THIS CONSTRUCTOR.
    /**
     * Constructor for the Pizza object. All pizzas need to start with a crust at a
     * minimum. Most likely sauce and cheese as well.
     * 
     * @param ingredients Ingredients needed for pizza (ArrayList<Ingredients>)
     */
    public Pizza(ArrayList<Ingredient> ingredients) {
        this.ingredients = new ArrayList<Ingredient>();
        this.ingredients.addAll(ingredients);
    }

    /**
     * Constructor for a new blank Pizza object with no toppings/ingredients.
     */
    public Pizza() {
        this.ingredients = new ArrayList<Ingredient>();
        this.setRandomIngredients();
    }

    // TODO: METHOD TO GENERATE RANDOM INGREDIENTS (SHOULD ALWAYS INCLUDE DOUGH).
    public void setRandomIngredients() {
        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>();
        allIngredients.add(new ToppingHam());
        allIngredients.add(new ToppingPineapple());
        allIngredients.add(new ToppingSauce());
//        allIngredients.add(new ToppingOnion());
        allIngredients.add(new ToppingMushroom());
        this.setIngredients(allIngredients);
    }

    /**
     * Gets the ingredients needed for a pizza.
     * 
     * @return The ingredients (ArrayList<Ingredient>)
     */

    // TODO: CREATE A METHOD THAT FINDS AN INGREDIENT, USING THE INGREDIENT NAME,
    // WITHIN AN ARRAY.
//    public void findIngredient(String ingredientName) {
//        if (ingredientName == ) {
//    }
//    }

    public boolean isFinished() {
        boolean ret = true;
        for (Ingredient ingredient : this.getIngredients()) {
            if (!ingredient.isOnPizza()) {
                ret = false;
            }
        }
        return ret;
    }

    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    /**
     * Sets the ingredients needed for a pizza.
     * 
     * @param ingredients The ingredients to set (ArrayList<Ingredient>)
     */
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Add an ingredient needed to build pizza.
     * 
     * @param ingredient The ingredient to be added (Ingredient)
     */
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}

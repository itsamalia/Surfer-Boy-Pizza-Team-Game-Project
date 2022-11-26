package application.model;

import java.util.ArrayList;
import java.util.Random;

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
    }

    /**
     * Sets the ingredients ArrayList of the Pizza Object to a list of random
     * Ingredients from the available Ingredients.
     */
    public void setRandomIngredients() {
        ArrayList<Ingredient> allIngredients = this.getListOfIngredientTypes();
        ArrayList<Ingredient> randomIngredients = new ArrayList<Ingredient>();
        Random randNumIngredients = new Random();
        Random randIngredient = new Random();
        int tempNumIngredients = randNumIngredients.nextInt(5) + 1;
        int tempIngredientIndex = 0;
        System.out.println("Number of Ingredients in randomIngredients: " + tempNumIngredients);
        for (int i = 0; i < tempNumIngredients/* randNumIngredients.nextInt(5) + 1 */; i++) {
            tempIngredientIndex = randIngredient.nextInt(5);

            while (randomIngredients.contains(allIngredients.get(tempIngredientIndex))) {
                tempIngredientIndex = randIngredient.nextInt(5);
            }
            randomIngredients.add(allIngredients.get(tempIngredientIndex));
        }
        this.setIngredients(randomIngredients);
    }

    /**
     * Generates an ArrayList<Ingredient> of all the available toppings that we have
     * subclasses for that are of type Ingredient.
     * 
     * @return List of with one of each available Ingredient (ArrayList<Ingredient>)
     */
    private ArrayList<Ingredient> getListOfIngredientTypes() {
        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>();
        allIngredients.add(new ToppingSauce());
        allIngredients.add(new ToppingHam());
        allIngredients.add(new ToppingMushroom());
        allIngredients.add(new ToppingPineapple());
        allIngredients.add(new ToppingOnion());
        return allIngredients;
    }

    /**
     * Determines if the Pizza Object is fully built and returns true if yes and
     * false if no.
     * 
     * @return If pizza is finished being built (boolean)
     */
    public boolean isFinished() {
        boolean ret = true;
        for (Ingredient ingredient : this.getIngredients()) {
            if (!ingredient.isOnPizza()) {
                ret = false;
            }
        }
        return ret;
    }

    /**
     * Gets the ingredients needed for a pizza.
     * 
     * @return The ingredients (ArrayList<Ingredient>)
     */
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

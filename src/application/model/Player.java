package application.model;

/**
 * Contains information for the Player to be used throughout the program to keep
 * track of their progress.
 * 
 * CS3443-004 - Fall 2022
 *
 * @author Danny Ghrist (kda458)
 *
 */
public class Player {

    private String name;
    private int numPizzasToMake;
    private int numPizzasMade;
    private int numPizzasRemaining;
    private boolean isDone;

    /**
     * 
     */
    public Player(int numPizzasToMake) {
        this.numPizzasToMake = numPizzasToMake;
        this.numPizzasMade = 0;
        this.numPizzasRemaining = numPizzasToMake;
        this.isDone = false;
    }

    /**
     * Returns the number of pizzas that the Player needs to complete making.
     * 
     * @return The number of pizzas Player needs to make (int)
     */
    public int calculatePizzasRemaining() {
        return this.getNumPizzasToMake() - this.getNumPizzasMade();
    }

    /**
     * Returns true if the Player is done making pizzas or false if they still have
     * pizzas remaining to be made.
     * 
     * @return If all the pizzas have been completed by Player (boolean)
     */
    public boolean isPlayerFinished() {
        if (this.calculatePizzasRemaining() < 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return The Player name (String)
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name The Player name to set (String)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The numPizzasToMake (int)
     */
    public int getNumPizzasToMake() {
        return this.numPizzasToMake;
    }

    /**
     * @param numPizzasToMake The numPizzasToMake to set (int)
     */
    public void setNumPizzasToMake(int numPizzasToMake) {
        this.numPizzasToMake = numPizzasToMake;
    }

    /**
     * @return The numPizzasMade (int)
     */
    public int getNumPizzasMade() {
        return this.numPizzasMade;
    }

    /**
     * @param numPizzasMade The numPizzasMade to set (int)
     */
    public void setNumPizzasMade(int numPizzasMade) {
        this.numPizzasMade = numPizzasMade;
    }

    /**
     * @return The numPizzasRemaining (int)
     */
    public int getNumPizzasRemaining() {
        return this.numPizzasRemaining;
    }

    /**
     * Add a pizza to the Player's pizzaMade count;
     */
    public void addPizzaMade() {
        this.numPizzasMade++;
    }
}

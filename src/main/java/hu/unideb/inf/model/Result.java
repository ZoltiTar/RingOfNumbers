package hu.unideb.inf.model;

/**
 * Class for representing information about the player.
 */
public class Result {

    /**
     * The name of the player.
     */
    private String name;

    //TODO: implement stopwatch to track time of player.
    private long time;

    /**
     * The number of steps the player made.
     */
    private int steps;

    private boolean solved;


    // Increments the player's step count.
    public void step() {
        steps++;
    }

}

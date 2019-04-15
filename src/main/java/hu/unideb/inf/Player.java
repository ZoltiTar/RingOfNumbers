package hu.unideb.inf;

public class Player {
    private String name;
    private long time;
    private int steps;

    public Player(String name, long time, int steps) {
        this.name = name;
        this.time = time;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void step() {
        steps++;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}

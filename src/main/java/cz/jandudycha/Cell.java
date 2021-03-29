package cz.jandudycha;

public class Cell {
    private int amountOfNeighbors;
    private boolean alive;

    public Cell(int amountOfNeighbors, boolean alive) {
        this.amountOfNeighbors = amountOfNeighbors;
        this.alive = alive;
    }

    public int getAmountOfNeighbors() {
        return amountOfNeighbors;
    }

    public void setAmountOfNeighbors(int amountOfNeighbors) {
        this.amountOfNeighbors = amountOfNeighbors;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

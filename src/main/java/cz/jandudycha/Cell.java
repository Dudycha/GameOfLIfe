package cz.jandudycha;



public class Cell {
    private int amountOfNeighbors;
    private boolean alive;

    public Cell(int amountOfNeighbors, boolean alive) {
        this.amountOfNeighbors = amountOfNeighbors;
        this.alive = alive;
    }

    public void resetNeighbors() {
        amountOfNeighbors = 0;
    }

    public void incrementAliveNeighborCount() {
        amountOfNeighbors++;
    }

    public int getAmountOfNeighbors() {
        return amountOfNeighbors;
    }


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

package cz.jandudycha;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener {
    private static final int SCREEN_WIDTH = 700; // sloupcu
    private static final int SCREEN_HEIGHT = 700; // radku
    private static final int UNIT_SIZE = 10;
    private static final int DELAY = 100;
    private Cell arrayOfCells[][] = new Cell[SCREEN_WIDTH / UNIT_SIZE][SCREEN_HEIGHT / UNIT_SIZE];
    private final int[] offsetX = {0, 1, 1, 1, 0, -1, -1, -1};
    private final int[] offsetY = {-1, -1, 0, 1, 1, 1, 0, -1};

    private Timer timer;
    private boolean running = false;


    public Game() {
        System.out.println(SCREEN_WIDTH / UNIT_SIZE);
        System.out.println(SCREEN_HEIGHT / UNIT_SIZE);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(this.getForeground());
        this.setFocusable(true);
        populateGame();
        seedGame();
        startGame();

    }

    private void populateGame() {
        for (int i = 0; i < arrayOfCells.length; i++) {
            for (int j = 0; j < arrayOfCells[0].length; j++) {
                arrayOfCells[i][j] = new Cell(0, false);
            }
        }
    }

    private void seedGame() {

        //Small circle
        arrayOfCells[41][20].setAlive(true);
        arrayOfCells[42][20].setAlive(true);
        arrayOfCells[43][20].setAlive(true);
        arrayOfCells[41][22].setAlive(true);
        arrayOfCells[42][22].setAlive(true);
        arrayOfCells[43][22].setAlive(true);
        arrayOfCells[41][21].setAlive(true);
        arrayOfCells[43][21].setAlive(true);

        //Small glider
        arrayOfCells[13][4].setAlive(true);
        arrayOfCells[14][5].setAlive(true);
        arrayOfCells[15][5].setAlive(true);
        arrayOfCells[15][4].setAlive(true);
        arrayOfCells[15][3].setAlive(true);

        //Gosper glider gun
        arrayOfCells[0][28].setAlive(true);
        arrayOfCells[1][28].setAlive(true);
        arrayOfCells[0][29].setAlive(true);
        arrayOfCells[1][29].setAlive(true);
        arrayOfCells[13][26].setAlive(true);
        arrayOfCells[12][26].setAlive(true);
        arrayOfCells[11][27].setAlive(true);
        arrayOfCells[10][28].setAlive(true);
        arrayOfCells[10][29].setAlive(true);
        arrayOfCells[10][30].setAlive(true);
        arrayOfCells[11][31].setAlive(true);
        arrayOfCells[12][32].setAlive(true);
        arrayOfCells[13][32].setAlive(true);
        arrayOfCells[15][27].setAlive(true);
        arrayOfCells[16][28].setAlive(true);
        arrayOfCells[16][29].setAlive(true);
        arrayOfCells[16][30].setAlive(true);
        arrayOfCells[17][29].setAlive(true);
        arrayOfCells[15][31].setAlive(true);
        arrayOfCells[14][29].setAlive(true);
        arrayOfCells[20][26].setAlive(true);
        arrayOfCells[21][26].setAlive(true);
        arrayOfCells[20][27].setAlive(true);
        arrayOfCells[21][27].setAlive(true);
        arrayOfCells[20][28].setAlive(true);
        arrayOfCells[21][28].setAlive(true);
        arrayOfCells[22][25].setAlive(true);
        arrayOfCells[22][29].setAlive(true);
        arrayOfCells[24][24].setAlive(true);
        arrayOfCells[24][25].setAlive(true);
        arrayOfCells[24][29].setAlive(true);
        arrayOfCells[24][30].setAlive(true);
        arrayOfCells[34][26].setAlive(true);
        arrayOfCells[35][26].setAlive(true);
        arrayOfCells[34][27].setAlive(true);
        arrayOfCells[35][27].setAlive(true);
    }

    public void startGame() {
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }


    private void update() {
        calculateLivingNeighbors();
        //   drawInfo();
        followRulesOfLife();
    }

    private void drawInfo() {
        System.out.println("========================================");
        for (int y = 0; y < arrayOfCells.length; y++) {
            for (int x = 0; x < arrayOfCells[0].length; x++) {
                if (arrayOfCells[x][y].isAlive()) {
                    System.out.println("CELL on [" + x + "," + y + "] has " + arrayOfCells[x][y].getAmountOfNeighbors() + " neighbors");
                }
            }
        }

    }

    private void followRulesOfLife() {
        for (int y = 0; y < arrayOfCells.length; y++) {
            for (int x = 0; x < arrayOfCells[0].length; x++) {
                if (arrayOfCells[x][y].isAlive()) {
                    //CASE FOR ALIVE CELLS
                    if (arrayOfCells[x][y].getAmountOfNeighbors() < 2) { //UNDERPOPULATION
                        arrayOfCells[x][y].setAlive(false);
                    }
                    if (arrayOfCells[x][y].getAmountOfNeighbors() > 3) { //OVERPOPULATION
                        arrayOfCells[x][y].setAlive(false);
                    }

                } else {
                    //CASE FOR DEAD CELLS
                    if (arrayOfCells[x][y].getAmountOfNeighbors() == 3) {
                        arrayOfCells[x][y].setAlive(true);
                    }

                }
                arrayOfCells[x][y].resetNeighbors();
            }
        }
    }


    private void calculateLivingNeighbors() {
        for (int y = 0; y < arrayOfCells.length; y++) {
            for (int x = 0; x < arrayOfCells[0].length; x++) {

                for (int i = 0; i < offsetX.length; i++) {
                    int nx = x + offsetX[i];
                    int ny = y + offsetY[i];
                    if (!(nx < 0 || nx > arrayOfCells.length - 1 || ny < 0 || ny > arrayOfCells[0].length - 1)) {
                        if (arrayOfCells[nx][ny].isAlive()) {
                            arrayOfCells[x][y].incrementAliveNeighborCount();
                        }
                    }
                }

            }
        }

    }

    public void render(Graphics g) {
        drawGrid(g);
        drawContent(g);

    }

    private void drawContent(Graphics g) {
        for (int i = 0; i < arrayOfCells.length; i++) {
            for (int j = 0; j < arrayOfCells[0].length; j++) {
                if (arrayOfCells[i][j].isAlive()) {
                    g.setColor(Color.RED);
                    g.fillRect(i * UNIT_SIZE + 1, j * UNIT_SIZE + 1, UNIT_SIZE - 1, UNIT_SIZE - 1);
                }
            }
        }
    }

    private void drawGrid(Graphics g) {
        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            g.setColor(new Color(80, 80, 80));
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
        for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
            g.setColor(new Color(80, 80, 80));
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            update();
            repaint();
        }
    }


}

package cz.jandudycha;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game extends JPanel implements ActionListener {
    private static final int SCREEN_WIDTH = 700;
    private static final int SCREEN_HEIGHT = 700;
    private static final int UNIT_SIZE = 10;
    private static final int DELAY = 500;
    private Cell arrayOfCells[][] = new Cell[SCREEN_WIDTH / UNIT_SIZE][SCREEN_HEIGHT / UNIT_SIZE];
    private final int[] offsetX = {0, 1, 0, -1};
    private final int[] offsetY = {-1, 0, 1, 0};

    private Timer timer;
    private Random random;
    private boolean running = false;


    public Game() {
        System.out.println(SCREEN_WIDTH / UNIT_SIZE);
        System.out.println(SCREEN_HEIGHT / UNIT_SIZE);
        random = new Random();
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
       // arrayOfCells[0][0].setAlive(true);

           arrayOfCells[50][10].setAlive(true);
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

    }

    private void calculateLivingNeighbors() {
        for (int i = 0; i < arrayOfCells.length; i++) {
            for (int j = 0; j < arrayOfCells[0].length; j++) {


                for (int k = 0; k < offsetX.length; k++) {
                    int nx = j + offsetX[k];
                    int ny = i + offsetY[k];
                    if (nx < 0 || nx > arrayOfCells.length - 1 || ny < 0 || ny > arrayOfCells[0].length - 1) {
                        System.out.println("out of bounds");
                    } else {
                        if (arrayOfCells[nx][ny].isAlive()) {
                            arrayOfCells[i][j].setAlive(true);
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
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
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

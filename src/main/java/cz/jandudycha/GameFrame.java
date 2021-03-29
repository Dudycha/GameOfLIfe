package cz.jandudycha;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        Game panel = new Game();
        this.add(panel);
        this.setTitle("Game of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}

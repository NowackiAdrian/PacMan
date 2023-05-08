package Counters;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PacMan extends JPanel implements ActionListener {
    private int[][] maze = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,2,2,2,2,1,1,1,2,2,2,2,1},
            {1,2,1,1,1,1,1,2,2,2,2,2,1,1,2,1},
            {1,2,1,2,2,2,1,2,1,1,1,2,2,2,2,1},
            {1,2,1,2,1,2,1,2,2,2,2,2,1,1,2,1},
            {1,2,2,2,1,2,2,2,1,1,1,2,2,2,2,1},
            {1,1,1,2,1,1,1,2,2,2,2,2,1,1,2,1},
            {1,2,2,2,2,2,2,2,1,1,1,2,2,2,2,1},
            {1,2,1,1,1,1,1,2,2,2,2,2,1,1,2,1},
            {1,2,1,2,2,2,1,2,1,1,1,2,2,2,2,1},
            {1,2,1,2,1,2,1,2,2,2,2,2,1,1,2,1},
            {1,2,2,2,1,2,2,2,1,1,1,2,2,2,2,1},
            {1,1,1,2,1,1,1,2,2,2,2,2,1,1,2,1},
            {1,2,2,2,2,2,2,2,1,1,1,2,2,2,2,1},
            {1,2,1,1,1,1,1,2,2,2,2,2,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };

    private int x, y;
    private int dx, dy;
    private int points;

    public PacMan() {
        x = 1;
        y = 1;
        dx = 0;
        dy = 0;
        points = 0;

        Timer timer = new Timer(1000, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch(key) {
                    case KeyEvent.VK_LEFT:
                        dx = -1;
                        dy = 0;
                        break;
                    case KeyEvent.VK_RIGHT:
                        dx = 1;
                        dy = 0;
                        break;
                    case KeyEvent.VK_UP:
                        dx = 0;
                        dy = -1;
                        break;
                    case KeyEvent.VK_DOWN:
                        dx = 0;
                        dy = 1;
                        break;
                }
        }
        });


        setFocusable(true);
        }

@Override
public void actionPerformed(ActionEvent e) {
        if (maze[y+dy][x+dx] != 1) {
        x += dx;
        y += dy;

        // Check if Pac-Man has collided with a ball
        if (maze[y][x] == 2) {
        maze[y][x] = 3;
        points++;
        }
        }

        repaint();
        }

@Override
public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw maze
        int blockSize = 30;
        int mazeWidth = maze[0].length * blockSize;
        int mazeHeight = maze.length * blockSize;
        for (int y = 0; y < maze.length; y++) {
        for (int x = 0; x < maze[y].length; x++) {
        if (maze[y][x] == 1) {
        g.setColor(Color.BLUE);
        g.fillRect(x*blockSize, y*blockSize, blockSize, blockSize);
        } else if (maze[y][x] == 2) {
        g.setColor(Color.YELLOW);
        g.fillOval(x*blockSize+10, y*blockSize+10, blockSize/2, blockSize/2);
        }
        }
        }

        // Draw Pac-Man
        g.setColor(Color.RED);
        g.fillOval(x*blockSize+5, y*blockSize+5, blockSize-10, blockSize-10);

        // Draw score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Points: " + points, 50, mazeHeight + 50);
        }

public static void main(String[] args) {
        JFrame frame = new JFrame("Pac-Man");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        PacMan pacMan = new PacMan();
        frame.add(pacMan);

        frame.setVisible(true);
        }}

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.System.exit;


public class PacMan {
    private int x;
    private int y;
    private int mouthAngle;

    public PacMan() {
        this.x = 1;
        this.y = 1;
        this.mouthAngle = 45; // Start with an open mouth
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMouthAngle() {
        return mouthAngle;
    }

    public void setMouthAngle(int mouthAngle) {
        this.mouthAngle = mouthAngle;
    }

//    public void movePacMan(KeyEvent e, int[][] maze) {
//
//        int[] nextPos = new int[2];
//        int []currentPos = new int[2];
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_UP:
//                nextPos = new int[] {currentPos[this.x] - 1, currentPos[this.x]};
//                break;
//            case KeyEvent.VK_DOWN:
//                nextPos = new int[] {currentPos[this.x] + 1, currentPos[this.x]};
//                break;
//            case KeyEvent.VK_LEFT:
//                nextPos = new int[] {currentPos[this.x], currentPos[this.x] - 1};
//                break;
//            case KeyEvent.VK_RIGHT:
//                nextPos = new int[] {currentPos[this.x], currentPos[this.x] + 1};
//                break;
//            default:
//                System.out.println("Invalid direction input.");
//                return;
//        }
//
//        int nextRow = nextPos[0];
//        int nextCol = nextPos[1];
//
//        // Check if next position is within the bounds of the maze
//        if (nextRow < 0 || nextRow >= maze.length || nextCol < 0 || nextCol >= maze[0].length) {
//            return;
//        }
//
//        int nextTile = maze[nextRow][nextCol];
//
//        // Check if Pac-Man can move to the next position
//        if (nextTile == 1) {
//            return;
//        } else if (nextTile == 2) {
//            // Collect point and replace it with a 0
//            maze[nextRow][nextCol] = 0;
////            score++;
//        } else if (nextTile == 4) {
//            // Collision with ghost, game over
//
//            System.exit(0);
//            return;
//        }
//
//        // Move Pac-Man to the next position
//        maze[currentPos[0]][currentPos[1]] = 0;
//        maze[nextRow][nextCol] = 3;
//        this.repaint();
//    }
}

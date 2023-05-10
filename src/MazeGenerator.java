import javax.swing.*;

public class MazeGenerator {
    public static Integer size;

    public static int[][]  createMaze() {
        while (true) {
            size = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter maze size (10-100):"));
            try {
                if (size < 10 || size > 100) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid integer between 10 and 100.");
            }
        }

        int[][] maze = new int[size][size];

        // Fill first and last row with 1's
        int WALL = 1;
        for (int i = 0; i < size; i++) {
            maze[0][i] = WALL;
            maze[size - 1][i] = WALL;
        }

        // Fill first and last column with 1's
        for (int i = 0; i < size; i++) {
            maze[i][0] = WALL;
            maze[i][size - 1] = WALL;
        }

        // Fill remaining cells with 2's
        for (int i = 1; i < size - 1; i++) {
            for (int j = 1; j < size - 1; j++) {
                maze[i][j] = 2;
            }
        }

        // fill remaining cells with 1's every second index
        for (int i = 2; i < size - 2; i += 2) {
            for (int j = 2; j < size - 2; j += 2) {
                maze[i][j] = 1;
            }
        }

        // Place Pacman at (1, 1)
        int PACMAN = 3;
        maze[1][1] = PACMAN;

        // Place enemies at (size/2, size/2-1) and (size/2, size/2+1)
        int middle = size / 2;
        int ENEMY = 4;
        maze[middle][middle] = ENEMY;
        maze[middle][middle - 1] = ENEMY;
        maze[middle][middle + 1] = ENEMY;

        // Print maze
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    return maze;}
}



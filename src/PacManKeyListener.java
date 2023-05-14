import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PacManKeyListener extends GameBoard implements KeyListener {
    private final int[][] maze;
    MazeTableModel mazeTableModel;
    private int pacManRow = 1;
    private int pacManCol = 1;
    int score;

    public PacManKeyListener(int[][] maze)  {
      this.maze = maze;
      this.mazeTableModel =  new MazeTableModel();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int newRow = pacManRow;
        int newCol = pacManCol;
        switch (key) {
            case KeyEvent.VK_UP -> newRow = pacManRow - 1;
            case KeyEvent.VK_DOWN -> newRow = pacManRow + 1;
            case KeyEvent.VK_LEFT -> newCol = pacManCol - 1;
            case KeyEvent.VK_RIGHT -> newCol = pacManCol + 1;
            default -> {
            }
        }

        if (this.maze[newCol][newRow] == 1) {
            return;
        } else if (this.maze[newCol][newRow] == 2) {
            // Collect point and replace it with a 0
            this.maze[newCol][newRow] = 0;
            this.mazeTableModel.setValueAt(0,newRow,newCol);
            this.score++;
        } else if (this.maze[newCol][newRow] == 4) {
            // Collision with ghost, game over
            System.exit(0);
            return;
        }

        //update pacman position;
        this.pacManRow = newRow;
        this.pacManCol = newCol;
        this.maze[this.pacManCol][this.pacManRow] = 3;
        this.mazeTableModel.setValueAt(3,pacManRow,pacManCol);

    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}

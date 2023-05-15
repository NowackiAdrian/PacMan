import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Arc2D;
import java.lang.reflect.Array;
import java.util.Arrays;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameBoard  {
    PacMan pacMan;
    int mazeWidth;
    int mazeHeight;
    private final int rows;
    private final int cols;

    private int[][] maze ;
    private JTable table;
    private JLabel scoreLabel;
    private JFrame frame;
    private int score = 0;
    private int TABLE_HEIGHT = 500;
    private int TABLE_WIDTH = 500;
    int mouthAngle = 45;

    int blockSize;
    MazeTableModel mazeTableModel;


    public GameBoard(int[][] maze) {
        this.maze= maze;
        this.rows = maze.length;
        this.cols = maze[0].length;
        this.pacMan = new PacMan();
        JFrame frame = new JFrame();
        JPanel pan = new  JPanel();
         this.mazeTableModel = new MazeTableModel();
//        table.addKeyListener(new PacManKeyListener(this.maze));
        frame.setTitle("PacMan");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.BLUE);

        // create JTable to display the maze
        table = new JTable(mazeTableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setGridColor(Color.WHITE);
        table.setShowGrid(true);
        table.setDefaultEditor(Object.class, null); // disable editing
        table.setDefaultRenderer(Object.class, new CustomCellRenderer());


        frame.add(table, BorderLayout.CENTER);
        this.blockSize  = Math.min(table.getWidth() / table.getColumnCount(), table.getHeight() / table.getRowCount());
        // create score label
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(scoreLabel, BorderLayout.SOUTH);

        //set the table always as the size of the frame
        pan.add(new JScrollPane(table), BorderLayout.CENTER);


        // set size and show
        frame.add(pan);
        frame.pack();
        frame.setVisible(true);
        table.setFocusable(true); // Set panel to be focusable to receive key events
        table.addKeyListener(new PacManKeyListener(this.maze,this.mazeTableModel));
        table.requestFocusInWindow(); // Request focus to receive key events immediately
        UIManager.put("List.selectionBackground", Color.WHITE);

    }

    public MazeTableModel getMazeTableModel() {
        return mazeTableModel;
    }

    public class MazeTableModel extends AbstractTableModel  {
        

        @Override
        public int getRowCount() {
            return rows;
        }

        @Override
        public int getColumnCount() {
            return cols;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return maze[rowIndex][columnIndex];
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            if (maze[rowIndex][columnIndex] == 2) {
                score++;
                scoreLabel.setText("Score: " + score);
            }
            maze[rowIndex][columnIndex] = (int) value;
            fireTableCellUpdated(rowIndex, columnIndex);
            table.repaint();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }
    }

    public class CustomCellRenderer extends DefaultTableCellRenderer {

            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Set the background color of the cell
                if (value instanceof Integer) {
                    int intValue = (int) value;
                    if (intValue == 1) {
                        c.setBackground(Color.BLUE);
                    } else if (intValue == 2) {
                        c.setBackground(Color.WHITE);
                        return new OvalCellRenderer();
                    } else if (intValue == 3) {
                        c.setBackground(Color.WHITE);
                        return new PacManCellRenderer();
                    }else if (intValue == 4) {
                        c.setBackground(Color.WHITE);
                        return new EnemyCellRenderer();
                    }else {
                        c.setBackground(Color.WHITE);
                    }
                }
                if(!(value==null)){
                    setText("");
                }

                return c;
            }

            private  class OvalCellRenderer extends JPanel {
                public OvalCellRenderer() {
                    setOpaque(false);

                }

                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(Color.YELLOW);
                    g.fillOval(2, 2, getWidth() - 10, getHeight() - 10);

                }
            }
        }


    public class PacManKeyListener implements KeyListener {
        private final int[][] maze;
        GameBoard.MazeTableModel mazeTableModel;
        private  int  pacManRow = 1;
        private  int pacManCol = 1;
        int score;

        public PacManKeyListener(int[][] maze, MazeTableModel mazeTableModel)  {
            this.maze = maze;
            this.mazeTableModel = mazeTableModel;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            int newRow = pacManRow;
            int newCol = pacManCol;
            int previousRow = newRow;
            int previousCol = newCol;
            switch (key) {
                case KeyEvent.VK_UP:
                    newRow = newRow - 1;
                    break;
                case KeyEvent.VK_DOWN :
                    newRow = newRow + 1;
                    break;
                case KeyEvent.VK_LEFT :
                    newCol = newCol - 1;
                    break;
                case KeyEvent.VK_RIGHT :
                    newCol = newCol + 1;
                    break;
                default :
                    System.out.println("wrong keyboard input");
                    break;
            }

            if (this.maze[newRow][newCol] == 0) {
                // move Pacman to the new position
                this.mazeTableModel.setValueAt(3,newRow,newCol);
                // update Pacman's position
                this.pacManRow = newRow;
                this.pacManCol = newCol;
                // update the previous position with a 0
                this.mazeTableModel.setValueAt(0,previousRow,previousCol);
            } else if (this.maze[newRow][newCol] == 1) {
                // wall, don't move Pacman
                return;
            }else if (this.maze[newRow][newCol] == 2 ){
                score++;
                this.mazeTableModel.setValueAt(3,newRow,newCol);
                this.mazeTableModel.setValueAt(0,previousRow,previousCol);
                this.pacManRow = newRow;
                this.pacManCol = newCol;
            }
            else if (this.maze[newRow][newCol] == 4) {
                // Collision with ghost, game over
                System.exit(0);
                return;
            }
        }
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}

    }




    private class PacManCellRenderer extends JPanel  {
        private int mouthAngle = 45;
        private Timer chompTimer;
        boolean gameOver;

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            // Draw the pacman mouth
            g2d.setColor(Color.red);
            int mouthSize = getWidth() / 2;
            int mouthAngle = GameBoard.this.pacMan.getMouthAngle();
            g2d.fill(new Arc2D.Double(
                    GameBoard.this.pacMan.getX(), GameBoard.this.pacMan.getY(),
                    getWidth(), getHeight(), mouthAngle, 360 - 2 * mouthAngle, Arc2D.PIE));
        }

    }


        private class EnemyCellRenderer extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.GREEN);
            g2d.fillRect(GameBoard.this.pacMan.getX()+7, GameBoard.this.pacMan.getY(), getWidth() -10,getWidth() -10);
        }}




    public static void main(String[] args) {

       GameBoard gameBoard =  new GameBoard(MazeGenerator.createMaze());

    }}




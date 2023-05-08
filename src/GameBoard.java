import Counters.PacMan;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GameBoard  {
    PacMan pacMan;
    int mazeWidth;
    int mazeHeight;
    private final int rows = 10;
    private final int cols = 15;
    private int[][] maze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 3, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 2, 2, 2, 1, 2, 2, 2, 0, 2, 2, 2, 0, 1},
            {1, 0, 2, 1, 1, 2, 2, 1, 2, 2, 2, 1, 2, 0, 1},
            {1, 0, 2, 2, 1, 2, 0, 2, 2, 0, 2, 2, 2, 0, 1},
            {1, 0, 0, 1, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 1},
            {1, 0, 2, 2, 1, 0, 2, 1, 2, 0, 2, 2, 2, 0, 1},
            {1, 0, 2, 1, 2, 2, 0, 0, 2, 2, 2, 1, 2, 0, 1},
            {1, 0, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 2, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private JTable table;
    private JLabel scoreLabel;
    private JFrame frame;
    private int score = 0;
    private int TABLE_HEIGHT = 500;
    private int TABLE_WIDTH = 500;
    private int COL_HEIGHT = 30;
    private int ROW_HEIGHT = 30;
    int blockSize;


    public GameBoard() {
        this.pacMan = new PacMan();
        JFrame frame = new JFrame();

        frame.setTitle("PacMan");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.BLUE);

         mazeWidth = maze[0].length * blockSize;
         mazeHeight = maze.length * blockSize;

        // create JTable to display the maze
        table = new JTable(new MazeTableModel());
        table.setRowHeight(70);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
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

        // set size and show
        frame.setSize(this.TABLE_WIDTH, this.TABLE_HEIGHT-300);
        frame.setVisible(true);
    }


private class MazeTableModel extends AbstractTableModel  {

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


        private class PacManCellRenderer extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.RED);
            g2d.fillRect(GameBoard.this.pacMan.getX()+7, GameBoard.this.pacMan.getY(), getWidth() -10,getWidth() -10);
        }
    }

    public class MyPanel extends JPanel {
        public MyPanel() {
            setBackground(Color.BLACK); // Set the background color of the panel
            setOpaque(true); // Set the panel to be opaque
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK); // Set the color for drawing
            g.fillRect(0, 0, getWidth(), getHeight()); // Fill the entire panel with the color
        }
    }


    public static void main(String[] args) {
       GameBoard gameBoard =  new GameBoard();

    }}




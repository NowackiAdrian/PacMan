import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;



public class GameBoard extends JPanel {

    private int rows;
    private int cols;
    private int[][] gameBoard;
    private JTable table;

    public GameBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        gameBoard = new int[rows][cols];
        setLayout(new BorderLayout());
        table = new JTable(new GameTableModel());
        table.setRowHeight(30);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        add(new JScrollPane(table), BorderLayout.CENTER);
        setPreferredSize(new Dimension(cols * 30, rows * 30));
        generateGameBoard();
    }

    private void generateGameBoard() {
        // create outer walls
        for (int i = 0; i < cols; i++) {
            gameBoard[0][i] = 1;
            gameBoard[rows - 1][i] = 1;
        }
        for (int i = 0; i < rows; i++) {
            gameBoard[i][0] = 1;
            gameBoard[i][cols - 1] = 1;
        }
        // create inner walls
        for (int i = 2; i < cols - 2; i += 3) {
            for (int j = 2; j < rows - 2; j++) {
                gameBoard[j][i] = 1;
            }
        }
        for (int i = 2; i < rows - 2; i += 3) {
            for (int j = 2; j < cols - 2; j++) {
                gameBoard[i][j] = 1;
            }
        }
        // create yellow points
        for (int i = 1; i < cols - 1; i++) {
            for (int j = 1; j < rows - 1; j++) {
                if (gameBoard[j][i] == 0 && (i + j) % 4 == 0) {
                    gameBoard[j][i] = 2;
                }
            }
        }
    }

    private void getCellPanel(int value, boolean isSelected, boolean hasFocus) {
        for (int[] coulmnandrow: gameBoard){
        for (int value: coulmnandrow) {
        if (value == 0) {
            table.setBackground(Color.WHITE);
        } else if (value == 1) {
            table.setBackground(Color.BLUE);
        } else if (value == 2) {
            table.setBackground(Color.WHITE);
            Graphics g = table.getGraphics();
            g.setColor(Color.YELLOW);
            g.fillOval(30 / 4, 30 / 4, 30 / 2, 30 / 2);
        }}}}


        private class GameTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return rows;
        }

        @Override
        public int getColumnCount() {
            return cols;
        }

        @Override
        public Object getValueAt(int row, int col) {
            int cellValue = gameBoard[row][col];
            if (cellValue == 0 || cellValue == 2) {
                return 0;
            } else if (cellValue == 1) {
                return 1;
            }
            return null;
        }}
        private class GameTableCellRenderer implements TableCellRenderer {

                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    int cellValue = (int) value;
                    return getCellPanel(cellValue, isSelected, hasFocus);
                }

    }
    public static void main(String[] args) {
        int rows = 15;
        int cols = 20;
        JFrame frame = new JFrame("GameBoard Test");
        GameBoard board = new GameBoard(rows, cols);
        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        }}

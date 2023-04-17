import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class Display extends JFrame implements KeyListener {

    private JPanel[][] grid;
    private Maze maze;

    public Display(int rows, int cols) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pathfinding");
        setSize(900, 900);
        setResizable(false);
        setLayout(new GridLayout(rows, cols));
        grid = new JPanel[rows][cols];
        maze = new Maze(rows, cols);
        addKeyListener(this);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new JPanel();
                grid[i][j].setBackground(Color.GRAY);
                SquareListener squareListener = new SquareListener();
                grid[i][j].addMouseListener(squareListener);
                grid[i][j].addMouseMotionListener(squareListener);
                grid[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                add(grid[i][j]);
            }
        }

        setVisible(true);
    }


    public int colIndex(JPanel square) {
        for (JPanel[] jPanels : grid) {
            for (int j = 0; j < grid.length; j++) {
                if (square.equals(jPanels[j])) {
                    return j;
                }
            }
        }
        return -1;
    }

    public int rowIndex(JPanel square) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (square.equals(grid[i][j])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            System.out.println("Let's do this");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private class SquareListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            try {
                if (!maze.needsStart()) {
                    JPanel square = (JPanel) e.getSource();
                    int row = rowIndex(square);
                    int cols = colIndex(square);
                    maze.setPath(row, cols);
                    square.setBackground(Color.green);

                } else if (!maze.needsEnd()) {
                    JPanel square = (JPanel) e.getSource();
                    int row = rowIndex(square);
                    int cols = colIndex(square);
                    maze.setEnd(row, cols);
                    square.setBackground(Color.magenta);
                } else {
                    processMouseEvent(e);
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) {
                try {
                    if (!maze.needsStart()) {
                        JPanel square = (JPanel) e.getSource();
                        int rows = rowIndex(square);
                        int cols = colIndex(square);

                        square.setBackground(Color.green);
                        maze.setPath(rows, cols);
                    }  else if (!maze.needsEnd()) {
                        JPanel square = (JPanel) e.getSource();
                        int rows = rowIndex(square);
                        int cols = colIndex(square);

                        square.setBackground(Color.magenta);
                        maze.setEnd(rows, cols);
                    } else {
                        processMouseEvent(e);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JPanel square = (JPanel) e.getSource();
                square.setBackground(Color.red);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JPanel square = (JPanel) e.getSource();
            int row = rowIndex(square);
            int cols = colIndex(square);

            if (maze.isStart(row, cols)) {
                square.setBackground(Color.green);
            } else if (maze.isEnd(row, cols)) {
                square.setBackground(Color.magenta);
            } else if (maze.isWall(row, cols)) {
                square.setBackground(Color.black);
            } else {
                square.setBackground(Color.gray);
            }
        }

        private void processMouseEvent(MouseEvent e) throws Exception {
            JPanel square = (JPanel) e.getSource();
            int cols = colIndex(square);
            int row = rowIndex(square);
            if (maze.isWall(row, cols)) {
                square.setBackground(Color.gray);
                maze.setNode(row, cols, 0);
            } else if (maze.isStart(row, cols)) {
                square.setBackground(Color.gray);
                maze.removePath(row, cols);
            }  else if (maze.isEnd(row, cols)) {
                square.setBackground(Color.gray);
                maze.removeEnd(row, cols);

            } else {
                square.setBackground(Color.black);
                maze.setNode(row, cols, 1);
            }
        }
    }
}


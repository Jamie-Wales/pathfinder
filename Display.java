import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;


public class Display extends JFrame {

    private JPanel[][] grid;
    private Boolean[][] clicked;

    public Display(int rows, int cols) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Pathfinding");
        setSize(900, 900);
        setResizable(false);


        grid = new JPanel[rows][cols];
        clicked = new Boolean[rows][cols];
        for (int i = 0; i < clicked.length; i++) {
            Arrays.fill(clicked[i], false);
        }
        setLayout(new GridLayout(rows, cols));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new JPanel();
                grid[i][j].setBackground(Color.GRAY);
                grid[i][j].addMouseListener(new SquareListener());
                grid[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                add(grid[i][j]);
            }
        }

        setVisible(true);

    }

    private class SquareListener implements MouseListener, MouseMotionListener {

        private boolean pressed = false;
        @Override
        public void mouseClicked(MouseEvent e) {
            JPanel square = (JPanel) e.getSource();
            int cols = colIndex(square);
            int row = rowIndex(square);
            if (!clicked[row][cols]) {
                square.setBackground(Color.black);
                clicked[row][cols] = true;
            } else {
                square.setBackground(Color.GRAY);
                clicked[row][cols] = false;
            }



        }

        @Override
        public void mousePressed(MouseEvent e) {
            pressed = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            pressed = false;
        }


        @Override
        public void mouseEntered(MouseEvent e) {
            JPanel square = (JPanel) e.getSource();
            square.setBackground(Color.red);

        }

        @Override
        public void mouseExited(MouseEvent e) {
            JPanel square = (JPanel) e.getSource();
            int cols = colIndex(square);
            int row = rowIndex(square);
            if (!clicked[row][cols]) {
                square.setBackground(Color.gray);
            } else {
                square.setBackground(Color.black);
            }

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    private int colIndex(JPanel square) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (square.equals(grid[i][j])) {
                    return j;
                }
            }
        }
        return -1;
    }

    private int rowIndex(JPanel square) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (square.equals(grid[i][j])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
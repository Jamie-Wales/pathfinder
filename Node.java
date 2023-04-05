import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public class Node extends JComponent {

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(50, 50, 50,50);
    }
}

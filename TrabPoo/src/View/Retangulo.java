package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

public class Retangulo extends JComponent {

	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int width;
    private int height;
    private Color c;

    public Retangulo(int x, int y, int width, int height, Color c) {
        this.width = width;
        this.height = height;
        this.c = c;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(c);
        g.fillRect(x, y, width, height);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
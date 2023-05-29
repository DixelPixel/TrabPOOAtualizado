package View;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import static View.Tab.processaCoord;

public class PecaJog extends JComponent {
	private int x;
	private int y;
	private Color cor;
	
	public PecaJog(int x, int y, Color cor) {
		this.x = x;
		this.y = y;
		this.cor = cor;


		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(x+ " "+ y);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});;
	}
	
	 @Override
	    public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 Graphics2D g2d = (Graphics2D) g;
		 
		 g.setColor(Color.black);
		 g.drawOval(x,y,30,30);
		 g.setColor(cor);
		 g.fillOval(x,y,30,30);

		 
		 
		 
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

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}
	 
	 
}

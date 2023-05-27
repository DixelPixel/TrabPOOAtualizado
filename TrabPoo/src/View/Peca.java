package View;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class Peca extends JComponent {
	
		private static final long serialVersionUID = 1L;
		private Color cor;
        private int x;
        private int y;
        private int diametro;

        public Peca(Color cor, int x, int y, int diametro) {
            this.cor = cor;
            this.x = x;
            this.y = y;
            this.diametro = diametro;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(cor);
            g.fillOval(x, y, diametro, diametro);
        }
}


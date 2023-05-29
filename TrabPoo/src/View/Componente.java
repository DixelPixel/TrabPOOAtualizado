package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import static View.Tab.processaCoord;

class Componente extends JComponent {
	public final int LARG_DEFAULT=1200;
    public final int ALT_DEFAULT=700;
    private static final Color BLUE_COLOR = Color.decode("0x6495ED");
    private static final Color RED_COLOR = Color.decode("0xdc143c");

    public Componente(){
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x=e.getX();
                int y=e.getY();
                System.out.println(processaCoord(x,y));

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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
       

        //quadrado vermelho
        g.setColor(RED_COLOR);
        g.fillRect(0,0,294,290);
        g.setColor(Color.black);
        g.drawRect(0,0,294,290);

        //quadrado azul
        g.setColor(BLUE_COLOR);
        g.fillRect(0,410,294,290);
        g.setColor(Color.black);
        g.drawRect(0,410,294,290);



        //quadrado amarelo
        g.setColor(Color.yellow);
        g.fillRect(441,410,294,290);
        g.setColor(Color.black);
        g.drawRect(441,410,294,290);



        //quadrado verde
        g.setColor(Color.green.darker());
        g.fillRect(441,0,294,290);
        g.setColor(Color.black);
        g.drawRect(441,0,294,290);


        //casas do lado esquerdo
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 6; j++){
                if(i == 1 && j >0){
                    g.setColor(RED_COLOR);
                    g.fillRect(49*j,290 + 40*i,49,40);
                }else if(i == 0 && j ==1){
                    g.setColor(RED_COLOR);
                    g.fillRect(49*j,290 + 40*i,49,40);
                }
                g.setColor(Color.black);
                g.drawRect(49*j,290 + 40*i,49,40);
            }
        }


        //casas do lado direito
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 6; j++){
                if(i == 1 && j <5){
                    g.setColor(Color.YELLOW);
                    g.fillRect(441+49*j,290 + 40*i,49,40);
                }else if(i == 2 && j ==4){
                    g.setColor(Color.YELLOW);
                    g.fillRect(441+49*j,290 + 40*i,49,40);
                }
                g.setColor(Color.black);
                g.drawRect(441+49*j,290 + 40*i,49,40);
            }
        }


        //casas de cima
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 3; j++){
                if(j == 1 && i >0){
                    g.setColor(Color.GREEN.darker());
                    g.fillRect(294+ 49*j, 48*i,49,48);
                }else if(i == 1 && j ==2){
                    g.setColor(Color.GREEN.darker());
                    g.fillRect(294+ 49*j, 48*i,49,48);
                }
                g.setColor(Color.black);
                g.drawRect(294+ 49*j, 48*i,49,48);
            }
        }


        //casas de baixo
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 3; j++){
                if(j == 1 && i <5){
                    g.setColor(BLUE_COLOR);
                    g.fillRect(294+ 49*j, 410 + 42*i,49,42);
                }else if(i == 4 && j ==0){
                    g.setColor(BLUE_COLOR);
                    g.fillRect(294, 410 + 42*i,49,42);
                }
                g.setColor(Color.black);
                g.drawRect(294+ 49*j, 410 + 42*i,49,42);
            }
        }

        g.setColor(Color.black);
        g.drawRect(294, 289,147,121);

        
        // triângulos da reta final:
        g.setColor(Color.green.darker());
        g.fillPolygon(new int[] {294,441,367}, new int[]{290,290,350}, 3);
        g.setColor(Color.black);
        g.drawPolygon(new int[] {294,441,367}, new int[]{290,290,350}, 3);


        g.setColor(BLUE_COLOR);
        g.fillPolygon(new int[] {294,441,367}, new int[]{410,410,350}, 3);
        g.setColor(Color.black);
        g.drawPolygon(new int[] {294,441,367}, new int[]{410,410,350}, 3);

        g.setColor(RED_COLOR);
        g.fillPolygon(new int[] {294,294,367}, new int[]{290,410,350}, 3);
        g.setColor(Color.black);
        g.drawPolygon(new int[] {294,294,367}, new int[]{290,410,350}, 3);


        g.setColor(Color.yellow);
        g.fillPolygon(new int[] {441,441,367}, new int[]{290,410,350}, 3);
        g.setColor(Color.black);
        g.drawPolygon(new int[] {441,441,367}, new int[]{290,410,350}, 3);
    }
}
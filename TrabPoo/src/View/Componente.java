package View;

import Model.API;
import Model.Cores;
import Model.Observado;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

class Componente extends JComponent implements Observado {
	private static final long serialVersionUID = 1L;
	public final int LARG_DEFAULT=1200;
    public final int ALT_DEFAULT=700;
    private static final Color BLUE_COLOR = Color.decode("0x6495ED");
    private static final Color RED_COLOR = Color.decode("0xdc143c");
    private ConversorCoordenadas conversor;
    private int pos;
    private List<Observador> observadores;
    private API api;
    
    public Componente(Tab frame){
    	api = API.getInstance();
    	conversor = ConversorCoordenadas.getInstance();

        observadores = new ArrayList<>();
    	registraObservador(frame);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x=e.getX();
                int y=e.getY();
                int coordenadaLinear = conversor.getCoordLinear(x,y);
//                System.out.println(" x: "+ x + " y: "+ y + " indice corrigido: " + coordenadaLinear);
                pos = coordenadaLinear;
                notificaObservadores();
                conversor.imprimeHash();
//                int[] coord = conversor.converteLinearParaCartesiana(coordenadaLinear);
//                System.out.println("Pos corrigidas: x = " + coord[0] + " y = " + coord[1]);
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
        });
    }
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
       

        //quadrado vermelho
        g2d.setColor(RED_COLOR);
        g2d.fillRect(0,0,294,290);
        g2d.setColor(Color.black);
        g2d.drawRect(0,0,294,290);



        //quadrado azul
        g2d.setColor(BLUE_COLOR);
        g2d.fillRect(0,410,294,290);
        g2d.setColor(Color.black);
        g2d.drawRect(0,410,294,290);



        //quadrado amarelo
        g2d.setColor(Color.yellow);
        g2d.fillRect(441,410,294,290);
        g2d.setColor(Color.black);
        g2d.drawRect(441,410,294,290);




        //quadrado verde
        g2d.setColor(Color.green.darker());
        g2d.fillRect(441,0,294,290);
        g2d.setColor(Color.black);
        g2d.drawRect(441,0,294,290);

        int x,y;
        //casas do lado esquerdo
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 6; j++){
                if(i == 1 && j >0){
                    g2d.setColor(RED_COLOR);

                    x = 49*j; y = 290 + 40*i;
                    g2d.fillRect(x, y, 49, 40);
                    conversor.putMapaCoordCart(x+2, y+2);

                }else if(i == 0 && j ==1){
                    g2d.setColor(RED_COLOR);

                    x = 49*j; y = 290 + 40*i;
                    g2d.fillRect(x, y, 49, 40);
                    conversor.putMapaCoordCart(x+2, y+2);

                }
                g2d.setColor(Color.black);

                x = 49*j; y = 290 + 40*i;
                g2d.drawRect(x, y, 49, 40);
                conversor.putMapaCoordCart(x+2, y+2);
            }
        }


        //casas do lado direito
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 6; j++){
                if(i == 1 && j <5){
                    g2d.setColor(Color.YELLOW);

                    x = 441+49*j; y = 290 + 40*i;
                    g2d.fillRect(x, y, 49, 40);
                    conversor.putMapaCoordCart(x+2, y+2);
                }else if(i == 2 && j ==4){
                    g2d.setColor(Color.YELLOW);

                    x = 441+49*j; y = 290 + 40*i;
                    g2d.fillRect(x, y, 49, 40);
                    conversor.putMapaCoordCart(x+2, y+2);
                }
                g2d.setColor(Color.black);

                x = 441+49*j; y = 290 + 40*i;
                g2d.drawRect(x, y, 49, 40);
                conversor.putMapaCoordCart(x+2, y+2);
            }
        }


        //casas de cima
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 3; j++){
                if(j == 1 && i >0){
                    g2d.setColor(Color.GREEN.darker());

                    x = 294+ 49*j; y = 48*i;
                    g2d.fillRect(x, y, 49, 48);
                    conversor.putMapaCoordCart(x+2, y+2);
                }else if(i == 1 && j ==2){
                    g2d.setColor(Color.GREEN.darker());

                    x = 294+ 49*j; y = 48*i;
                    g2d.fillRect(x, y, 49, 48);
                    conversor.putMapaCoordCart(x+2, y+2);
                }
                g2d.setColor(Color.black);

                x = 294+ 49*j; y = 48*i;
                g2d.drawRect(x, y, 49, 48);
                conversor.putMapaCoordCart(x+2, y+2);
            }
        }


        //casas de baixo
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 3; j++){
                if(j == 1 && i <5){
                    g2d.setColor(BLUE_COLOR);

                    x = 294+ 49*j; y = 410 + 42*i;
                    g2d.fillRect(x, y, 49, 42);
                    conversor.putMapaCoordCart(x+2, y+2);
                }else if(i == 4 && j ==0){
                    g2d.setColor(BLUE_COLOR);

                    x = 294+ 49*j; y = 410 + 42*i;
                    g2d.fillRect(x, y, 49, 42);
                    conversor.putMapaCoordCart(x+2, y+2);
                }
                g2d.setColor(Color.black);

                x = 294+ 49*j; y = 410 + 42*i;
                g2d.drawRect(x, y, 49, 42);
                conversor.putMapaCoordCart(x+2, y+2);
            }
        }

        g2d.setColor(Color.black);
        g2d.drawRect(294, 289,147,121);


        // triângulos da reta final:
        g2d.setColor(Color.green.darker());
        g2d.fillPolygon(new int[] {294,441,367}, new int[]{290,290,350}, 3);
        g2d.setColor(Color.black);
        g2d.drawPolygon(new int[] {294,441,367}, new int[]{290,290,350}, 3);


        g2d.setColor(BLUE_COLOR);
        g2d.fillPolygon(new int[] {294,441,367}, new int[]{410,410,350}, 3);
        g2d.setColor(Color.black);
        g2d.drawPolygon(new int[] {294,441,367}, new int[]{410,410,350}, 3);

        g2d.setColor(RED_COLOR);
        g2d.fillPolygon(new int[] {294,294,367}, new int[]{290,410,350}, 3);
        g2d.setColor(Color.black);
        g2d.drawPolygon(new int[] {294,294,367}, new int[]{290,410,350}, 3);


        g2d.setColor(Color.yellow);
        g2d.fillPolygon(new int[] {441,441,367}, new int[]{290,410,350}, 3);
        g2d.setColor(Color.black);
        g2d.drawPolygon(new int[] {441,441,367}, new int[]{290,410,350}, 3);


        desenha_pecas(g2d,0,Color.RED);
        desenha_pecas(g2d,3,Color.BLUE);
        desenha_pecas(g2d,2,Color.ORANGE);
        desenha_pecas(g2d,1,Color.GREEN);

    }

    protected void desenha_pecas(Graphics2D g, int num,Color cor){
        int x,y;
        x = 0;
        y = 0;
        for(int i = 0; i < 4;i++) {
            int pos = api.getPos(num, i);
            if(pos >= 0) {
                if(api.isCasaFinal(num, i)) {
					/* TO-DO */
                    if(cor == Color.RED){
                        x = 49*(pos+1) + 5;
                        y = 335;

                    } else if (cor == Color.GREEN){
                        x = 345;
                        y = 48*(pos+1);

                    } else if (cor == Color.ORANGE) {
                        x = 637 - (pos * 49);
                        y = 335;
                    } else if (cor == Color.BLUE) {
                        x = 345;
                        y = 578 - (pos*42);
                    }

                }
            	else {
            		int[] coord = conversor.converteLinearParaCartesiana(pos);
            		x = coord[0];
            		y = coord[1];
//                System.out.println("Posição da peça "+i + ": " + x + " "+ y);
            	}
            } else{
                if(cor == Color.red){
                    x = 25;
                    y = 25;
                    if( i == 1){
                        x += 200;
                    }else if(i == 2){
                        y+=100;
                    }else if(i ==3){
                        x+=200;
                        y+=100;
                    }
                }else if(cor == Color.green){
                    x = 475;
                    y = 25;
                    if( i == 1){
                        x += 200;
                    }else if(i == 2){
                        y+=100;
                    }else if(i ==3){
                        x+=200;
                        y+=100;
                    }
                }else if(cor == Color.ORANGE){
                    x = 475;
                    y = 420;
                    if( i == 1){
                        x += 200;
                    }else if(i == 2){
                        y+=100;
                    }else if(i ==3){
                        x+=200;
                        y+=100;
                    }
                }else if(cor == Color.blue){
                    x = 25;
                    y = 420;
                    if( i == 1){
                        x += 200;
                    }else if(i == 2){
                        y+=100;
                    }else if(i ==3){
                        x+=200;
                        y+=100;
                    }
                }
            }
            g.setColor(Color.black);
            g.drawOval(x, y, 35, 35);
            g.setColor(cor);
            g.fillOval(x, y, 35, 35);
        }

    }


    @Override
    public void registraObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void removeObservador(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificaObservadores() {
        for(Observador observador : observadores){
            observador.updateCasa(pos);
        }
    }
}
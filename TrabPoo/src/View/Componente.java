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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;

import Controller.Controller;

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
    private Controller controller;
    public boolean retaFinalClick = false;
	/**
	 * essa variavel corJDV serve para evitar que um jogador tenha mais de uma peça
	 * retirada da casa inicial em uma rodada. Ela é uma variavel estática pois não
	 * podemos perder-la de um re-render para outro. 
	 */
    private static String corJDV;
    
    public Componente(Tab frame){
    	api = API.getInstance();
    	conversor = ConversorCoordenadas.getInstance();
        observadores = new ArrayList<>();
    	registraObservador(frame);
    	controller = Controller.getInstance();
    	
    	if(corJDV == null) {
    		corJDV = controller.getNomeCorDaVez();
    	}
    	System.out.printf("teste if rodada %d cor da vez: %s corJDV: %s\n", 
    			controller.getRodada(),controller.getNomeCorDaVez(), corJDV);
    	
    	if(controller.getRodada() == 1 && corJDV == controller.getNomeCorDaVez()) {
    		controller.primeiraRodada();
    		corJDV = controller.getNomeCorProx();
    	}

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x=e.getX();
                int y=e.getY();
                int coordenadaLinear = conversor.getCoordLinear(x,y);
                pos = coordenadaLinear;
                if(pos > -1 && pos <= 5 && x >= 345){
                    retaFinalClick = true;
                }
                notificaObservadores();
                retaFinalClick = false;
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
            	x = 49*j; y = 290 + 40*i;
            	
                if(i == 1 && j >0){
                    g2d.setColor(RED_COLOR);
                    g2d.fillRect(x, y, 49, 40);
                    conversor.putMapaCoordCart(x+2, y+2);

                }else if(i == 0 && j ==1){
                    g2d.setColor(RED_COLOR);
                    g2d.fillRect(x, y, 49, 40);
                    conversor.putMapaCoordCart(x+2, y+2);
                }
                else if(i == 2 && j == 1) {
                	g2d.setColor(Color.black);
                	g2d.fillRect(x, y, 49, 40);
                }
                g2d.setColor(Color.black);

                g2d.drawRect(x, y, 49, 40);
                conversor.putMapaCoordCart(x+2, y+2);
            }
        }


        //casas do lado direito
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 6; j++){
            	x = 441+49*j; y = 290 + 40*i;
                if(i == 1 && j <5){
                    g2d.setColor(Color.YELLOW);
                    g2d.fillRect(x, y, 49, 40);
                    conversor.putMapaCoordCart(x+2, y+2);
                }
                else if(i == 2 && j ==4){
                    g2d.setColor(Color.YELLOW);
                    g2d.fillRect(x, y, 49, 40);
                    conversor.putMapaCoordCart(x+2, y+2);
                }
                else if(i == 0 && j == 4) {
                	g2d.setColor(Color.black);
                	g2d.fillRect(x, y, 49, 40);
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
            	x = 294+ 49*j; y = 48*i;
                if(j == 1 && i >0){
                    g2d.setColor(Color.GREEN.darker());
                    g2d.fillRect(x, y, 49, 48);
                    conversor.putMapaCoordCart(x+2, y+2);
                }
                else if(i == 1 && j ==2){
                    g2d.setColor(Color.GREEN.darker());
                    g2d.fillRect(x, y, 49, 48);
                    conversor.putMapaCoordCart(x+2, y+2);
                }
                else if(i == 1 && j == 0) {
                	g2d.setColor(Color.black);
                	g2d.fillRect(x, y, 49, 48);
                }
                
                g2d.setColor(Color.black);
                g2d.drawRect(x, y, 49, 48);
                conversor.putMapaCoordCart(x+2, y+2);
            }
        }


        //casas de baixo
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 3; j++){
                x = 294+ 49*j; y = 410 + 42*i;
                if(j == 1 && i <5){
                    g2d.setColor(BLUE_COLOR);
                    g2d.fillRect(x, y, 49, 42);
                    conversor.putMapaCoordCart(x+2, y+2);
                }
                else if(i == 4 && j ==0){
                    g2d.setColor(BLUE_COLOR);
                    g2d.fillRect(x, y, 49, 42);
                    conversor.putMapaCoordCart(x+2, y+2);
                }
                else if(i == 4 && j == 2) {
                    g2d.setColor(Color.black);
                    g2d.fillRect(x, y, 49, 42);
                }
                g2d.setColor(Color.black);
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
        desenha_pecas(g2d,1,Color.GREEN);
        desenha_pecas(g2d,2,Color.ORANGE);
        desenha_pecas(g2d,3,Color.BLUE);

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
                    x = 75;
                    y = 75;
                    if( i == 1){
                        x += 100;
                    }else if(i == 2){
                        y+=100;
                    }else if(i ==3){
                        x+=100;
                        y+=100;
                    }
                }else if(cor == Color.green){
                    x = 525;
                    y = 75;
                    if( i == 1){
                        x += 100;
                    }else if(i == 2){
                        y+=100;
                    }else if(i ==3){
                        x+=100;
                        y+=100;
                    }
                }else if(cor == Color.ORANGE){
                    x = 525;
                    y = 470;
                    if( i == 1){
                        x += 100;
                    }else if(i == 2){
                        y+=100;
                    }else if(i ==3){
                        x+=100;
                        y+=100;
                    }
                }else if(cor == Color.blue){
                    x = 75;
                    y = 470;
                    if( i == 1){
                        x += 100;
                    }else if(i == 2){
                        y+=100;
                    }else if(i ==3){
                        x+=100;
                        y+=100;
                    }
                }
            }
            if(pos>=0){
                if(api.retBarricada(retNumJog(cor),i)){
                    g.setColor(Color.GREEN);
                    g.drawOval(x, y, 35, 35);
                    g.setColor(Color.GREEN);
                    g.fillOval(x, y, 35, 35);

                    g.setColor(Color.white);
                    g.drawOval(x + 2, y + 3, 30, 30);
                    g.setColor(Color.white);
                    g.fillOval(x + 2, y + 3, 30, 30);

                    g.setColor(Color.green);
                    g.drawOval(x + 5, y + 6, 25, 25);
                    g.setColor(Color.green);
                    g.fillOval(x + 5, y + 6, 25, 25);
                    continue;
                }else if(api.retAbrigoMaisUmaPeca(retNumJog(cor),i)){
                    g.setColor(Color.GREEN);
                    g.drawOval(x, y, 35, 35);
                    g.setColor(Color.GREEN);
                    g.fillOval(x, y, 35, 35);

                    g.setColor(Color.RED);
                    g.drawOval(x + 2, y + 3, 30, 30);
                    g.setColor(Color.RED);
                    g.fillOval(x + 2, y + 3, 30, 30);
                    continue;
                }
            }
            g.setColor(Color.black);
            g.drawOval(x, y, 35, 35);
            g.setColor(cor);
            g.fillOval(x, y, 35, 35);

        }

    }

    public int retNumJog(Color cor){
        Map<Color, Integer> hashJog = new HashMap<>();
        hashJog.put(Color.RED,0);
        hashJog.put(Color.GREEN,1);
        hashJog.put(Color.ORANGE,2);
        hashJog.put(Color.BLUE,3);
        return hashJog.get(cor);
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
            observador.updateCasa(pos, retaFinalClick);
        }
    }

}

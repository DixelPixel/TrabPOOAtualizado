
package View;

import javax.swing.*;
import Model.Dado;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Model.API;


public class Tab extends JFrame {
    public final int LARG_DEFAULT=1200;
    public final int ALT_DEFAULT=700;
    private static final Color BLUE_COLOR = Color.decode("0x6495ED");
    private static final Color RED_COLOR = Color.decode("0xdc143c");

    public Tab(){
        setSize(LARG_DEFAULT,ALT_DEFAULT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		API api = API.getInstance();



		desenha_pecas(0, Color.RED);
		desenha_pecas(1, Color.GREEN);
		desenha_pecas(2,Color.ORANGE);
		desenha_pecas(3,Color.BLUE);
//		desenha_pecas(2);
//		desenha_pecas(3);

		//Peças Azuis:





		Componente myComponent = new Componente();
		myComponent.setBounds(0,0,800,700);
		getContentPane().add(myComponent);

		Menu menu = new Menu(this);
		menu.setBounds(800,0,500,700);
		getContentPane().add(menu);

		setVisible(true);


    }

	public void desenha_pecas(int num, Color color){
		API api = API.getInstance();
		int x,y;
		x = api.get_x_jog(num,0);
		y = api.get_y_jog(num,0);

		PecaJog peca1v = new PecaJog(x,y,color);
		peca1v.setBounds(peca1v.getX(),peca1v.getY(),peca1v.getX()+40,peca1v.getY()+40);
		getContentPane().add(peca1v);

		x = api.get_x_jog(num,1);
		y = api.get_y_jog(num,1);

		PecaJog peca2 = new PecaJog(x,y,color);
		peca2.setBounds(peca2.getX(),peca2.getY(),peca2.getX()+40,peca2.getY()+40);
		getContentPane().add(peca2);

		x = api.get_x_jog(num,2);
		y = api.get_y_jog(num,2);

		PecaJog peca3 = new PecaJog(x,y,color);
		peca3.setBounds(peca3.getX(),peca3.getY(),peca3.getX()+40,peca3.getY()+40);
		getContentPane().add(peca3);

		x = api.get_x_jog(num,3);
		y = api.get_y_jog(num,3);

		PecaJog peca4 = new PecaJog(x,y,color);
		peca4.setBounds(peca4.getX(),peca4.getY(),peca4.getX()+40,peca4.getY()+40);
		getContentPane().add(peca4);


	}

	public void add_azul(){
		PecaJog peca1 = new PecaJog(25,25,Color.RED);
		peca1.setBounds(peca1.getX(),peca1.getY(),peca1.getX()+40,peca1.getY()+40);
		getContentPane().add(peca1);

		PecaJog peca2 = new PecaJog(25,100,Color.RED);
		peca2.setBounds(peca2.getX(),peca2.getY(),peca2.getX()+40,peca2.getY()+40);
		getContentPane().add(peca2);

		PecaJog peca3 = new PecaJog(100,100,Color.RED);
		peca3.setBounds(peca3.getX(),peca3.getY(),peca3.getX()+40,peca3.getY()+40);
		getContentPane().add(peca3);

		PecaJog peca4 = new PecaJog(100,25,Color.RED);
		peca4.setBounds(peca4.getX(),peca4.getY(),peca4.getX()+40,peca4.getY()+40);
		getContentPane().add(peca4);
	}

	public void add_vermelho(){

	}
    
    private static int getCoordLinear(int i, int j, int cor) {
    	int coord = -1;
    	switch(cor) {
    		case 1: // amarelo
    			if(i == 0) {
    				coord = j + 31;
    			}else if(i == 2) {
    				coord = 43 - j;
    			}else if(i == 1) {
    				if(j == 5) 
    					coord = 37;
    			}
    			break;
    		case 2: // azul
    			if(j == 0) {
    				if(i < 5)
    					coord = 4 - i;
    				else {
    					coord = 51;
    				}
    			}else if(j == 1) {
    				if(i == 5)
    					coord = 50;
    			}else if(j == 2) {
    				coord = 44 + i;
    			}
    			break;
    		case 3: //vermelho
    			if(i == 0) {
    				coord = 12 + j;
    			}else if(i == 2) {
    				coord = 10 - j;
    			}else if(i == 1) {
    				if(j == 0)
    					coord = 11;
    			}
    			break;
    		case 4: //verde
    			if(j == 0) {
    				coord = 23 - i;
    			}else if(j == 2) {
    				coord = 25 + i;
    			}else if(j == 1) {
    				if(i == 0)
    					coord = 24;
    			}
    			break;
    	}
    	return coord;
    }
     
    public static int processaCoord(int x, int y) {
    	int i = -1;
    	int j = -1;
    	int cor = -1;
    	
    	if(x<294 && y>290 && y< 414) {
    		//seção vermelha
    		cor = 3;
    		for(int c1 = 0; c1 < 3; c1++){
                for(int c2 = 0; c2 < 6; c2++){
                	if((x>(49*c2) && x<((49*c2)+49))&&(y > (290 + 40*c1) && (y < (290 + 40*c1 + 40)))) {
                		i = c1;
                		j = c2;
                		System.out.println(c1+ " " + c2);
                		break;
                	}
                }
                
    		}
    	}else if(x>294 && x < 441 && y< 290) {
    		// seção do verde
    		cor = 4;
    		for(int c1 = 0; c1 < 6; c1++){
                for(int c2 = 0; c2 < 3; c2++){
                	if((x>(294+ 49*c2)) && (x<(294+ 49*c2 + 49)) && (y > 48*c1 && (y < (48*c1 + 48)))) {
                		i = c1;
                		j = c2;
                		System.out.println(c1+ " " + c2);
                		break;
                	}
                }
            }
    	}else if((x>441 && x<735 && y<414 && y>290)) {
    		//seção amarela
    		cor =1;
    		for(int c1 = 0; c1 < 3; c1++){
                for(int c2 = 0; c2 < 6; c2++){
                	if((x>(441 + c2*49) && x<((441 + c2*49)+49))&&(y > (290 + 40*c1) && (y < (290 + 40*c1 + 40)))) {
                		i = c1;
                		j = c2;
                		System.out.println(c1+ " " + c2);
                		break;
                	}
                }
                
    		}
    	}else if(x > 294 && x < 441 && y > 410) {
    		//sessão azul
    		cor =2;
    		for(int c1 = 0; c1 < 6; c1++){
                for(int c2 = 0; c2 < 3; c2++){
                	if((x>(294+ 49*c2)) && (x<(294+ 49*c2 + 49)) && (y > 410 + 42*c1 && (y < (410 + 42*c1 + 42)))) {
                		i = c1;
                		j = c2;
                		System.out.println(c1+ " " + c2);
                		break;
                	}
                }
            }
    		
    	}
    	return getCoordLinear(i,j,cor);
    	
    }
 
    public static void main(String[] args){
        Tab frame = new Tab();
//        API api = API.getInstance();

        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}




package View;

import javax.swing.*;

import java.awt.*;
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
		getContentPane().setLayout(null);

		Componente myComponent = new Componente();
		myComponent.setBounds(0,0,800,700);
		getContentPane().add(myComponent);

		Menu menu = new Menu(this);
		menu.setBounds(800,0,500,700);
		getContentPane().add(menu);

		setVisible(true);

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
//                		System.out.println(c1+ " " + c2);
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
//                		System.out.println(c1+ " " + c2);
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
//                		System.out.println(c1+ " " + c2);
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
//                		System.out.println(c1+ " " + c2);
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



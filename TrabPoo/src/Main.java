
import Model.*;

public class Main {
	 public static int getCoordLinear(int i, int j, int cor) {
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
	    		case 3: //vermelho
	    			if(i == 0) {
	    				coord = 12 + j;
	    			}else if(i == 2) {
	    				coord = 10 - j;
	    			}else if(i == 1) {
	    				if(j == 0)
	    					coord = 11;
	    			}
	    		case 4: //verde
	    			if(j == 0) {
	    				coord = 23 - i;
	    			}else if(j == 2) {
	    				coord = 25 + i;
	    			}else if(j == 1) {
	    				if(i == 0)
	    					coord = 24;
	    			}
	    	}
	    	return coord;
	    }
	 
	public static void main(String[]args) {
		API api = API.getInstance();

		api.adicionaJogador("j2", Cores.AZUL);
		api.printaJogador(Cores.AZUL);

		api.colocaCasaInicial(Cores.AZUL);
		api.movePecaJogador(Cores.AZUL, 0,50);
		api.movePecaJogador(Cores.AZUL, 50,1);
		api.movePecaJogador(Cores.AZUL, 0,1);
		api.movePecaJogador(Cores.AZUL, 1,1);
		api.movePecaJogador(Cores.AZUL, 2,1);
		api.movePecaJogador(Cores.AZUL, 3,3);
		api.printTabuleiro();
		System.out.println(getCoordLinear(0,1,3));

	}
}

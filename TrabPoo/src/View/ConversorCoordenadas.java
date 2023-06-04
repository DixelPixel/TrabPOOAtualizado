package View;

import java.util.*;

public class ConversorCoordenadas {
	private static ConversorCoordenadas instance;
	
	private Map<Integer, int[]> mapaCoordCart = new HashMap<Integer, int[]>();

	private ConversorCoordenadas(){

	}
	
	public void putMapaCoordCart(int x, int y) {
//		imprimeHash();
		int pos = getCoordLinear(x,y);
		if(pos == -1){
			return;
		}
		mapaCoordCart.put(pos, new int [] {x, y});
	}

	public int[] converteLinearParaCartesiana(int coordLin) {
//		imprimeHash();
    	return mapaCoordCart.get(coordLin);
    }
	
	public void imprimeHash() {
		System.out.println("Tamanho: " + mapaCoordCart.size());
		for (Map.Entry<Integer, int[]> entry : mapaCoordCart.entrySet()) {
            Integer key = entry.getKey();
            int[] value = entry.getValue();
//            System.out.println(key + " -> x: " + value[0] + " y: " + value[1]);
        }
	}
	
    private int processaCoord(int i, int j, int cor) {
    	int coord = -1;
//    	System.out.println("cor: "+ cor + " i: " + i + " j: "+j);
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
     
    public int getCoordLinear(int x, int y) {
    	int i = -1;
    	int j = -1;
    	int cor = -1;
    	
    	if(x <= 294 && y>=290 && y<= 414) {
    		//seção vermelha
    		cor = 3;
    		for(int c1 = 0; c1 < 3; c1++){
                for(int c2 = 0; c2 < 6; c2++){
                	if((x >= (49*c2) && x <= (( 49*c2 )+49))&&(y >= (290 + 40*c1) && (y <= (290 + 40*c1 + 40)))) {
                		i = c1;
                		j = c2;
//                		System.out.println(c1+ " " + c2);
                		break;
                	}
                }
                
    		}
    	}else if(x >= 294 && x <= 441 && y<= 290) {
    		// seção do verde
    		cor = 4;
    		for(int c1 = 0; c1 < 6; c1++){
                for(int c2 = 0; c2 < 3; c2++){
                	if((x >= (294+ 49*c2)) && (x <= (294+ 49*c2 + 49)) && (y >= 48*c1 && (y <= (48*c1 + 48)))) {
                		i = c1;
                		j = c2;
//                		System.out.println(c1+ " " + c2);
                		break;
                	}
                }
            }
    	}else if((x >= 441 && x <= 735 && y <= 414 && y >= 290)) {
    		//seção amarela
    		cor =1;
    		for(int c1 = 0; c1 < 3; c1++){
                for(int c2 = 0; c2 < 6; c2++){
                	if((x >= (441 + c2*49) && x<=((441 + c2*49)+49))&&(y >= (290 + 40*c1) && (y <= (290 + 40*c1 + 40)))) {
                		i = c1;
                		j = c2;
//                		System.out.println(c1+ " " + c2);
                		break;
                	}
                }
                
    		}
    	}else if(x >= 294 && x <= 441 && y >= 410) {
    		//sessão azul
    		cor =2;
    		for(int c1 = 0; c1 < 6; c1++){
                for(int c2 = 0; c2 < 3; c2++){
                	if((x>=(294+ 49*c2)) && (x<=(294+ 49*c2 + 49)) && (y >= 410 + 42*c1 && (y <= (410 + 42*c1 + 42)))) {
                		i = c1;
                		j = c2;
//                		System.out.println(c1+ " " + c2);
                		break;
                	}
                }
            }
    		
    	}
    	return processaCoord(i,j,cor);
    	
    }

	public static synchronized ConversorCoordenadas getInstance(){
		if(instance == null){
			instance = new ConversorCoordenadas();
		}
		return instance;
	}

}


import Model.API;
import Controller.*;
import Model.Cores;

public class Main {
	public static void main(String[]args) {
		API api = API.getInstance();
		Controller c = Controller.getInstance();
		api.colocaCasaInicial(Cores.VERMELHO);
//		api.movePecaJogador(Cores.VERMELHO,13,51);
//		api.printTabuleiro();
//		api.movePecaJogador(Cores.VERMELHO,0,1);
//		api.printTabuleiro();

		
//		 vermelho
//		c.turno(13, 0);
//		c.turno(13, 50);
//
//		c.turno(50, 1);
//		c.turno(0, 1);
//		c.turno(1, 1);
////		System.out.println(api.getCorDaVez().name());
//
//		// verde
//		c.turno(26, 2);
////		System.out.println(api.getCorDaVez().name());
//
//		// amarelo
//		c.turno(39, 1);
//
//		// azul
//		c.turno(0, 1);
//
//		// vermelho
//		c.turno(18, 9);
		
		
	}
}

package Controller;

import Model.API;
import Model.Cores;

public class Controller {
	private final API api = API.getInstance();
	private int rodada = 1;
	private int n6Seguidos = 0;
	
	public void turno(int casaClicada, int vDado) {
		boolean andou;
		System.out.println("6 seguidos: "+n6Seguidos);
		
		if(rodada == 1 && n6Seguidos == 0) {
			api.colocaCasaInicial();
		}
		
		andou = api.movePecaJogador(casaClicada, vDado);
		if(andou) {
			if(vDado == 6) {
				n6Seguidos++;
//				System.out.println("6 seguidos: "+n6Seguidos);
				if(n6Seguidos == 3) {
					api.voltaCasaInicial();
					
					System.out.println("Uma peca do jogador: "
					+ api.getCorDaVez().name().toLowerCase() + 
					" foi jogada para a casa inicial pois tirou o terceiro 6 seguido!");
					
					n6Seguidos = 0;
					api.atualizaJogadorDaVez();
				}
			}
			else {
				if(api.getCorDaVez() == Cores.AZUL) {
					rodada++;
				}
				n6Seguidos = 0;
				api.atualizaJogadorDaVez();
				
			}
			
		}
		System.out.println("Andou? " + andou + " rodada " + rodada);
		api.printTabuleiro();
	}
}

package Controller;

import Model.API;
import Model.Cores;

public class Controller {
	private final API api = API.getInstance();
	private int rodada = 1;
	
	public void turno(int casaClicada, int vDado) {
		boolean andou;
		if(rodada == 1) {
			api.colocaCasaInicial();
		}
		
		andou = api.movePecaJogador(casaClicada, vDado);
		
		if(andou) {
			if(api.getCorDaVez() == Cores.AZUL){
				rodada++;
			}
			api.atualizaJogadorDaVez();
		}
		System.out.println("Andou? " + andou + " rodada " + rodada);
		api.printTabuleiro();
	}
}

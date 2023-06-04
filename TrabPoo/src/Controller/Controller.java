package Controller;

import Model.API;
import Model.Cores;
import Model.Observado;

import View.Observador;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Controller implements Observado{
	private final API api = API.getInstance();
	private int rodada = 1;
	private int n6Seguidos = 0;
	private static Controller instance = null;
	private List<Observador> observadores;
	
	private Controller() {
		observadores = new ArrayList<Observador>();
	}
	
	public static Controller getInstance() {
		if(instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public boolean turno(int casaClicada, int vDado) {
		boolean andou;
		
		if(rodada == 1 && n6Seguidos == 0 && vDado == 0) {
			return api.colocaCasaInicial();
		}
		
		else if(vDado == 0) {
			return false;
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
					notificaObservadores();
				}
			}
			else {
				if(api.getCorDaVez() == Cores.AZUL) {
					rodada++;
				}
				n6Seguidos = 0;
				api.atualizaJogadorDaVez();
				notificaObservadores();
				
			}
			
		}
		else if(!api.jogadorDaVezTemPecaParaMover()) {
			api.atualizaJogadorDaVez();
			notificaObservadores();
		}
		
		System.out.println("Andou? " + andou + " rodada " + rodada + " Dado: " + vDado);
//		api.printTabuleiro();
		return andou;
	}
	
	public Color getCorDaVez() {
		String cor = api.getCorDaVez().name();
		if(cor == Cores.VERMELHO.name()) {
			return Color.red;
		}
		else if(cor == Cores.VERDE.name()) {
			return Color.green;
		}
		else if(cor == Cores.AMARELO.name()) {
			return Color.yellow;
		}
		else {
			return Color.blue;
		}
	}
	
	public String getNomeCorDaVez() {
		return api.getCorDaVez().name();
	}
	
	public int getRodada() {
		return rodada;
	}

	@Override
	public void registraObservador(Observador observador) {
		// TODO Auto-generated method stub
		observadores.add(observador);
		
	}

	@Override
	public void removeObservador(Observador observador) {
		// TODO Auto-generated method stub
		observadores.remove(observador);
		
	}

	@Override
	public void notificaObservadores() {
		// TODO Auto-generated method stub
		for(Observador ob: observadores) {
			ob.update();
		}
		
	}
	
}

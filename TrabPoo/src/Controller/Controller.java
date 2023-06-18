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
	private boolean jaBotouSaida;
	
	private Controller() {
		observadores = new ArrayList<Observador>();
		jaBotouSaida = false;
	}
	
	public static Controller getInstance() {
		if(instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	public void reinicia(){
		rodada = 1;
		jaBotouSaida = false;
		notificaObservadores();
	}
	
	
	public int getn6() {
		return n6Seguidos;
	}
	
	public void setn6(int n) {
		if(n >= 0 && n <= 3) {			
			n6Seguidos = n;
		}
		else {
			System.out.println("Numero inválido, resetado para 0");
			n6Seguidos = 0;
		}
	}
	
	public void atualizaJogadorDaVez() {
		api.atualizaJogadorDaVez();
		jaBotouSaida = false;
	}
	
	public boolean primeiraRodada() {
		if(rodada == 1 && !jaBotouSaida) {
			jaBotouSaida = true;
			return api.colocaCasaInicial();
		}
		return false;
	}
	
	public boolean turno(int casaClicada, int vDado, boolean click) {
		boolean andou;
		
		if(vDado == 0) {
			return false;
		}
		
		andou = api.movePecaJogador(casaClicada, vDado, click);
		if(andou) {
			if(vDado == 6) {
				n6Seguidos++;
			}
			else {
				if(api.getCorDaVez() == Cores.AZUL) {
					rodada++;
				}
				n6Seguidos = 0;
				atualizaJogadorDaVez();
				notificaObservadores();
			}
		}
		else {
			passaVez(vDado);
		}
		
		if(n6Seguidos == 3) {
			api.voltaCasaInicial();
			System.out.println("Uma peca do jogador: "
			+ api.getCorDaVez().name().toLowerCase() + 
			" foi jogada para a casa inicial pois tirou o terceiro 6 seguido!");
			
			n6Seguidos = 0;
			atualizaJogadorDaVez();
			notificaObservadores();
			return false;
		}
		
		System.out.printf("Andou: %b Rodada: %d Dado: %d QTD 6 seguidos: %d\n",
				andou, rodada, vDado, n6Seguidos);
		notificaObservadores();
		return andou;
	}
	
	/**
	 * verifica se o jogador tem peca para mover e se não tiver, passa a vez,
	 * notificando os observadores
	 */
	public boolean passaVez(int vDado) {
		boolean ret = api.jogadorDaVezTemPecaParaMover();
		if(!ret && vDado != 5) {
			atualizaJogadorDaVez();
			notificaObservadores();
		}
		return !ret;
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
	
	public String getNomeCorProx() {
		Cores cor = api.getCorDaVez();
		if(cor == Cores.VERMELHO) {
			return Cores.VERDE.name();
		}
		else if(cor == Cores.VERDE) {
			return Cores.AMARELO.name();
		}
		else if(cor == Cores.AMARELO) {
			return Cores.AZUL.name();
		}
		else {
			return Cores.VERMELHO.name();
		}
	}
	
	public String getNomeCorDaVez() {
		return api.getCorDaVez().name();
	}
	
	public int getRodada() {
		return rodada;
	}
	
	public void setRodada(int r) {
		rodada = r;
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

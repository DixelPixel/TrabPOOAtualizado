package Model;

import View.Tab;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import Controller.Controller;

public class API {
    private static API instance;
    private Tabuleiro tabuleiro;
    private List<Jogador> jogadores = new ArrayList<Jogador>();
    private Jogador jogadorDaVez;
	private Jogador[] ranking;

    private API(){
        this.tabuleiro = new Tabuleiro();
        jogadores.add(new Jogador("Jogador Vermelho", Cores.VERMELHO));
        jogadores.add(new Jogador("Jogador Verde", Cores.VERDE));
        jogadores.add(new Jogador("Jogador Amarelo", Cores.AMARELO));
        jogadores.add(new Jogador("Jogador Azul", Cores.AZUL));
//        System.out.println(jogadores.get(0));
        jogadorDaVez = jogadores.get(0);
    }
    public static synchronized API getInstance(){
        if(instance == null)
            instance = new API();
        return instance;
    }
    public void resetaJogo(){
		/*Reseta o Tabuleiro e o controller, e cria novos jogadores e peças.*/
		/* TODO: reiniciar jogador da vez no Retângulo do dado @Miguel */
		this.tabuleiro = new Tabuleiro();
		resetaJogadores();
		Controller controller = Controller.getInstance();
		controller.reinicia();
	}
	public void resetaJogadores(){
		jogadores.clear();
		jogadores.add(new Jogador("Jogador Vermelho", Cores.VERMELHO));
		jogadores.add(new Jogador("Jogador Verde", Cores.VERDE));
		jogadores.add(new Jogador("Jogador Amarelo", Cores.AMARELO));
		jogadores.add(new Jogador("Jogador Azul", Cores.AZUL));
		jogadorDaVez = jogadores.get(0);
	}
    public Cores getCorDaVez() {
    	return jogadorDaVez.getCor();
    }

    public Cores getCorProx() {
    	ListIterator<Jogador> li = jogadores.listIterator();
    	while(li.hasNext()) {
    		if(jogadorDaVez.getCor() == Cores.AZUL) {
    			return li.next().getCor();
    		}
    		else if(li.next().getCor() == jogadorDaVez.getCor()) {
    			jogadorDaVez = li.next();
    			return li.next().getCor();
    		}
    	}
    	return null;
    }
    
    public void atualizaJogadorDaVez() {
    	ListIterator<Jogador> li = jogadores.listIterator();
    	while(li.hasNext()) {
    		if(jogadorDaVez.getCor() == Cores.AZUL) {
    			jogadorDaVez = li.next();
    		}
    		else if(li.next().getCor() == jogadorDaVez.getCor()) {
    			jogadorDaVez = li.next();
    			return;
    		}
    	}
    	
    }
	public String printVencedor(){
		Jogador [] rank = Ranking.calculaRanking(jogadores);
		String ranking = "";
		int count = 1;
		for(Jogador jog: rank){
			String jogAtual = jog.getCor().toString();
			int valor = jog.getSomaDist();
			String atual = String.format("%d colocado de cor %s teve soma %d", count, jogAtual,valor);
			ranking = ranking.concat(atual).concat("\n") ;
			count++;
		}
		return ranking;
	}
    
    public void printaJogadorDaVez(){
    	System.out.println(jogadorDaVez);
    }

    public void printaJogador(Cores cor){
        for(Jogador j: jogadores) {
        	if(j.getCor() == cor) {
        		System.out.println(j);
        		break;
        	}
        }
    }
    
    public void colocaCasaInicial(Cores cor){
        for(Jogador j: jogadores) {
        	if(j.getCor() == cor) {
        		tabuleiro.setPecaCasaDeSaida(j.getPeca(-1));
        		break;
        	}
        }
    }
    
    public boolean colocaCasaInicial(){
    	return tabuleiro.setPecaCasaDeSaida(jogadorDaVez.getPeca(-1));
    }
    
	/* retorna true se foi possivel mover a peca e falso caso contrario */
    public boolean movePecaJogador(int casa, int mov){
        return tabuleiro.movePeca(jogadorDaVez, casa, mov);
    }
    
    public boolean movePecaJogador(Cores cor, int casa, int mov){
        for(Jogador j: jogadores) {
        	if(j.getCor() == cor) {
        		return tabuleiro.movePeca(j, casa, mov);
        	}
        }
        return false;
    }

    public void printTabuleiro(){
        System.out.println(this.tabuleiro);
    }
    
    public int rolarDado() {
    	return Dado.rolarDado();
    }
    
    /**
     * Devolver a peça mais a frente do jogador da vez para
     * a casa inicial
     *
     */
    public void voltaCasaInicial() {
    	Peca p = jogadorDaVez.getPecaMaiorPos();
    	Casa c = tabuleiro.getCasas()[p.getPos()];
    	p.foiComida();
		c.removePeca(p);
    }
    
    public boolean jogadorDaVezTemPecaParaMover() {
    	return jogadorDaVez.verificaSeTemPeca(tabuleiro.getCasas());
    }

	/** retorna a posição de 1 das 4 peças do jogador */
    public int getPos(int num, int peca){
        Peca[] pecas = jogadores.get(num).getPecas();
        return pecas[peca].getPos();
    }

    public void adicionaObservadorTabuleiro(Tab tab){
        tabuleiro.registraObservador(tab);
    }
	
	public boolean isCasaFinal(int num, int peca){
		Peca[] pecas = jogadores.get(num).getPecas();
		return pecas[peca].isRetaFinal();
	}

	public boolean retCasaFinal(int pos){
		Peca[] pecas = jogadorDaVez.getPecas();
		if(pos <=5){
			for(Peca peca: pecas){
				if(peca.isRetaFinal() && peca.getPos() == pos){
					return true;
				}
			}
		}
		return false;
	}
	public boolean retBarricada(int jogNum, int pecaNum){
		Peca[] pecas = jogadores.get(jogNum).getPecas();
		Peca pecaEsc = pecas[pecaNum];
		int pos = pecaEsc.getPos();
		if(!pecaEsc.isRetaFinal()){
			Casa casa_peca = tabuleiro.getCasas()[pos];
			return casa_peca.getEfeito() == Efeitos.BARREIRA;
		}
		return false;
	}
	public boolean retAbrigoMaisUmaPeca(int jogNum, int pecaNum){
		Peca[] pecas = jogadores.get(jogNum).getPecas();
		Peca pecaEsc = pecas[pecaNum];
		int pos = pecaEsc.getPos();
		if(!pecaEsc.isRetaFinal()){
			Casa casa_peca = tabuleiro.getCasas()[pos];
			int numPecasCasa = casa_peca.getNumPecas();
			return (casa_peca.getEfeito() == Efeitos.SAIDA || casa_peca.getEfeito() == Efeitos.ABRIGO) && numPecasCasa > 1;
		}
		return false;
	}
	public boolean verificaSeAlgumJogadorVenceu() {
		int cor;
		for(Jogador j: jogadores) {
			if(j.getCor() == Cores.VERMELHO) {
				cor = 0;
			}
			else if(j.getCor() == Cores.AMARELO) {
				cor = 1;
			}
			else if(j.getCor() == Cores.VERDE) {
				cor = 2;
			}
			else {
				cor = 3;
			}
			if(tabuleiro.corVenceu(tabuleiro.getVetCasaRetaFinal(cor)[5])) {
				return true;
			}
		}
		return false;
	}
}

package Model;

import View.Tab;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class API {
    private static API instance;
    private Tabuleiro tabuleiro;
    private List<Jogador> jogadores = new LinkedList<Jogador>();
    private Jogador jogadorDaVez;

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
    
    public Cores getCorDaVez() {
    	return jogadorDaVez.getCor();
    }

//    public void adicionaJogador(String nome, Cores cor){
//        if(jogadores.size()<4)
//            jogadores.add(new Jogador(nome, cor));
//        else{
//            System.out.println("Não é possível adicionar mais que 4 jogadores!");
//        }
//    }
    
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
    	Peca pecas[] = jogadorDaVez.getPecas();
    	boolean ret = false;
    	for(Peca p: pecas) {
    		if(p.getPos() != -1)
    			ret = true;
    	}
    	return ret;
    }

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
	
	public boolean verificaSeAlgumJogadorVenceu() {
		int cor;
		boolean ret = false;
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
			ret = tabuleiro.corVenceu(tabuleiro.getVetCasaRetaFinal(cor)[5]);
		}
		return ret;
	}
}

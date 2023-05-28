package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

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
    
    public void colocaCasaInicial(){
    	tabuleiro.setPecaCasaDeSaida(jogadorDaVez.getPeca(-1));
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
}

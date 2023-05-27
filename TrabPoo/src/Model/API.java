package Model;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class API {
    private static API instance;
    private Tabuleiro tabuleiro;
    private Map<Cores,Jogador> jogadores = new HashMap<Cores, Jogador>();

    private API(){
        this.tabuleiro = new Tabuleiro();
    }
    public static synchronized API getInstance(){
        if(instance == null)
            instance = new API();
        return instance;
    }

    public void adicionaJogador(String nome, Cores cor){
        if(jogadores.size()<4)
            jogadores.put(cor,new Jogador(nome, cor));
        else{
            System.out.println("Não é possível adicionar mais que 4 jogadores!");
        }
    }

    public void printaJogador(Cores cor){
        System.out.println(this.jogadores.get(cor));
    }
    public void colocaCasaInicial(Cores cor){
        tabuleiro.setPecaCasaDeSaida(this.jogadores.get(cor).getPeca(-1));
    }
    public void movePecaJogador(Cores cor, int casa, int mov){
        tabuleiro.movePeca(this.jogadores.get(cor),casa,mov);
    }

    public void printTabuleiro(){
        System.out.println(this.tabuleiro);
    }
    
    public int rolarDado() {
    	return Dado.rolarDado();
    }
}

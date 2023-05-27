package Model;

public class Jogador implements Comparable<Jogador>{
	private final String nome;
	private Peca pecas[];
	private Cores cor;
	
	//soma das distancias no fim do jogo
	private int somaDist;
	
	public int getSomaDist() {
		return somaDist;
	}

	public void setSomaDist() {
		this.somaDist = calculaSomaDist();
	}

	protected Jogador(String nome, Cores cor) {
		this.nome = nome;
		this.cor = cor;
		pecas = new Peca[4];
		for(int i = 0; i < 4; i++) {
			pecas[i] = new Peca(cor);
		}
	}

	protected String getNome() {
		return this.nome;
	}
	
	protected Cores getCor() {
		return cor;
	}
	
	protected Peca[] getPecas() {
		return pecas;
	}
	
	protected Peca getPeca(int pos) {
		/* retorna a peca na posição pos */
		for(int i = 0; i < 4; i++) {
			if(pos == pecas[i].getPos())
				return pecas[i];
		}
		return null; 
		
	}
	
	public String toString() {
		String s = "";
		s += String.format("Jogador %s da cor %s pecas:\n", nome, pecas[0].getCor().name().toLowerCase());
		for(Peca peca: pecas) {
			s += String.format("	Casas percorridas: %d pos: %d\n",
					peca.getCasasPercorridas(),
					peca.getPos()
					);
		}
		return s;
	}
	
	protected boolean verificaSeTemPeca() {
		/*
		 * essa funcao informa se o jogador tem peça no jogo para mover ou se deve tirar
		 * 5 para remover a peça da casa inicial para só então jogar
		 */
		for(Peca p: pecas) {
			if(p.getPos() > -1) {
				return true;
			}
		}
		return false;
	}
	
	private int calculaSomaDist() {
		int somaDist = 0;
		for(Peca peca: this.getPecas()) {
			somaDist+=57-peca.getCasasPercorridas();
		}
		return somaDist;
	}
	public int compareTo(Jogador j) {
		return Integer.compare( j.getSomaDist(), this.getSomaDist());
	}
	
}

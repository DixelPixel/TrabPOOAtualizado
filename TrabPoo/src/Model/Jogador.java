package Model;


class Jogador implements Comparable<Jogador>{
	private final String nome;
	private Peca pecas[];
	private Cores cor;
	
	//soma das distancias no fim do jogo
	private Integer somaDist;
	
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
		somaDist = 0;

		calcula_pos_ini(cor,pecas);

	}

	protected void calcula_pos_ini(Cores cor, Peca[] pecas){
		int x=0;
		int y=0;
		if(cor == Cores.VERMELHO){
			x = 25;
			y = 25;
		}else if(cor == Cores.VERDE){
			x = 475;
			y = 25;
		}else if(cor == Cores.AMARELO){
			x = 475;
			y = 420;
		}else if(cor == Cores.AZUL){
			x = 25;
			y = 420;
		}
		for(int i = 0; i < 4;i++){
			pecas[i]= new Peca(cor);
		}
		pecas[0].setX(x);
		pecas[0].setY(y);

		pecas[1].setX(x+200);
		pecas[1].setY(y);

		pecas[2].setX(x);
		pecas[2].setY(y+200);

		pecas[3].setX(x+200);
		pecas[3].setY(y+200);

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
	
	protected Peca getPeca(int pos, boolean click) {
		/* retorna a peca na posição pos */
		for(int i = 0; i < 4; i++) {
			if(pos == pecas[i].getPos() && (!click && !pecas[i].isRetaFinal()))
				return pecas[i];
			else if(pos == pecas[i].getPos() && (click && pecas[i].isRetaFinal())){
				return pecas[i];
			}
			else if(pos == pecas[i].getPos() && (!click && pecas[i].isRetaFinal())){
				return pecas[i];
			}
		}
		return null; 
		
	}
	
	/**
	 * retorna a peca do jogador que esta mais a frente no tabuleiro
	 */
	protected Peca getPecaMaiorPos() {
		Peca p = null;
		for(int i = 0; i < 4; i++) {
			if(p == null) {
				p = pecas[i];
			}
			else if(p.getPos() < pecas[i].getPos()) {
				p = pecas[i];
			}
		}
		return p;
		
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
	
	/*
	 * essa funcao informa se o jogador tem peça no jogo para mover ou se deve tirar
	 * 5 para remover a peça da casa inicial para só então jogar
	 */
	protected boolean verificaSeTemPeca(Casa [] casas) {
		boolean pNaUltimaCasa, ret = false;
		for(Peca p: pecas) {
			pNaUltimaCasa = p.isRetaFinal() && p.getPos() == 5;
			if(pNaUltimaCasa) {
				ret |= false;
			}
			else if(p.getPos() > -1) {
				if(!casas[Tabuleiro.posFinal(p.getPos()+1)].podeMover(-1)) {
					ret |= false;
				}
				else {
					ret |= true;
				}
			}
		}
		return ret;
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

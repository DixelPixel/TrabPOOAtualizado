package Model;

public class Peca {
	
	private final Cores cor;
	private int pos;
	private int casasPercorridas;
	private boolean retaFinal = false;
	//consideramos que as casas percorridas de cada peça incluem a primeira jogada, ou seja, a saída da casa inicial para a casa de saída.
	
	protected Peca(Cores cor) {
		/*
		 * uma peca é inicializada com posição -1, ou seja, ela não está em posição
		 * alguma no Tabuleiro. Econtra-se na verdade, na casa inicial
		 */
		this.cor = cor;
		pos = -1;
		casasPercorridas = 0;
	}
	
	protected Cores getCor() {
		return cor;
	}
	
	protected int getPos() {
		return pos;
	}
	
	protected int getCasasPercorridas() {
		return casasPercorridas;
	}
	
	protected void setPos(int pos) {
		this.pos = pos;
	}
	
	protected void setCasasPercorridas(int n) {
		casasPercorridas = n;
	}
	
	protected void movePeca(int pos, int qtd) {
		this.pos = pos;
		casasPercorridas += qtd;
	}
	
	protected void movePecaRetaFinal(int qtd, int pos) {
		this.retaFinal = true;
		this.casasPercorridas += qtd;
		this.pos = pos;
	
	}
	
	
	protected boolean isRetaFinal() {
		return retaFinal;
	}

	protected void setRetaFinal(boolean retaFinal) {
		this.retaFinal = retaFinal;
	}

	public String toString() {
		return String.format("Cor: %s posicao: %d casas percorridas: %d", cor.name(), pos, casasPercorridas);
	}
	
	public boolean equals(Object p) {
		/*
		 * nao ha uma identificação unica para a peca estamos considerando duas peças
		 * como iguais se elas possuem a mesma cor e tem a mesma quantidade de casas
		 * percorridas, o que em termos praticos serve perfeitamente
		 */
		Peca peca = (Peca) p;
		return peca.getCor() == this.cor && peca.getCasasPercorridas() == casasPercorridas;
	}
	
	protected void foiComida() {
		/*
		 * uma peça, quando comida "sai do tabuleiro", isto é, tem sua pos como -1 e a
		 * quantidade de casas percorridas passa a ser zero
		 */
		pos = -1;
		casasPercorridas = 0;
	}

	
}

package Model;


public class CasaRetaFinal extends Casa{
	private final Cores cor;

	
	// essa classe é diferente do resto das classes. Isso ocorre pois possui uma cor, e também funcionará de forma diferente do que as outras casas, pois podem até 4 peças.
	protected CasaRetaFinal( Cores cor) {
		super(Efeitos.RETAFINAL);
		this.cor = cor;
	}



	protected Cores getCor() {
		return cor;
	}

	
	public String toString() {
		String s = "";
		s += super.getEfeito().name().toLowerCase();
		for(Peca p: this.getPecas()) {
			s += String.format(" %s ", p);
		}
		return s;
		
	}

	
	

}

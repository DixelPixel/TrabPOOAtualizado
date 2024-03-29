package Model;
import java.util.*;

class Casa {

	private Efeitos efeito;
	private List<Peca> pecas = new LinkedList<Peca>();
	
	protected Casa(Efeitos efeito) {
		this.efeito = efeito;
		// Uma casa pode ter, no maximo 4 pecas, no caso de estar na reta final.
	}
	
	protected Efeitos getEfeito() {
		return efeito;
	}
	public List<Peca> getPecas() {
		return pecas;
	}
	
	public int getNumPecas() {
		return pecas.size();
	}

//	public List<Peca> getPecas(){
//		return pecas;
//	}

	protected void setEfeito() {
		int numPecas = getNumPecas();
		if(efeito != Efeitos.SAIDA && efeito != Efeitos.ABRIGO) {
			if(numPecas == 2 && (pecas.get(0).getCor() == pecas.get(1).getCor())) 
				efeito = Efeitos.BARREIRA;
			else
				efeito = Efeitos.COMUM;
		}
	}
	
	protected void addPeca(Peca peca) {
		pecas.add(peca);
	}
	
	protected Peca getPeca() {
		ListIterator<Peca> li = pecas.listIterator();
		
		if(getNumPecas() > 0)
			return li.next();
		else {
			return null;
		}
		
	}
	
	protected Peca getPeca(Cores cor) {
		ListIterator<Peca> li = pecas.listIterator();
		
		while(li.hasNext()) {
			Peca corrente = li.next();
			if(corrente.getCor() == cor)
				return corrente;
		}
		return null;
	}
	
	
	protected boolean podeMover(int qtdFaltantes) {
		if(efeito == Efeitos.BARREIRA || (getNumPecas() == 2 && (qtdFaltantes == 0 && (efeito == Efeitos.ABRIGO || efeito == Efeitos.SAIDA)))) {
			return false;
		}
		return true;
	}
	
	protected Peca captura(Peca p) {
		Peca pecaNaCasa = getPeca();
		if(pecaNaCasa == null) return null;
		
		if(pecaNaCasa.getCor() == p.getCor()) {
			return null;
		}
		else if(efeito == Efeitos.BARREIRA || efeito == Efeitos.SAIDA || efeito == Efeitos.ABRIGO) {
			return null;
		}
		return pecaNaCasa;
	}
	
	protected void removePeca(Peca p) {
		
		/*
		 * As peças de mesma cor que estão na mesma casa sao "a mesma coisa", então é so
		 * remover a primeira peça encontrada da cor passada como paramentro
		 */

		pecas.remove(p);
	}
	
	public String toString() {
		String s = "";
		s += efeito.name().toLowerCase();
		for(Peca p: pecas) {
			s += String.format(" %s ", p);
		}
		return s;
		
	}

}


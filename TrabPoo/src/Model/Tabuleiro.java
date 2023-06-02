package Model;

class Tabuleiro {
//	quantidade de casas do vetor
	private static final int QTDCASAS = 52;
//	o vetor de casas tem seu começo na casa de saida da peca azul
	private Casa casas[];
	private CasaRetaFinal[][] casasRetaFinal;
	
	protected Tabuleiro() {
		casas = new Casa[QTDCASAS];
		for(int i = 0; i < QTDCASAS; i++) {
		
			if(i == 0) {
				//casa de saida do azul
				casas[i] = new Casa(Efeitos.SAIDA);
			}
			else if(i == 9) {
				//abrigo 1
				casas[i] = new Casa(Efeitos.ABRIGO);
			}
			else if(i == 13) {
				//casa de saida do vermelho
				casas[i] = new Casa(Efeitos.SAIDA);
			}
			else if(i == 22) {
				//abrigo 2
				casas[i] = new Casa(Efeitos.ABRIGO);
			}
			else if(i == 26) {
				//casa de saida do verde
				casas[i] = new Casa(Efeitos.SAIDA);
			}
			else if(i == 35) {
				//abrigo 3
				casas[i] = new Casa(Efeitos.ABRIGO);
			}
			else if(i == 39) {
				//casa de saida do amarelo
				casas[i] = new Casa(Efeitos.SAIDA);
			}
			else if(i == 48) {
				//abrigo 4
				casas[i] = new Casa(Efeitos.ABRIGO);
			}
			else if(i >= 52) {
				casas[i] = new Casa(Efeitos.RETAFINAL);
			}
			else {
				casas[i] = new Casa(Efeitos.COMUM);
			}
		}
		int n = 0;
		casasRetaFinal = new CasaRetaFinal[4][6];
		for(Cores cor: Cores.values()) {
			for(int j = 0; j < 6; j++) {
				casasRetaFinal[n][j] = new CasaRetaFinal(cor);
			}
			n++;
		}
	}
	public Casa[] getCasas() {
        return casas;
    }
	
	public String toString() {
		String s = "";
		for(int i = 0; i < QTDCASAS; i++) {
			s += String.format("Casa %d é %s\n",i, casas[i]);
		}
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 6; j++) {
				s+= String.format("Casa posição %d %s\n", j, casasRetaFinal[i][j]);
			}
		}
		return s;
	}
	
	public boolean setPecaCasaDeSaida(Peca peca) {
		/*
		 * essa função poe a peca passada como parametro na sua casa de saida dependendo
		 * da sua cor e retorna se isso pôde ser feito ou nao
		 */
		
		if(peca == null) return false;
		Cores corP = peca.getCor();
		switch(corP) {
		case AZUL:
			//a cor azul tem sua casa de saida no indice 0
			if(casas[0].getPeca(corP) != null)
				/*
				 * nesse caso ja existe uma peça dessa cor na casa de saida, então, não devemos
				 * tira-la da casa inicial
				 */
				return false;
			peca.setPos(0);
			peca.setCasasPercorridas(1);
			casas[0].addPeca(peca);
			return true;
		case VERMELHO:
			//a cor vermelha tem sua casa de saida no indice 13
			if(casas[13].getPeca(corP) != null)
				return false;
			peca.setPos(13);
			peca.setCasasPercorridas(1);
			casas[13].addPeca(peca);
			return true;
		case VERDE:
			//a cor verde tem sua casa de saida no indice 26
			if(casas[26].getPeca(corP) != null)
				return false;
			peca.setPos(26);
			peca.setCasasPercorridas(1);
			casas[26].addPeca(peca);
			return true;
		case AMARELO:
			//a cor amarela tem sua casa de saida no indice 39
			if(casas[39].getPeca(corP) != null)
				return false;
			peca.setPos(39);
			peca.setCasasPercorridas(1);
			casas[39].addPeca(peca);
			return true;
		}
		return false;
	}
	
	public int posFinal(int pos) {
		/* o objetivo dessa função é corrigir os indices de acesso ao vetor de casas */
		int posAux = pos;
		if(posAux > Tabuleiro.QTDCASAS - 1) {
			posAux -= 52;
		}
		else {
			return posAux;
		}
		return posAux;
	}
	
	private boolean verificaCasaComBarreira(Jogador j) {
		/*
		 * essa função vai auxiliar a saber se um jogador possui peças que 
		 * formam uma barreira.
		 */
		Casa casaAtual;
		for(Peca p: j.getPecas()) {
			if(p.getPos() > -1) {
				casaAtual = casas[p.getPos()];
				if(casaAtual.getEfeito() == Efeitos.BARREIRA)
					return true;
			}
		}
		return false;
	}
	private void verificaRetaFinal(Peca peca, int vDado) {
		if(peca.getCasasPercorridas()<=51) {
			Casa casaAtualPeca = casas[peca.getPos()];
			casaAtualPeca.removePeca(peca);
			casaAtualPeca.setEfeito();
		}
		/* se passar para as casas finais, então continua a função  */
		
		if(peca.getCasasPercorridas()+vDado > 51) {
			/* se passar para as casas finais, então continua a função  */
			movePecaRetaFinal(peca,vDado);
		}
		
	}
	private CasaRetaFinal[] procuraCasaRetaFinal( Peca peca) {
		int count = 0;
		for(Cores corPeca: Cores.values()) {
			if(peca.getCor() == corPeca) {
				return casasRetaFinal[count];
			}
			count++;
		}
		
		return null;
	}
	private void movePecaRetaFinal(Peca p, int vDado) {
		
		int posAtual, restante, casasPercorridas;
		
		casasPercorridas = p.getCasasPercorridas();
		if(!p.isRetaFinal()) {
			posAtual = p.getCasasPercorridas() - 51;
			restante = 57 - casasPercorridas;
		}
		else {
			posAtual = p.getPos();
			restante = 5 - posAtual;
		}
		CasaRetaFinal[] retaCor = procuraCasaRetaFinal(p);
				
		if(vDado <= restante) { 
			p.movePecaRetaFinal(vDado,posAtual+vDado);
			retaCor[p.getPos()].addPeca(p);
			if(posAtual > -1)
				retaCor[posAtual].removePeca(p);
		}
		
		
	}
	
	private void executaOperacoesParaMover(Peca peca, int vDado) {
		/*
		 * essa é a função responsavel por efetivamente fazer as operações nas
		 * estruturas para mover uma peça
		 */
		
		verificaRetaFinal(peca, vDado);
		
		if(peca.isRetaFinal() == false) {
			Casa casaAtualPeca, casaFinal; 
			Peca pecaComida;
			int pos;
			
			/* pos tem o indice correto no qual a peça deve ser deslocada */
			pos = posFinal(vDado+peca.getPos());
			casaAtualPeca = casas[peca.getPos()];
			casaFinal = casas[pos];
			
			/*
			 * a funcão captura retorna nulo caso comer uma peça não seja uma opção, seja
			 * por não ter peça na casa destino ou por ser abrigo
			 */
			pecaComida = casaFinal.captura(peca);
			if(pecaComida != null) {
				pecaComida.foiComida();
				casaFinal.removePeca(pecaComida);
			}
			
			
			peca.movePeca(pos, vDado);
			casaFinal.addPeca(peca);
			casaAtualPeca.removePeca(peca);
			
			/*
			 * essas duas linhas servem para verificar se o efeito da casas afetadas mudou e
			 * se for o caso, mudar tal efeito
			 */
			
			casaFinal.setEfeito();
			casaAtualPeca.setEfeito();
		}
	}
	
	private boolean corVenceu(Casa ultima) {
		return ultima.getPecas().size()==4;
	}
	
	private boolean movePecaParaCasaPermitida(Peca peca, int vDado) {
	
		
		int i, pos = 1;
		
		for(i = 1; i < vDado; i++, pos++) {
			int idx = posFinal(i+peca.getPos());
			Casa casaAtual = casas[idx];
			if(!casaAtual.podeMover()) {
				/*
				 * a questão aqui é mover a peça para a casa imediatamente anterior a uma
				 * barreira caso exista uma. Se ele ja moveu, o loop acabou.
				 */
				pos = i-1;
				break;
			}
		}
		if(pos == 0) {
			/*
			 * nesse caso, tem um impedimento para movimentação na casa imediatamente
			 * posterior a casa atual da peca, e portanto essa peca nao pode ser mexida.
			 */
			return false;
		}
		else {
			executaOperacoesParaMover(peca, pos);
			return true;
		}
	}
	
	public boolean movePeca(Jogador j, int idxCasa, int vDado) {
		/*
		 * essa é a função responsavel por decidir se o movimento da peça na casa de
		 * indice passado pode ser feita o idxCasa = -1 significa que ele quer tirar
		 * uma peça da casa inicial
		 */
		if(j.verificaSeTemPeca()){
			/*
			 * verifica se o jogador tem peça para mover, ou seja, se alguma peça dentre as
			 * dele tem pos > -1
			 */
			if(idxCasa == -1) {
				/*
				 * o jogador já tem peça em jogo e, portanto, para tirar uma peça da casa
				 * inicial tem que tirar 5 no dado
				 */
				if(vDado == 5) {
					if(setPecaCasaDeSaida(j.getPeca(idxCasa)))
						/*nesse caso, o jogador saiu da casa inicial com uma peça, ou seja,
						jogada feita*/
						return true;
				}
			}
			
			else if(vDado == 6 && verificaCasaComBarreira(j)) {
			/* 
			 * se o jogador tiver uma barreira e tirou 6 no dado ele tem que mover uma
			 * peça que está na barreira
			 */
				
				if(casas[idxCasa].getEfeito() == Efeitos.BARREIRA) {
					/* 
					 * nesse caso, o jogador está tentando mover uma peça que está
					 * na barreira então podemos move-la
					 */
					return movePecaParaCasaPermitida(j.getPeca(idxCasa), vDado);
				}
				else
					/*
					 * se o jogador está tentando mover uma peça em um casa que 
					 * não é barreira, não acontece nada
					 */
					return false;
			}else {
				if(vDado == 5) {
					if(setPecaCasaDeSaida(j.getPeca(-1))) {
						/*
						 * nesse caso o jogador saiu com uma peça da casa inicial e
						 * essa foi a sua jogada
						 */
						return true;
					}
				}
				if(j.getPeca(idxCasa) != null) {
					return movePecaParaCasaPermitida(j.getPeca(idxCasa), vDado);
				}
			}
		}else {
			if(vDado == 5) {
				setPecaCasaDeSaida(j.getPeca(-1));
				return true;
			}
		}
		return false;
	}
}

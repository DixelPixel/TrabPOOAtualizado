package Model;

import java.util.Arrays;

class Ranking {
	

	protected static Jogador[] calculaRanking(Jogador[] jogadores) {
		for(Jogador jogador: jogadores) {
			jogador.setSomaDist();
		}
		Arrays.sort(jogadores);
		return jogadores;
	}
}

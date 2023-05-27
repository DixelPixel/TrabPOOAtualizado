package Model;

import java.util.Arrays;

public class Ranking {
	

	protected static Jogador[] calculaRanking(Jogador[] jogadores) {
		for(Jogador jogador: jogadores) {
			jogador.setSomaDist();
		}
		Arrays.sort(jogadores);
		return jogadores;
	}
}

package Model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class Ranking {
	

	protected static Jogador[] calculaRanking(List<Jogador> jogadores) {
		Jogador [] arrJog = jogadores.toArray(new Jogador[4]);
		for(Jogador jogador: arrJog) {
			jogador.setSomaDist();
		}
		Arrays.sort(arrJog, Collections.reverseOrder());
		return arrJog;
	}
}

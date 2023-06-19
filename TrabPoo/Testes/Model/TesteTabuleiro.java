package Model;
import Model.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class TesteTabuleiro {

//	testando se a classe podem ser inicializada sem erro
 	@Test
 	public void testa_criar_tabuleiro() {
		Tabuleiro t = new Tabuleiro();
		assertNotNull("O tabuleiro criado é nulo",t);
 	}

// 	Testar se nenhuma casa do tabuleiro possui um efeito null
 	@Test
 	public void testa_efeitos_casas() {
 		Tabuleiro t = new Tabuleiro();
 		for(Casa c: t.getCasas()) {
 			if (c.getEfeito() == null) {
 				fail("tem uma casa com efeito nulo");
 			}
 		}
 	}

// 	Testar se o tabuleiro possui as 52 casas
 	@Test
 	public void testa_num_casas() {
 		Tabuleiro t = new Tabuleiro();
 		assertTrue("O tabuleiro não possui 52 casas",t.getCasas().length == 52);
 	}

// 	Testar se cada cor de peça inicializa na posição certa
 	@Test
 	public void testa_peca_posicionada() {
 		Tabuleiro t = new Tabuleiro();

 		Peca p1 = new Peca(Cores.AMARELO);
 		Peca p2 = new Peca(Cores.AZUL);
 		Peca p3 = new Peca(Cores.VERDE);
 		Peca p4 = new Peca(Cores.VERMELHO);

 		t.setPecaCasaDeSaida(p1);
 		t.setPecaCasaDeSaida(p2);
 		t.setPecaCasaDeSaida(p3);
 		t.setPecaCasaDeSaida(p4);

 		assertTrue("A peça não foi inicializada na posição certa",p1.getPos() == 30
 				|| p2.getPos() == 0
 				|| p3.getPos() == 26
 				|| p4.getPos() == 13
 				);
 	}

//	Testando se a peça é retirada da casa inicial corretamente
 	@Test
 	public void testa_movimentoInicial_peca() {

 		Tabuleiro t = new Tabuleiro();
 		Jogador j1 = new Jogador("Teste",Cores.AMARELO);

 		t.movePeca(j1, -1, 5,false);

 		assertTrue("O movimento não funcionou corretamente",j1.getPecas()[0].getPos() != -1);
 	}

// 	Testando se uma peça recém-inicializada se move normalmente
 	@Test
 	public void testa_movimento_peca() {
 		Tabuleiro t = new Tabuleiro();
 		Jogador j1 = new Jogador("Teste",Cores.AMARELO);
 		t.movePeca(j1, -1, 5, false);
 		int pos_inicial = j1.getPecas()[0].getPos();

 		t.movePeca(j1,pos_inicial,3, false);
 		assertTrue("A peça não se moveu corretamente",j1.getPecas()[0].getPos() != pos_inicial);
 	}

// 	Testando se o jogador ultrapassa alguma casa com barreira
 	@Test
 	public void testa_ultrapassar_barreira() {
// 		inicializa o tabuleiro, cria os jogadores e move inicializa 2 peças do jogador 1
 		Tabuleiro t = new Tabuleiro();
 		Jogador j1 = new Jogador("Teste",Cores.AMARELO);
 		Jogador j2 = new Jogador("Teste2",Cores.VERDE);
 		t.setPecaCasaDeSaida(j1.getPeca(-1, false));
 		t.movePeca(j1, 39, 3, false);// é preciso mover uma peça da casa de saída antes de colocar outra
 		t.movePeca(j1, -1, 5, false);
 		t.setPecaCasaDeSaida(j2.getPeca(-1,false));

// 		movendo as peças do j1 para fora da casa inicial (2 posições na frente) e definindo como
// 		barreira
 		t.movePeca(j1, 39, 3,false);

// 		colocando a peça do j2 atrás e tenta fazer a peça do jogador 2 ultrapassar a barreira
 		t.movePeca(j2, 26, 14,false);
 		t.movePeca(j2,40, 4,false);
 		assertTrue("O jogador ultrapassou a barreira",j2.getPeca(41,false) != null );

 	}

// 	Testar se o jogador consegue ir para um índice maior que 52 (fora do tabuleiro)
 	@Test
 	public void testa_peca_fora_tabuleiro() {
		Tabuleiro t = new Tabuleiro();
 		Jogador j = new Jogador("Teste",Cores.AMARELO);

 		t.setPecaCasaDeSaida(j.getPeca(-1,false));
 		t.movePeca(j, 39 , 13,false);
 		assertTrue("A peça do jogador saiu do tabuleiro",j.getPeca(0,false) != null);

 	}

// 	Testando se o peão é capturado
 	@Test
 	public void teste_peao_come() {
 		Tabuleiro t = new Tabuleiro();
 		Jogador j1 = new Jogador("Teste",Cores.AZUL);
 		Jogador j2 = new Jogador("Teste",Cores.VERMELHO);

 		t.setPecaCasaDeSaida(j1.getPeca(-1,false));
 		t.setPecaCasaDeSaida(j2.getPeca(-1,false));
 		t.movePeca(j2, 13, 1,false);
 		t.movePeca(j1, 0, 13,false);
 		assertTrue("A peça não é capturado quando cai na mesma casa", j2.verificaSeTemPeca(t.getCasas()));
 	}

}

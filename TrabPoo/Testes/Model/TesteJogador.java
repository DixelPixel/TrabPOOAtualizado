package Model;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.*;

public class TesteJogador {

//Testes da classe Jogador
	
//	Testando se a classe podem ser inicializada sem erro
	@Test
	public void teste_criar_jogador() {
		Jogador j = new Jogador("j1",Cores.AMARELO);
		assertNotNull("O jogador criado é nulo",j);
	}
	
//	Testando se o jogador possui quatro peças
	@Test
	public void teste_qnt_pecas() {
		Jogador j = new Jogador("j1",Cores.AMARELO);
		assertTrue("O jogador não possui exatamente 4 peças",j.getPecas().length == 4);
	}
	
//	Testando se todas as peças do jogador possuem a cor do jogador.
	@Test
	public void teste_cor_pecas() {
		Jogador j = new Jogador("j1",Cores.AMARELO);
		for(int i = 0;i < j.getPecas().length;i++) {
			if ( j.getPecas()[i].getCor() != j.getCor() ) {
				fail("A " + Integer.toString(i+1) + "° peça é de outra cor");
			}
		}
		assertTrue(true);
	}
	

}

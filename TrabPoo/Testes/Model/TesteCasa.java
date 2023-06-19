package Model;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.*;

public class TesteCasa {

//Testes referente a classe Casa

//	Testando se a classe podem ser inicializada sem erro
 	@Test
 	public void teste_criar_casa(){
 		Casa c = new Casa(Efeitos.COMUM);
 		assertNotNull("A casa criada é nula",c);
 	}

// 	Testando se as peças são adicionadas corretamente
 	@Test
 	public void teste_adiciona_peca() {
 		Casa c = new Casa(Efeitos.BARREIRA);
 		Peca p = new Peca(Cores.VERDE);
 		c.addPeca(p);
 		assertTrue("A peça não foi adicionada",c.getNumPecas() == 1);
 	}

// 	Testando se as peças são removidas corretamente
 	@Test
 	public void teste_remove_peca() {
 		Casa c = new Casa(Efeitos.BARREIRA);
 		Peca p1 = new Peca(Cores.VERDE);
 		Peca p2 = new Peca(Cores.VERDE);
 		Peca p3 = new Peca(Cores.VERDE);
 		c.addPeca(p1);
 		c.addPeca(p2);
 		c.addPeca(p3);
 		if(c.getNumPecas() == 3) {
 			c.removePeca(p1);
 			c.removePeca(p2);
 			c.removePeca(p3);
 		}
 		assertTrue("Alguma das peças não foi removida",c.getNumPecas() == 0);

 	}

// 	Testando se a casa vazia vira comum
 	@Test
 	public void teste_efeito_casa_vazia() {
 		Casa c = new Casa(Efeitos.BARREIRA);
 		c.setEfeito();
 		assertTrue("A casa vazia não se tornou comum",c.getEfeito() == Efeitos.COMUM);
 	}

// 	Testando se casa de saída perde seu efeito
 	@Test
 	public void teste_efeito_efeito() {
 		Casa c = new Casa(Efeitos.ABRIGO);
 		c.setEfeito();
 		assertTrue("abrigo foi transformado",c.getEfeito() == Efeitos.ABRIGO);
 	}

// 	Testando se casa de abrigo perde seu efeito
 	@Test
 	public void teste_efeito_saida() {
 		Casa c = new Casa(Efeitos.SAIDA);
 		c.setEfeito();
 		assertTrue("abrigo foi transformado",c.getEfeito() == Efeitos.SAIDA);
 	}

// 	Testando se uma peça pode ser capturada em uma casa que não deveria
 	@Test
 	public void teste_captura_barreira() {
 		Casa c = new Casa(Efeitos.BARREIRA);

 	}
// 	Testando se uma peça pode ser capturada em uma casa que não deveria
 	@Test
 	public void teste_captura_outra_peca() {
 		Casa c = new Casa(Efeitos.COMUM);
 		Peca p1 = new Peca(Cores.AMARELO);
 		Peca p2 = new Peca(Cores.VERDE);
 		c.addPeca(p1);
 		assertTrue("A peça não foi capturada",c.captura(p2).equals(p1));

 	}







}

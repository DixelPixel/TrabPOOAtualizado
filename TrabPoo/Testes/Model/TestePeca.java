//package Model;
//
//
//import static org.junit.Assert.*;
//import org.junit.Test;
//
//import Model.*;
//
//public class TestePeca {
// //Testes referentes a classe Peca
//
////	Testando se a classe podem ser inicializada sem erro
// 	@Test
// 	public void testa_criar_peca() {
// 		Peca p = new Peca(Cores.AMARELO);
// 		assertNotNull("A peça criada é nula",p);
// 	}
//
////	Testando o método equals
// 	@Test
// 	public void testa_equals() {
// 		Peca p1 = new Peca(Cores.AMARELO);
// 		Peca p2 = new Peca(Cores.AMARELO);
// 		assertTrue("O método equals não está funcionando",p1.equals(p2));
// 	}
//
//// 	Testando se a peça retorna pro começo após ser comida
// 	@Test
// 	public void testa_comida() {
// 		Peca p = new Peca(Cores.VERDE);
// 		p.movePeca(20, 3);
// 		p.foiComida();
// 		assertTrue("A peça não retorna para o começo se for comida",p.getPos() == -1 || p.getCasasPercorridas() == 0);
//
// 	}
//
//
//
//
//}

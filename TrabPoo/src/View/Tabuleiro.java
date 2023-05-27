package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;

public class Tabuleiro {  
	public static void main(String[] args) {  
		JFrame janela =new JFrame();
		          
//		Criando os botões
		JButton b_NovoJogo=new JButton("Novo Jogo");
		JButton b_CarregarJogo=new JButton("Carregar Jogo");
		JButton b_SalvarJogo=new JButton("Salvar Jogo");
		JButton b_LancarDados=new JButton("Lançar Dados"); 
		
//		Definindo tamanho dos botões
		b_NovoJogo.setBounds(850,50,300, 50);
		b_CarregarJogo.setBounds(850,120,300, 50);
		b_SalvarJogo.setBounds(850,190,300, 50);
		b_LancarDados.setBounds(850,400,300, 50);
		          
//		Adicionando os botões na janela
		janela.add(b_NovoJogo); 
		janela.add(b_CarregarJogo); 
		janela.add(b_SalvarJogo); 
		janela.add(b_LancarDados); 
		
		Image i;
		try {
			i= ImageIO.read(new File("b_dama.gif"));
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
//		Configurando a janela          
		janela.setSize(1200,700);
		janela.setLayout(null);  
		janela.setVisible(true);
	}  
}  

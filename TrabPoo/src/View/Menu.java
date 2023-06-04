package View;

import Model.API;
import Controller.Controller;
import Model.Observado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Menu extends JComponent implements Observado, Observador {
    API api = API.getInstance();
    public Controller controller;
    private List<Observador> observadores;
    int vDado;
    private String corJogadorDaVez;
    private Tab frame;
    private JLabel JogadorAtual;

    public Menu(Tab frame){
    	this.frame = frame;
        observadores = new ArrayList<>();
        controller = Controller.getInstance();
        corJogadorDaVez = controller.getNomeCorDaVez();
        registraObservador(frame);
        //		Criando os botões
        JButton b_NovoJogo=new JButton("Novo Jogo");
        JButton b_CarregarJogo=new JButton("Carregar Jogo");
        JButton b_SalvarJogo=new JButton("Salvar Jogo");
        JButton b_LancarDados=new JButton("Lançar Dados");

//		Definindo tamanho dos botões
        b_NovoJogo.setBounds(800,50,325, 50);
        b_CarregarJogo.setBounds(800,120,325, 50);
        b_SalvarJogo.setBounds(800,190,325, 50);
        b_LancarDados.setBounds(800,350,325, 50);


//		Texto falando do jogador e do dado
        JLabel t_AJogar = new JLabel("À Jogar:");
        JLabel t_Resultado = new JLabel("Resultado:");
        this.JogadorAtual= new JLabel(controller.getNomeCorDaVez());

//		Adicionando os elementos na janela
        frame.add(b_NovoJogo);
        frame.add(b_CarregarJogo);
        frame.add(b_SalvarJogo);
        frame.add(b_LancarDados);
        frame.add(t_Resultado);
        frame.add(t_AJogar);
        frame.add(JogadorAtual);
        

        t_AJogar.setBounds(920, 260, 300, 50);
        t_AJogar.setFont(new Font("Arial", Font.BOLD, 24));
        JogadorAtual.setBounds(930, 290, 300, 50);
        JogadorAtual.setFont(new Font("Arial", Font.BOLD, 24));
        t_Resultado.setBounds(900, 410, 300, 50);
        t_Resultado.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel dado = new JLabel();

        dado.setBounds(907, 490, 100, 100);
        dado.setIcon(new ImageIcon(System.getProperty("user.dir") + "/Imagens/Dado6.png"));
        frame.add(dado);

        // Adicione um ActionListener ao botão Novo Jogo
        b_NovoJogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String j_atual = "você";
            }
        });

        // Adicione um ActionListener ao botão Carregar Jogo
        b_CarregarJogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int escolha = fileChooser.showOpenDialog(frame);
//	                Se o usuário tiver selecionado um arquivo ao invés de cancelar a ação
                if (escolha == JFileChooser.APPROVE_OPTION) {
                    System.out.println("jogo carregado");
                }
            }
        });

        // Adicione um ActionListener ao botão Salvar Jogo
        b_SalvarJogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Adicione um ActionListener ao botão Lançar Dados
        b_LancarDados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vDado = api.rolarDado();
                String projectPath = System.getProperty("user.dir");
                String dadoPngPath = projectPath + "/Imagens/Dado" + vDado + ".png";
                ImageIcon iconeDado = new ImageIcon(dadoPngPath);
                dado.setIcon(iconeDado);
                notificaObservadores();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(907, 540, 120, 120);
    }


    @Override
    public void registraObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void removeObservador(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificaObservadores() {
        for(Observador observador : observadores){
            observador.update(vDado);
        }
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		corJogadorDaVez = controller.getNomeCorDaVez();
		frame.remove(JogadorAtual);
		JogadorAtual = new JLabel(corJogadorDaVez);
		JogadorAtual.setBounds(930, 290, 300, 50);
	    JogadorAtual.setFont(new Font("Arial", Font.BOLD, 24));
		frame.add(JogadorAtual);
		frame.repaint();
		
	}

	@Override
	public void update(int dado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCasa(int casa) {
		// TODO Auto-generated method stub
		
	}
}

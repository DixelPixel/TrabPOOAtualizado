
package View;

import javax.swing.*;

import java.awt.*;
import Model.API;
import Controller.*;

public class Tab extends JFrame implements Observador {

	private static final long serialVersionUID = 1L;
	public final int LARG_DEFAULT=1200;
    public final int ALT_DEFAULT=700;
    private Componente tabuleiro;
    int vDado = 0;
    int pos;
    private Controller controller;
    private API api;

    public Tab(){
    	
        setSize(LARG_DEFAULT,ALT_DEFAULT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		
		controller = Controller.getInstance();
        api = API.getInstance();
        Menu menu = new Menu(this);
        tabuleiro = new Componente(this);
        
        controller.registraObservador(menu);
        controller.registraObservador(this);
        menu.getComponenteInput().registraObservador(this);

		tabuleiro.setBounds(0,0,800,700);
		getContentPane().add(tabuleiro);

		menu.setBounds(800,0,500,700);
		getContentPane().add(menu);

        api.adicionaObservadorTabuleiro(this);

		setVisible(true);
    }
    
    public void encerraJogo(){
        JOptionPane.showMessageDialog(null,api.printVencedor(),"Fim de Jogo",JOptionPane.INFORMATION_MESSAGE);

        int escolha = JOptionPane.showConfirmDialog(null,"Deseja Recomeçar o jogo?","Iniciar novo Jogo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(escolha == JOptionPane.YES_OPTION){
            api.resetaJogo();
            update();
        }else{
            System.exit(0);
        }
    }

    @Override
    public void update() {
    	
//    	vDado = 0;
        getContentPane().remove(tabuleiro);
        Componente aux = new Componente(this);
        tabuleiro = aux;
        tabuleiro.setBounds(0,0,800,700);
        getContentPane().add(tabuleiro);
        
        getContentPane().repaint();
    }

    @Override
    public void update(int dado) {
       this.vDado = dado;
       System.out.println("Valor tirado no dado "+vDado);
       controller.passaVez(dado);
    }

    @Override
    public void updateCasa(int casa, boolean click) {
//        this.pos = casa;
        System.out.println("Casa recebida pelo updateCasa: " + casa);
        System.out.println("Dado: " + vDado);
        
        boolean jogou = controller.turno(casa, vDado, click);
        if(jogou) {
        	vDado = 0;
        }

        if(api.verificaSeAlgumJogadorVenceu()) {
            encerraJogo();
        }
    }

}

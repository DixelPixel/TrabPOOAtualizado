
package View;

import javax.swing.*;

import java.awt.*;
import Model.API;
import Controller.*;

public class Tab extends JFrame implements Observador {
	
    public final int LARG_DEFAULT=1200;
    public final int ALT_DEFAULT=700;
    private static final Color BLUE_COLOR = Color.decode("0x6495ED");
    private static final Color RED_COLOR = Color.decode("0xdc143c");
    private Componente tabuleiro;
    int vDado = 0;
    int pos;
    private Controller controller;
    private API api;
    private Color corDaVez;
    private JLabel retangulo;

    public Tab(){
    	
        setSize(LARG_DEFAULT,ALT_DEFAULT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		
		controller = Controller.getInstance();
        api = API.getInstance();
        Menu menu = new Menu(this);
        tabuleiro = new Componente(this);
        retangulo = new JLabel();
        
        controller.registraObservador(menu);
        menu.getComponenteInput().registraObservador(this);

        corDaVez = controller.getCorDaVez();
		tabuleiro.setBounds(0,0,800,700);
		getContentPane().add(tabuleiro);

		retangulo.setOpaque(true);
		retangulo.setBackground(corDaVez);
		retangulo.setBounds(897, 480, 120, 120);
        getContentPane().add(retangulo);
		
		menu.setBounds(800,0,500,700);
		getContentPane().add(menu);

        api.adicionaObservadorTabuleiro(this);

		setVisible(true);
    }
    
    public static void main(String[] args){
        Tab frame = new Tab();

        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }

    @Override
    public void update() {
    	
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
    }

    @Override
    public void updateCasa(int casa) {
        this.pos = casa;
        System.out.println("Casa recebida pelo updateCasa: " + casa);
        System.out.println(controller.getNomeCorDaVez());
        System.out.println("Dado: " + vDado);
        
//        if(controller.getRodada() == 1) {
//        	System.out.println("FALAE ");
//        	controller.turno(casa, 0);
//        }
//        
//        if(vDado > 0) {
//        	controller.turno(casa, vDado);
//        }
        
        if(!api.verificaSeAlgumJogadorVenceu()) {
        	controller.turno(casa, vDado);
        	vDado = 0;
        }
        
        
		/*
		 * trecho de codigo para atualizar o feedback visual de quem é o jogador da vez
		 */
        corDaVez = controller.getCorDaVez();
        getContentPane().remove(retangulo);
        retangulo = new JLabel();
        retangulo.setOpaque(true);
		retangulo.setBackground(corDaVez);
		retangulo.setBounds(897, 480, 120, 120);
        getContentPane().add(retangulo);
        getContentPane().repaint();
        
    }

}



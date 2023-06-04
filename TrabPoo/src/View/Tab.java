
package View;

import javax.swing.*;

import java.awt.*;
import Model.API;
import Model.Observado;
import Controller.*;


public class Tab extends JFrame implements Observador {
    public final int LARG_DEFAULT=1200;
    public final int ALT_DEFAULT=700;
    private static final Color BLUE_COLOR = Color.decode("0x6495ED");
    private static final Color RED_COLOR = Color.decode("0xdc143c");
    private Componente tabuleiro;
    int vDado = 0;
    int pos;
    Controller controller = new Controller();

    public Tab(){
        setSize(LARG_DEFAULT,ALT_DEFAULT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
        API api = API.getInstance();

		tabuleiro = new Componente(this);
		tabuleiro.setBounds(0,0,800,700);
		getContentPane().add(tabuleiro);

		Menu menu = new Menu(this);
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
        if(tabuleiro != null){
            getContentPane().remove(tabuleiro);
        }
        Componente aux = new Componente(this);
        tabuleiro = aux;
        tabuleiro.setBounds(0,0,800,700);
        getContentPane().add(tabuleiro);
        getContentPane().repaint();
    }

    @Override
    public void update(int dado) {
       this.vDado = dado;
       System.out.println(vDado);
    }

    @Override
    public void updateCasa(int casa) {
        this.pos = casa;
        System.out.println("Aqui!!!");
        if(this.vDado != 0){
            controller.turno(casa, this.vDado);
            vDado = 0;
        }
    }

}



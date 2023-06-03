
package View;

import javax.swing.*;

import java.awt.*;
import Model.API;


public class Tab extends JFrame {
    public final int LARG_DEFAULT=1200;
    public final int ALT_DEFAULT=700;
    private static final Color BLUE_COLOR = Color.decode("0x6495ED");
    private static final Color RED_COLOR = Color.decode("0xdc143c");

    public Tab(){
        setSize(LARG_DEFAULT,ALT_DEFAULT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		API api = API.getInstance();
		getContentPane().setLayout(null);

		Componente myComponent = new Componente();
		myComponent.setBounds(0,0,800,700);
		getContentPane().add(myComponent);

		Menu menu = new Menu(this);
		menu.setBounds(800,0,500,700);
		getContentPane().add(menu);

		setVisible(true);

    }
    
    public static void main(String[] args){
        Tab frame = new Tab();
//        API api = API.getInstance();

        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}



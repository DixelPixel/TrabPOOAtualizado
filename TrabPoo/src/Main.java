
import Model.*;
import Controller.*;

public class Main {
	public static void main(String[]args) {
		API api = API.getInstance();
		Controller c = new Controller();
		
		// vermelho
		c.turno(13, 2);
		
		//verde
		c.turno(26, 3);
		
		//amarelo
		c.turno(39, 6);
		
		//azul
		System.out.println(api.getCorDaVez().name());
		c.turno(0, 15);
		
		// vermelho
		c.turno(-1, 5);



	}
}

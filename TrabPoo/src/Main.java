
import Model.API;
import Controller.*;

public class Main {
	public static void main(String[]args) {
		API api = API.getInstance();
		Controller c = new Controller();
		
		// vermelho
		c.turno(13, 6);
		System.out.println(api.getCorDaVez().name());
		
		c.turno(19, 5);
		System.out.println(api.getCorDaVez().name());
		
		//verde
		c.turno(26, 6);
		System.out.println(api.getCorDaVez().name());
		
		c.turno(32, 6);
		System.out.println(api.getCorDaVez().name());
		
		c.turno(38, 5);
		System.out.println(api.getCorDaVez().name());
		
		
	}
}

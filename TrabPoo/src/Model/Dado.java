package Model;
import java.util.Random;

class Dado {
	
	protected static int rolarDado() {
		Random g = new Random();
		return g.nextInt(6)+1;
	}
}

package Model;
import java.util.Random;

public class Dado {
	
	protected static int rolarDado() {
		Random g = new Random();
		return g.nextInt(6)+1;
	}
}

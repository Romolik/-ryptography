import java.util.List;
import java.util.Random;

public class Elgamal {
	Integer p = 30803;
	Integer g = 2;
	Integer cA;
	Integer cB;
	Integer dA;
	Integer dB;
	
	public Elgamal() {
		Random random = new Random();
		cA = random.nextInt(1, p);
		cB = random.nextInt(1, p);
		dA = Multiplier.multiply(g, cA, p);
		dB = Multiplier.multiply(g, cB, p);
	}
	
	public List<Integer> sendMessage(Integer message){
		Random random = new Random();
		Integer r = random.nextInt(1, p);
		Integer k = Multiplier.multiply(g, r, p);
		Integer x = message * Multiplier.multiply(dB, r, p) % p;
		return List.of(k, x);
	}
	
	public Integer receiveMessage(Integer k, Integer x) {
		return x * Multiplier.multiply(k, p - 1 - cB, p) % p;
	}
}

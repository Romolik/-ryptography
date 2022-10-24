import javax.swing.*;
import java.util.List;
import java.util.Random;

public class DigitalCash {
	private Integer p = 131;
	private Integer q = 227;
	private Integer N = p * q;
	private Integer c;
	private Integer d;
	
	public DigitalCash() {
		generateKeys();
	}
	
	public List<Integer> initializeBillNumber() {
		Random random = new Random();
		Integer n = random.nextInt(1, N);
		Integer k;
		Integer k_1;
		while(true) {
			k = random.nextInt(1, N);
			List<Integer> list = AdvancedEuclidAlgorithm.findEGCD(N, k);
			if (list.get(0) == 1) {
				k_1 = (list.get(2) + N) % N;
				break;
			}
		}
		return List.of(n, k, k_1);
	}
	
	public Integer sendToBankN(Integer n, Integer k) {
		return n * Multiplier.multiply(k, d, N) % N;
	}
	
	public Integer receiveN(Integer n) {
		return Multiplier.multiply(n, c, N);
	}
	
	public List<Integer> formingBill(Integer s_, Integer k_, Integer n) {
		Integer s = (s_ * k_) % N;
		return List.of(n, s);
	}
	
	public Boolean checkAuthenticityBill(Integer n, Integer s) {
		return Multiplier.multiply(s, d, N).equals(Multiplier.multiply(Multiplier.multiply(n, c, N), d, N));
	}
	
	private void generateKeys() {
		Integer gcd = 0;
		while (!gcd.equals(1)) {
			c = (int) (Math.random() * Integer.MAX_VALUE) % ((p - 1) * (q - 1));
			if (c % 2 == 0) {
				c++;
			}
			List<Integer> tmp = AdvancedEuclidAlgorithm.findEGCD((p - 1) * (q - 1), c);
			gcd = tmp.get(0);
			d = (tmp.get(2) + (p - 1) * (q - 1)) % ((p - 1) * (q - 1));
		}
	}
	
	
}

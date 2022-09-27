import java.util.List;
import java.util.Scanner;

public class ShamirProtocol {
	private Integer p = 30803;
	private Integer cA;
	private Integer dA;
	private Integer cB;
	private Integer dB;
	
	public ShamirProtocol() {
		initializeKeys();
	}
	
	private void initializeKeys() {
		List<Integer> keys = generateKeys();
		cA = keys.get(0);
		dA = keys.get(1);
		keys = generateKeys();
		cB = keys.get(0);
		dB = keys.get(1);
	}
	
	private List<Integer> generateKeys() {
		Integer c = null;
		Integer d = null;
		Integer gcd = 0;
		while (!gcd.equals(1)) {
			c = (int) (Math.random() * Integer.MAX_VALUE) % p;
			if (c % 2 == 0) {
				c++;
			}
			List<Integer> tmp = AdvancedEuclidAlgorithm.findEGCD(p - 1, c);
			gcd = tmp.get(0);
			d = (tmp.get(2) + (p - 1)) % (p - 1);
		}
		return List.of(c, d);
	}
	
	private Integer sendFirstMessageToB(Integer m) {
		return Multiplier.multiply(m, cA, p);
	}
	
	private Integer sendFirstMessageToA(Integer x) {
		return Multiplier.multiply(x, cB, p);
	}
	
	private Integer sendSecondMessageToB(Integer y) {
		return Multiplier.multiply(y, dA, p);
	}
	
	private Integer receiveSecondMessage(Integer z) {
		return Multiplier.multiply(z, dB, p);
	}
	public void sendMessage(Integer m) {
		Integer tmp = sendFirstMessageToB(m);
		System.out.println("Первая отправка сообщения от A к B: " + tmp);
		tmp = sendFirstMessageToA(tmp);
		System.out.println("Первая отправка сообщения от B к A: " + tmp);
		tmp = sendSecondMessageToB(tmp);
		System.out.println("Вторая отправка сообщения от A к B: " + tmp);
		tmp = receiveSecondMessage(tmp);
		System.out.println("B получил следующее сообщение: " + tmp + ", m = " + m);
	}
	
	public Integer getP() {
		return p;
	}
	
}

import java.util.List;
import java.util.Random;

public class DigitalSignatureElGamal {
	private Integer p = 31259;
	private Integer g = 2;
	private Integer y;
	private Integer x;
	
	public DigitalSignatureElGamal() {
		Random random = new Random();
		x = random.nextInt(1, p);
		y = Multiplier.multiply(g, x, p);
	}
	
	public List<Integer> computeSecretPower(Integer M) {
		Integer m = h(M);
		List<Integer> list;
		Integer k;
		while (true) {
			k = new Random().nextInt(1, p);
			list = AdvancedEuclidAlgorithm.findEGCD(p - 1, k);
			if (list.get(0).equals(1)) {
				break;
			}
		}
		Long k_1 = (long) (list.get(2) + p - 1) % (p - 1);
		Integer r = Multiplier.multiply(g, k, p);
		Long s = (m - (r * x) % (p - 1) + p - 1) % (p - 1) * k_1 % (p - 1);
		return List.of(M, r, Math.toIntExact(s));
	}
	
	public Boolean verifyAuthenticityKey(List<Integer> list) {
		if (list.get(1) < 0 || list.get(1) > p || list.get(2) < 0 || list.get(1) > (p - 1)) {
			return false;
		}
		Integer m = h(list.get(0));
		Integer leftOperand = (int)((long)(Multiplier.multiply(y,list.get(1), p) * Multiplier.multiply(list.get(1),list.get(2), p)) % p);
		Integer rightOperand = Multiplier.multiply(g, m, p);
		return leftOperand.equals(rightOperand);
	}
	
	public Integer h(Integer message) {
		return message;
	}
}

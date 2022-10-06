import java.math.BigInteger;
import java.util.List;

public class RSA {
	private BigInteger pA = new BigInteger("131");
	private BigInteger qA = new BigInteger("227");
	private BigInteger pB = new BigInteger("113");
	private BigInteger qB = new BigInteger("281");
	private Integer dA = 3;
	private Integer dB = 3;
	private BigInteger nA;
	private BigInteger nB;
	private Integer cA;
	private Integer cB;
	public RSA() {
		nA = pA.multiply(qA);
		nB = pB.multiply(qB);
		List<Integer> list = AdvancedEuclidAlgorithm.findEGCD((pA.intValue() - 1) * (qA.intValue() - 1), dA.intValue());
		cA = list.get(2);
		if (cA < 0) {
			cA += (pA.intValue() - 1) * (qA.intValue() - 1);
		}
		list = AdvancedEuclidAlgorithm.findEGCD((pB.intValue() - 1) * (qB.intValue() - 1), dB.intValue());
		cB = list.get(2);
		if (cB < 0) {
			cB += (pB.intValue() - 1) * (qB.intValue() - 1);
		}
	}
	
	public Integer encrypt(Integer message) {
		return Multiplier.multiply(message, dB, nB.intValue());
	}
	
	public Integer decrypt(Integer message) {
		return Multiplier.multiply(message, cB, nB.intValue());
	}
}

import java.util.List;

public class DigitalSignatureRSA {
	private final Integer N = 52891;
	private final Integer d = 3;
	private final Integer p = 227;
	private final Integer q = 233;
	private Integer c;
	
	public DigitalSignatureRSA() {
		c = (AdvancedEuclidAlgorithm.findEGCD((p - 1) * (q - 1), d).get(2) + (p - 1) * (q - 1))% ((p - 1) * (q - 1));
	}
	
	public Integer h(Integer message) {
		return message;
	}
	
	public List<Integer> computeSecretPower(Integer message) {
		return List.of(message, Multiplier.multiply(h(message), c, N));
	}
	
	public Boolean verifyAuthenticityKey(List<Integer> list) {
		Integer y = h(list.get(0));
		return y.equals(Multiplier.multiply(list.get(1), d, N));
	}
	
	
}

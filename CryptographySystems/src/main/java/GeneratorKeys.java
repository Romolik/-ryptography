import java.util.List;

public class GeneratorKeys {
	public static List<Integer> generateKeys(Integer p) {
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
}

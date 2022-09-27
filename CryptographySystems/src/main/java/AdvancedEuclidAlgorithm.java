import java.util.List;

public class AdvancedEuclidAlgorithm {
	private static Integer returnFirstArg(Integer a, Integer b) {
		return a;
	}
	
	public static List<Integer> findEGCD(Integer a, Integer b) {
		if (a < b) {
			a = returnFirstArg(b, b = a);
		}
		
		Integer x = 1;
		Integer y = 0;
		Integer x2 = 0;
		Integer y2 = 1;
		
		while (!a.equals(0) && !b.equals(0)) {
			Integer temp = a / b;
			a %= b;
			x -= x2 * temp;
			y -= y2 * temp;
			a = returnFirstArg(b, b = a);
			x = returnFirstArg(x2, x2 = x);
			y = returnFirstArg(y2, y2 = y);
		}
		
		return List.of(a, x, y);
	}
	
}

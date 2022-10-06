import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//Хэширование Пирсона
public class HashFunction {
	private List<Integer> exampleTable = new ArrayList<>();
	private class Comparator implements java.util.Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			Random random = new Random();
			int x = random.nextInt();
			System.out.println(x);
			return x;
		}
	}
	public HashFunction() {
		IntStream.range(0, 65536).mapToObj(x -> exampleTable.add(x)).collect(Collectors.toList());
		Collections.shuffle(exampleTable);
	}
	
	public Integer hash(String message) {
		Integer hash = message.length() % 65536;
		for (int i = 0; i < message.length(); i ++) {
			hash = exampleTable.get((hash + message.charAt(i)) % 65536);
		}
		return hash;
	}
}

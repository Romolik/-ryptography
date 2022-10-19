import java.util.*;

public class CoinTossing {
	private final Integer a = 0;
	private final Integer b = 1;
	
	private Integer p = 30803;
	private Integer cA;
	private Integer dA;
	private Integer cB;
	private Integer dB;
	private Map map = new HashMap<Integer, String>();
	private List<Integer> R = new ArrayList<>();
	
	private String resToss;
	
	public CoinTossing() {
		map.put(a, "Орёл");
		map.put(b, "Решка");
	}
	
	private void initializeKeysA() {
		List<Integer> keys = GeneratorKeys.generateKeys(p);
		cA = keys.get(0);
		dA = keys.get(1);
	}
	
	private void initializeKeysB() {
		List<Integer> keys = GeneratorKeys.generateKeys(p);
		cB = keys.get(0);
		dB = keys.get(1);
	}
	
	private void printIntToBin(List<Integer> list) {
		for (var item : list) {
			System.out.println(Integer.toBinaryString(item));
		}
	}
	
	public List<Integer> sendToBob() {
		initializeKeysA();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			Integer r = new Random().nextInt(2, (p - 1) / 2);
			Integer R1 = r<<1 | i;
			R.add(R1);
		}
		System.out.println("Сгенерированные алисой R: ");
		printIntToBin(R);
		for (int i = 0; i < 2; i++) {
			list.add(Multiplier.multiply(R.get(i), dA, p));
		}
		Collections.shuffle(list);
		return list;
	}
	
	public Integer receiveFromAlice1(List<Integer> list) {
		initializeKeysB();
		Integer rand = new Random().nextInt(0, 2);
		return Multiplier.multiply(list.get(rand), dB, p);
	}
	
	public Integer receiveFromBob1(Integer message) {
		return Multiplier.multiply(message, cA, p);
	}
	
	public Integer receiveFromAlice2(Integer message) {
		Integer tmp = Multiplier.multiply(message, cB, p);
		Integer tmp2 = tmp & 1;
		resToss = (String) map.get(tmp2);
		return tmp;
	}
	
	public String getResToss() {
		return resToss;
	}
	
	public Boolean checkAlice(Integer message) {
		return R.contains(message);
	}
}

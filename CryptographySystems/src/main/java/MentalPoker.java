import java.util.*;

public class MentalPoker {
	private Integer p = 30803;
	private Integer alpha = 0;
	private Integer beta = 1;
	private Integer gamma = 2;
	
	HashMap<Integer, String> card = new HashMap<>();
	private Integer cA;
	private Integer cB;
	private Integer dA;
	private Integer dB;
	
	private Random random = new Random();
	
	private Integer cardA;
	private Integer cardB;
	private List<Integer> R = new ArrayList<>();
	
	public MentalPoker() {
		initializeKeys();
		card.put(alpha, "Туз");
		card.put(beta, "Король");
		card.put(gamma, "Дама");
	}
	
	public String getCardA() {
		return card.get(cardA);
	}
	
	public String getCardB() {
		return card.get(cardB);
	}
	
	
	private void initializeR() {
		for (int i = 0; i < 3; i++) {
			Integer r = random.nextInt(2, (p - 1) / 4);
			Integer R1 = r<<2 | i;
			R.add(R1);
		}
	}
	
	private void clearR() {
		R.clear();
	}
	
	private void initializeKeys() {
		List<Integer> keys = GeneratorKeys.generateKeys(p);
		cA = keys.get(0);
		dA = keys.get(1);
		keys = GeneratorKeys.generateKeys(p);
		cB = keys.get(0);
		dB = keys.get(1);
	}
	
	public List<Integer> sendFirstMessageToB() {
		List<Integer> list = new ArrayList<>();
		initializeR();
		//for Test
		System.out.println("Сгенерированные алисой R: ");
		printIntToBin(R);
		for (int i = 0; i < 3; i++) {
			list.add(Multiplier.multiply(R.get(i), cA, p));
		}
		Collections.shuffle(list);
		return list;
	}
	
	public List<Integer> chooseRandomCardToA(List<Integer> list) {
		List<Integer> y = new ArrayList<>();
		Integer randomInt = random.nextInt(0,3);
		y.add(list.get(randomInt));
		list.remove(randomInt.intValue());
		List<Integer> tmpList = new ArrayList<>();
		for (var item : list) {
			tmpList.add(Multiplier.multiply(item, cB, p));
		}
		Collections.shuffle(tmpList);
		y.addAll(tmpList);
		return y;
	}
	
	public Integer chooseRandomCardToB(List<Integer> list) {
		cardA = list.get(0);
		cardA = Multiplier.multiply(cardA, dA, p);
		cardA &= 0x0003;
		System.out.println("Карта доставшаяся Алисе: " + cardA);
		list.remove(0);
		return Multiplier.multiply(list.get(random.nextInt(0, 1)), dA, p);
	}
	
	public Integer receiveCardToB(Integer z) {
		cardB = Multiplier.multiply(z, dB, p);
		cardB &= 0x0003;
		return cardB;
	}
	
	private void printIntToBin(List<Integer> list) {
		for (var item : list) {
			System.out.println(Integer.toBinaryString(item));
		}
	}
}

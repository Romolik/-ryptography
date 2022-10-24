import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class BabyStepGiantStep {
	private Integer n;
	private Integer m;
	
	private class ObjectList {
		private Integer index;
		private Integer rowNumber;
		
		public Integer getIndex() {
			return index;
		}
		
		public Integer getRowNumber() {
			return rowNumber;
		}
		
		public Integer getValue() {
			return value;
		}
		
		private Integer value;
		
		public ObjectList(Integer index, Integer value, Integer rowNumber) {
			this.index = index;
			this.value = value;
			this.rowNumber = rowNumber;
		}
		
		
	}
	
	public Integer findX(Integer p, Integer a, Integer y) {
		n = m = (int)Math.ceil(Math.sqrt(p));
		List<ObjectList> list1 = new ArrayList<>();
		Integer a2 = 1;
		Integer a3 = Multiplier.multiply(a, m, p);
		Integer tmp = a3;
		for (int i = 0; i < n; i++) {
			list1.add(new ObjectList(i, (y * a2) % p, 1));
			a2 = (a2 * a) % p;
			list1.add(new ObjectList(i + 1, tmp, 2));
			tmp = (tmp * a3) % p;
		}
		list1.sort(Comparator.comparing(ObjectList::getValue).thenComparing(ObjectList::getRowNumber));
		for (int i = 0; i < 2 * n - 1; i++) {
			if (list1.get(i).getValue().equals(list1.get(i + 1).getValue()) &&
				!list1.get(i).getRowNumber().equals(list1.get(i + 1).getRowNumber())) {
				if (list1.get(i + 1).getRowNumber() > list1.get(i).getRowNumber()) {
					return list1.get(i + 1).getIndex() * m - list1.get(i).getIndex();
				} else {
					return list1.get(i).getIndex() * m - list1.get(i + 1).getIndex();
				}
			}
		}
		return null;
	}
}

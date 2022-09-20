public class Multiplier {
    public Integer multiply(Long base, Integer exponent, Integer module) {
        StringBuffer binaryNotation = new StringBuffer(Integer.toBinaryString(exponent)).reverse();
        Long res = 1L;
        Long currentMultiplier = base;
        for (int i = 0; i < binaryNotation.length(); i++) {
            String str = String.valueOf(binaryNotation.charAt(i));
            Integer symbol = Integer.parseInt(str);
            if (symbol.equals(1)) {
                res = res * currentMultiplier % module;
            }
            currentMultiplier = currentMultiplier * currentMultiplier % module;
        }
        return Math.toIntExact(res);
    }
}

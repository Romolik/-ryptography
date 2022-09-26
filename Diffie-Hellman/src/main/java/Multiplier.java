import java.math.BigInteger;

public class Multiplier {
    public static Integer multiply(Integer base, Integer exponent, Integer module) {
        StringBuffer binaryNotation = new StringBuffer(Integer.toBinaryString(exponent)).reverse();
        BigInteger res = new BigInteger("1");
        BigInteger currentMultiplier = new BigInteger(String.valueOf(base));
        for (int i = 0; i < binaryNotation.length(); i++) {
            String str = String.valueOf(binaryNotation.charAt(i));
            Integer symbol = Integer.parseInt(str);
            if (symbol.equals(1)) {
                res = res.multiply(currentMultiplier).mod(BigInteger.valueOf(module));
            }
            currentMultiplier = currentMultiplier.multiply(currentMultiplier).mod(BigInteger.valueOf(module));
           
        }
        return res.intValue();
    }
}

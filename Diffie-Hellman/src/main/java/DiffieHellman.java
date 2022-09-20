public class DiffieHellman {
    private Integer p = 30803;
    private Integer g = 2;
    private Integer xA = (int) (Math.random() * Integer.MAX_VALUE);
    private Integer xB = (int) (Math.random() * Integer.MAX_VALUE);

    private Integer yA;
    private Integer yB;
    private Integer z;

    public DiffieHellman() {
        Multiplier multiplier = new Multiplier();
        yA = multiplier.multiply(Long.valueOf(g), xA, p);
        yB = multiplier.multiply(Long.valueOf(g), xB, p);
        z = multiplier.multiply(Long.valueOf(yB), xA, p);
    }

    public Integer getYa() {
        return yA;
    }

    public Integer getYb() {
        return yB;
    }

    public Integer getZ() {
        return z;
    }
}

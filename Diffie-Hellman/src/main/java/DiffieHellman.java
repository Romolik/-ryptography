public class DiffieHellman {
    private Integer p = 30803;
    private Integer g = 2;
    private Integer xA = (int) (Math.random() * Integer.MAX_VALUE);
    private Integer xB = (int) (Math.random() * Integer.MAX_VALUE);

    private Integer yA;
    private Integer yB;
    private Integer z;

    public DiffieHellman() {
        calculateKeys();
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

    //for test
    public void setXa(Integer x) {
        xA = x;
    }

    //for test
    public void setXb(Integer x) {
        xB = x;
    }

    //for test
    public void setP(Integer p) {
        this.p = p;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public void calculateKeys() {
        Multiplier multiplier = new Multiplier();
        yA = multiplier.multiply(Long.valueOf(g), xA, p);
        yB = multiplier.multiply(Long.valueOf(g), xB, p);
        z = multiplier.multiply(Long.valueOf(yB), xA, p);
    }
}

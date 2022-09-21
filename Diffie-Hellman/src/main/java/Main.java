public class Main {
    public static void main(String[] args) {
        Multiplier multiplier = new Multiplier();
        System.out.println("3^11 mod 19 = " + multiplier.multiply(3L, 11, 19));
        DiffieHellman diffieHellman = new DiffieHellman();
        diffieHellman.setXa(3);
        diffieHellman.setXb(4);
        diffieHellman.setP(23);
        diffieHellman.setG(2);
        diffieHellman.calculateKeys();
        System.out.println("Ya = " + diffieHellman.getYa() + ", Yb = " + diffieHellman.getYb() +
                ",Zab = " + diffieHellman.getZ());
    }
}

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShamirProtocol shamirProtocol = new ShamirProtocol();
        Scanner in = new Scanner(System.in);
        
        System.out.println("Введите m: ");
        try {
            Integer m = in.nextInt();
            if (m >= shamirProtocol.getP()) {
                System.out.println("m >= p");
                return;
            }
            shamirProtocol.sendMessage(m);
        } catch (Exception e) {
            System.out.println("Ошибка ввода");
            e.printStackTrace();
        }
        
    }
}

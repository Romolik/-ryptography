import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        DigitalCash digitalCash = new DigitalCash();
//        List<Integer> list = digitalCash.initializeBillNumber();
//        System.out.println("Покупатель вернул n, k, k^(-1): " + list);
//        Integer tmp = digitalCash.sendToBankN(list.get(0), list.get(1));
//        System.out.println("n~ = " + tmp);
//        tmp = digitalCash.receiveN(tmp);
//        System.out.println("s~ = " + tmp);
//        List<Integer> list2 = digitalCash.formingBill(tmp, list.get(2), list.get(0));
//        System.out.println("Купюра сформированная покупателем: " + list2);
//        System.out.println("Проверка банком подлинности купюры: " + digitalCash.checkAuthenticityBill(list.get(0),
//                list2.get(1)));
        BabyStepGiantStep babyStepGiantStep = new BabyStepGiantStep();
        Integer p;
        Integer a;
        Integer y;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("p = ");
            p = Integer.parseInt(bufferedReader.readLine());
            System.out.println("a = ");
            a = Integer.parseInt(bufferedReader.readLine());
            System.out.println("y = ");
            y = Integer.parseInt(bufferedReader.readLine());
            Integer x = babyStepGiantStep.findX(p, a, y);
            if (x == null) {
                System.out.println("Такого х не существует");
                return;
            }
            System.out.println("x = " + x);
            System.out.println("a^x mod p = " + Multiplier.multiply(a, x, p));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*MentalPoker mentalPoker = new MentalPoker();
        List<Integer> list = mentalPoker.sendFirstMessageToB();
        System.out.println("Алиса прислала Бобу X: ");
        System.out.println(list);
        list = mentalPoker.chooseRandomCardToA(list);
        System.out.println("Боб выбрал карту Алисе: " + list.get(0));
        System.out.println("Боб вернул Алисе Y: " + list.get(1) + ", " + list.get(2));
        Integer cardB = mentalPoker.chooseRandomCardToB(list);
        System.out.println("Алиса выбрала и отправила Бобу карту: " + cardB);
        mentalPoker.receiveCardToB(cardB);
        System.out.println("Боб расшифровал свою карту: " + mentalPoker.getCardB());
        System.out.println("Карта Алисы: " + mentalPoker.getCardA());*/
        DigitalSignatureRSA digitalSignatureRSA = new DigitalSignatureRSA();
        List<Integer> list = digitalSignatureRSA.computeSecretPower(500);
        System.out.println(list);
        System.out.println(digitalSignatureRSA.verifyAuthenticityKey(list));
    }
}
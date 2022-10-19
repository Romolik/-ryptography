import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        DigitalSignatureElGamal digitalSignatureElGamal = new DigitalSignatureElGamal();
//        List<Integer> list = digitalSignatureElGamal.computeSecretPower(500);
//        System.out.println("Подпись сообщения: 500");
//        System.out.println(list);
//        System.out.println("Проверка подписи");
//        System.out.println(digitalSignatureElGamal.verifyAuthenticityKey(list));
//        System.out.println("Немного изменим компонент подписанного сообщения");
//        list = List.of(500, list.get(1) + 1, list.get(2));
//        System.out.println(list);
//        System.out.println("Проверка подписи");
//        System.out.println(digitalSignatureElGamal.verifyAuthenticityKey(list));
        CoinTossing coinTossing = new CoinTossing();
        List<Integer> list;
        list = coinTossing.sendToBob();
        System.out.println("Отправляем Бобу два сгенерированных числа" + list);
        Integer tmp = coinTossing.receiveFromAlice1(list);
        System.out.println("Боб выберает случайное число и возводит в свою открытую степень: " + tmp);
        tmp = coinTossing.receiveFromBob1(tmp);
        System.out.println("Алиса возводит в секретную степень: " + tmp);
        tmp = coinTossing.receiveFromAlice2(tmp);
        System.out.println("Боб узнаёт какая сторона монеты выпала: " + coinTossing.getResToss());
        System.out.println("Алиса проверяет какая сторона монеты выпала: " + coinTossing.checkAlice(tmp));
    }
}
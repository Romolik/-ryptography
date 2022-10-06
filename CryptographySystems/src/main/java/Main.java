import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        RSA rsa = new RSA();
//        Integer encryptMessage = rsa.encrypt(543);
//        System.out.println("Алиса присылает зашифровнное сообщение Бобу: " + encryptMessage);
//        Integer decryptMessage = rsa.decrypt(encryptMessage);
//        System.out.println("Боб расшифровывает сообщение: " + decryptMessage + ", Исходное сообщение: " + "543");
        Elgamal elgamal = new Elgamal();
        List<Integer> list = elgamal.sendMessage(1025);
        System.out.println("A отправила k: " + list.get(0) + ", x: " + list.get(1));
        System.out.println("B получил m': " + elgamal.receiveMessage(list.get(0), list.get(1)) +
                ", Изначальное сообщение: " + 1025);
    }
}
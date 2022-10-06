import java.io.*;

public class Main {
	public static void main(String[] args) {
		/*Vernam vernam = new Vernam();
		String str = vernam.encrypt("1113");
		System.out.println(str);
		System.out.println(vernam.decrypt(str));*/
//		HashFunction hashFunction = new HashFunction();
//		System.out.println(hashFunction.hash("1111a"));
//		System.out.println(hashFunction.hash("1111A"));
//		System.out.println(hashFunction.hash("1111A"));
		GOST_28147_89 gost_28147_89 = new GOST_28147_89();
		try {
			gost_28147_89.rpz(GOST_28147_89.Mode.ENCRYPT,
					new DataOutputStream(new FileOutputStream("src/main/resources/EncryptedText.txt")),
					new DataInputStream(new FileInputStream("src/main/resources/Text.txt")));
			gost_28147_89.rpz(GOST_28147_89.Mode.DECRYPT,
					new DataOutputStream(new FileOutputStream("src/main/resources/DecryptedText.txt")),
					new DataInputStream(new FileInputStream("src/main/resources/EncryptedText.txt")));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

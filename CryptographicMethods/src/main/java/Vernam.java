import org.apache.commons.lang3.RandomStringUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Comparator;

public class Vernam {
	private static final String pathToKey = "src/main/resources/key.txt";
	public String encrypt(String message) {
		Integer lenMessage = message.length();
		String key = generateKey(lenMessage);
		StringBuffer encryptedString = new StringBuffer();
		try (FileWriter fileWriter = new FileWriter(pathToKey)) {
			fileWriter.write(key);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		for (int i = 0; i < lenMessage; i++) {
			encryptedString.append((char)(key.charAt(i) ^ message.charAt(i)));
		}
		return encryptedString.toString();
	}
	
	public String decrypt(String message) {
		String key;
		StringBuffer stringBuffer = new StringBuffer();
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToKey))) {
			key = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (message.length() == key.length()) {
			for (int i = 0; i < message.length(); i++) {
				stringBuffer.append((char) (message.charAt(i) ^ key.charAt(i)));
			}
		} else {
			System.out.println("Ошибка: длина ключа не совпадает с длинной зашифрованного сообщения");
		}
		return stringBuffer.toString();
	}
	
	private String generateKey(Integer len) {
		return RandomStringUtils.randomAlphabetic(len);
	}
}

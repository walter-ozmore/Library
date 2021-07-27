package Encription;

import java.io.File;

public class EncryptFileTester {
	public static void main(String[] args) {
		File file = new File("input.txt");
		EncryptFile5.codeAlgorithm(file, true, "ABABBABBBABBA",12);
	}
}

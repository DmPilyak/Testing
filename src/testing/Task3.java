package testing;

import java.math.BigInteger;
import java.util.Scanner;

public class Task3 {
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.print("Input your number: ");
		int num = scanner.nextInt();
		// use the BigInteger class to carry out calculations with large amounts
		BigInteger res = new BigInteger("1");
		for(Integer i = 2; i <= num; i++) {
			String stringIterable = i.toString(i);
			// get the factorial in BigInteger
			res = res.multiply(new BigInteger(stringIterable));
		}
		System.out.println(num + "! = " + res);
		// get the factorial in String
		String stringResult = res.toString();
		int sum = 0;
		for(int i = 0; i < stringResult.length(); i++) {
			// iterate over the symbols of the result and parse into int
			char currChar = stringResult.charAt(i);
			int parseCurrChar = currChar - '0';
			sum += parseCurrChar;
		}
		System.out.println("Sum of the digits in the number" + num + "! = " + sum);
	}

}

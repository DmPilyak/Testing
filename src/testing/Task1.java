package testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task1 {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Input your N: ");
		int N = scanner.nextInt();
		List<String> arr = new ArrayList<String>(); // main array
		arr.add("()");
		if (N <= 0) { // checking for correct input
			System.out.println("Incorrect input!");
			System.exit(0);
		}
		String currString;
		String pair = "()";
		// the essence of the algorithm is that starting with "()" in the list we add to
		// it all possible combinations of a pair of brackets after each '(' or ')',
		// while looking in the current sheet so that the combinations of brackets are
		// not repeated
		while (N > 1) {
			List<String> arr2 = new ArrayList<String>();
			// iterate over the elements of the list
			for (String elem : arr) {
				// iterate over element char
				for (int i = 0; i < elem.length(); i++) {
					// iterate over each character in the element, inserting "()" after it
					currString = elem.substring(0, i) + pair + elem.substring(i, elem.length());
					// check if there is already such an element in the list
					if (!arr2.contains(currString))
						arr2.add(currString);
				}
			}
			// overwrite the array to store the data in one and decrease N by 1
			arr = arr2;
			N--;
		}
		// output
		System.out.println("Number of correct brackets: " + arr.size());
	}

}

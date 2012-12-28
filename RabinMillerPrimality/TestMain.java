package rabinMillerTest;

import java.util.Scanner;
public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number to check: ");
		double testNum = sc.nextDouble();
		System.out.println("Enter number of tests: ");
		int tests = sc.nextInt();
		double witness = RMtest.rmTest(testNum, tests);
		if (witness == -2) {
			System.out.println("Your number was too small.");
		}
		else if (witness == -1) {
			System.out.println(testNum + " is probably prime.");
		}
		else {
			System.out.println(witness + " is a witness to compositeness");
		}
	}

}

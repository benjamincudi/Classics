package rabinMillerTest;

import java.util.Random;
/**
 * Implementation of Rabin-Miller Primality test.
 * This test checks a number to determine whether it is
 * prime or composite, and returns a witness if it is 
 * found to be composite. 
 * 
 * @author BenGarfield
 * @version 4/7/2012
 */
public class RMtest {

	/**
	 * This performs the Rabin-Miller test.
	 * Number of trials determines accuracy.
	 * @return -2 if testNum was too small.
	 * 
	 * @param testNum - Number being checked for primality,
	 * 		testNum must be > 3 and odd.
	 * @param trials - How many times to repeat the test
	 * @return - Integer that is witness to a composite,
	 * 		or -1 if it is probably prime based on tests.
	 */
	public static double rmTest(double testNum, int trials) {
		//If n being checked is <= 3 or even, this test is pointless
		if ((testNum <= 3) || ((testNum % 2) == 0)) {
			return -2;
		}
		double witness = -1; //Where we will store any witness we find
		double rmCheck = testNum - 1;
		int binaryPower = 0; //We will write rmCheck as (2^i)*d
		while ((rmCheck % 2) == 0) {
			binaryPower++;
			rmCheck /= 2;
		}
		Random lens = new Random(System.nanoTime());
		//Pick random integer potential witnesses from [2, n-2]
		double [] trialNum = new double [trials];
		for (int i = 0; i < trials; i++) {
			trialNum[i] = Math.floor(lens.nextDouble() * (testNum - 4)) + 2;
		}
		double check;
		int k = 0;
		for (int i = 0; i < trials; i++) {
			check = trialNum[i] % testNum;
			k = 1;
			double baseCheck = check;
			while (k < rmCheck) {
				if ((k*2) <= rmCheck) {
					check *= check;
					check = check % testNum;
					k *= 2;
				}
				else {
					check *= baseCheck;
					check = check % testNum;
					k++;
				}
			}
			//If either of these are true, our test
			//says that testNum is probably prime so far.
			if ((check != 1) && (check != (testNum - 1))) {
			//If neither are true, verify that no square check
			//is congruent to -1 mod testNum
				int j = 1;
				while (j < binaryPower) {
					check = Math.pow(check, 2) % testNum;
					//If check ^ (2^j * rmCheck) == 1, it is composite
					if (check == 1) {
						//This is the witness to compositeness
						witness = trialNum[i];
						return witness;
					}
					//If check ^ (2^j * rmCheck) == -1, it is probably prime
					else if (check == testNum - 1) {
						//Congruence to -1 implies primeness
						j = binaryPower + 1;
					}
					else {
						//If neither is true, keep checking!
						j++;
					}
				}
				if (j == binaryPower) {
					/**
					 * If all squares were checked without any 
					 * implying primeness of testNum, it is 
					 * composite and we have a witness.
					 */
					witness = trialNum[i];
					return witness;
				}
			}
		}
		//If we did all our trials and they all said that
		//testNum was prime, then it is probably prime.
		return witness;
	}
}
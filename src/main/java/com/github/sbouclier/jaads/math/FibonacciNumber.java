package com.github.sbouclier.jaads.math;

import java.math.BigInteger;

/**
 * In mathematics, the Fibonacci numbers are the numbers in the following
 * integer sequence, called the Fibonacci sequence, and characterized by the
 * fact that every number after the first two is the sum of the two preceding
 * ones.
 * 
 * @author Stéphane Bouclier
 * @see <a href=
 *      "https://en.wikipedia.org/wiki/Fibonacci_number">https://en.wikipedia.org/wiki/Fibonacci_number</a>
 */
public final class FibonacciNumber {

	private FibonacciNumber() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Compute Fibonacci number recursively. Be warned that this function is
	 * highly slower than iterative function
	 * 
	 * @param n
	 *            number
	 * @return fibonacci number
	 */
	public static long computeRecursively(long n) {
		return n < 2 ? n : computeRecursively(n - 1) + computeRecursively(n - 2);
	}

	public static BigInteger computeIteratively(long n) {
		BigInteger first = BigInteger.ZERO;
		BigInteger second = BigInteger.ONE;
		BigInteger temp;

		if (n == 0) {
			return BigInteger.ZERO;
		}

		for (long i = 2; i <= n; i++) {
			temp = first.add(second);
			first = second;
			second = temp;
		}

		return second;
	}
}

package com.github.sbouclier.jaads.math;

import java.math.BigInteger;

/**
 * In mathematics, the factorial of a non-negative integer n, denoted by n!, is
 * the product of all positive integers less than or equal to n.
 * 
 * @author Stéphane Bouclier
 * @see <a href=
 *      "https://en.wikipedia.org/wiki/Factorial">https://en.wikipedia.org/wiki/Factorial</a>
 */
public final class Factorial {
	
	private Factorial() {
		throw new UnsupportedOperationException();
	}

	public static BigInteger computeRecursively(long n) {
		return computeRecursivelyWithLargeValue(BigInteger.valueOf(n));
	}

	private static BigInteger computeRecursivelyWithLargeValue(BigInteger n) {
		// f(0) => 1
		// f(n) => n * f(n - 1)
		return BigInteger.ZERO.equals(n) ? BigInteger.ONE
				: n.multiply(computeRecursivelyWithLargeValue(n.subtract(BigInteger.ONE)));
	}

	public static BigInteger computeIteratively(long n) {
		BigInteger fact = BigInteger.ONE;

		for (long i = 1; i <= n; i++) {
			fact = fact.multiply(BigInteger.valueOf(i));
		}

		return fact;
	}
}

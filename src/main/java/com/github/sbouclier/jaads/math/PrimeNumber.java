package com.github.sbouclier.jaads.math;

import java.util.ArrayList;
import java.util.List;

/**
 * A prime number is a natural number greater than 1 that has no positive
 * divisors other than 1 and itself.
 * 
 * @author Stéphane Bouclier
 * @see <a href=
 *      "https://en.wikipedia.org/wiki/Prime_number">https://en.wikipedia.org/wiki/Prime_number</a>
 */
public class PrimeNumber {
	
	private PrimeNumber() {
		throw new UnsupportedOperationException();
	}

	public static List<Integer> sieveOfEratosthenes(int n) {
		List<Integer> res = new ArrayList<Integer>();

		boolean prime[] = new boolean[n + 1];

		// init array
		for (int i = 0; i < n; i++) {
			prime[i] = true;
		}

		for (int p = 2; p * p <= n; p++) {
			if (prime[p] == true) {
				// flag to false all multiples of p
				for (int i = p * 2; i <= n; i += p) {
					prime[i] = false;
				}
			}
		}

		// add prime numbers to list
		for (int i = 2; i <= n; i++) {
			if (prime[i] == true) {
				res.add(i);
			}
		}

		return res;
	}
}

package com.github.sbouclier.jaads.math;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

import org.junit.Test;

/**
 * Fibonacci number test
 * 
 * @author Stéphane Bouclier
 *
 */
public class FibonacciNumberTest {

	@Test
	public void should_return_values() {
		long[] expectedValues = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181,
				6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811 };

		for (int i = 0; i < expectedValues.length; i++) {
			assertEquals(expectedValues[i], FibonacciNumber.computeIteratively(i).longValue());
			assertEquals(expectedValues[i], FibonacciNumber.computeRecursively(i));
		}

		// over 50 it is very slow due to the recursive calls
		assertEquals(102334155, FibonacciNumber.computeRecursively(40));

		assertEquals(new BigInteger("12586269025"), FibonacciNumber.computeIteratively(50));
		assertEquals(new BigInteger("354224848179261915075"), FibonacciNumber.computeIteratively(100));
		assertEquals(
				new BigInteger(
						"43466557686937456435688527675040625802564660517371780402481729089536555417949051890403879840079255169295922593080322634775209689623239873322471161642996440906533187938298969649928516003704476137795166849228875"),
				FibonacciNumber.computeIteratively(1000));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void should_not_instanciate_class() throws Throwable {
		final Constructor<FibonacciNumber> constructor = FibonacciNumber.class.getDeclaredConstructor();
		constructor.setAccessible(true);

		try {
			constructor.newInstance();
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}
}

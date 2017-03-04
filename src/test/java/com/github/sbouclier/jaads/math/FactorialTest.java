package com.github.sbouclier.jaads.math;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

import org.junit.Test;

/**
 * Factorial test
 * 
 * @author Stéphane Bouclier
 *
 */
public class FactorialTest {

	@Test
	public void should_return_values() {
		long[] expectedValues = { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600 };

		for (int i = 0; i < expectedValues.length; i++) {
			assertEquals(expectedValues[i], Factorial.computeIteratively(i).intValue());
			assertEquals(expectedValues[i], Factorial.computeRecursively(i).intValue());
		}

		assertEquals(new BigInteger("2432902008176640000"), Factorial.computeRecursively(20));
		assertEquals(new BigInteger("30414093201713378043612608166064768844377641568960512000000000000"),
				Factorial.computeRecursively(50));
		assertEquals(
				new BigInteger(
						"93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000"),
				Factorial.computeRecursively(100));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void should_not_instanciate_class() throws Throwable {
		final Constructor<Factorial> constructor = Factorial.class.getDeclaredConstructor();
		constructor.setAccessible(true);

		try {
			constructor.newInstance();
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}
}

package com.github.sbouclier.jaads.sort;

import static org.junit.Assert.assertArrayEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Bogosort test
 * 
 * @author Stéphane Bouclier
 *
 */
public class BogosortTest {

	@Test
	public void should_sort() {
		List<Integer> list = Arrays.asList(3, 2, 8, 4);
		Bogosort.sort(list);

		assertArrayEquals(new Integer[] { 2, 3, 4, 8 }, list.toArray());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void should_not_instanciate_class() throws Throwable {
		final Constructor<Bogosort> constructor = Bogosort.class.getDeclaredConstructor();
		constructor.setAccessible(true);

		try {
			constructor.newInstance();
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}
}

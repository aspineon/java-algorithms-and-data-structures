package com.github.sbouclier.jaads.sort;

import static org.junit.Assert.assertArrayEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * QuickSort test
 * 
 * @author Stéphane Bouclier
 *
 */
public class QuickSortTest {

	public List<Integer> list = Arrays.asList(9, 8, 3, 6, 5, 4, 7, 2, 1);
	public List<Integer> listOneValue = Arrays.asList(2);
	public List<Integer> listEmpty = new ArrayList<Integer>();

	@Test
	public void should_sort_in_place() {
		QuickSort.sortInPlace(list);
		assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, list.toArray());
	}

	@Test
	public void should_sort_out_of_place() {
		assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, QuickSort.sortOutOfPlace(list).toArray());
	}

	@Test
	public void should_sort_in_place_empty_list() {
		QuickSort.sortInPlace(listEmpty);
		assertArrayEquals(new Integer[] {}, listEmpty.toArray());
	}

	@Test
	public void should_sort_out_of_place_empty_list() {
		assertArrayEquals(new Integer[] {}, QuickSort.sortOutOfPlace(listEmpty).toArray());
	}

	@Test
	public void should_sort_in_place_list_with_one_value() {
		QuickSort.sortInPlace(listOneValue);
		assertArrayEquals(new Integer[] { 2 }, listOneValue.toArray());
	}

	@Test
	public void should_sort_out_of_place_list_with_one_value() {
		assertArrayEquals(new Integer[] { 2 }, QuickSort.sortOutOfPlace(listOneValue).toArray());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void should_not_instanciate_class() throws Throwable {
		final Constructor<QuickSort> constructor = QuickSort.class.getDeclaredConstructor();
		constructor.setAccessible(true);

		try {
			constructor.newInstance();
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}
}

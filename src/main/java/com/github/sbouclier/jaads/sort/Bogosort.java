package com.github.sbouclier.jaads.sort;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Do not use, it's the worst sort algorithm !
 * 
 * @author Stéphane Bouclier
 * @see <a href=
 *      "https://en.wikipedia.org/wiki/Bogosort">https://en.wikipedia.org/wiki/Bogosort</a>
 *
 * @param <T>
 */
public final class Bogosort<T extends Comparable<T>> {

	private Bogosort() {
		throw new UnsupportedOperationException();
	}

	private static <T extends Comparable<T>> boolean isSorted(List<T> list) {
		if (list.isEmpty()) {
			return true;
		}

		Iterator<T> it = list.iterator();
		T previous = it.next();
		while (it.hasNext()) {
			T current = it.next();
			if (previous.compareTo(current) > 0) {
				return false;
			}
			previous = current;
		}
		return true;
	}

	/**
	 * While list is not sorted, shuffle the list
	 * 
	 * @param list
	 *            to sort
	 */
	public static <T extends Comparable<T>> void sort(List<T> list) {
		while (!isSorted(list)) {
			Collections.shuffle(list);
		}
	}
}

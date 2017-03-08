package com.github.sbouclier.jaads.sort;

import java.util.LinkedList;
import java.util.List;

/**
 * Quicksort is a divide and conquer algorithm
 * 
 * @author Stéphane Bouclier
 *
 * @param <T>
 *            type of value
 */
public class QuickSort<T extends Comparable<T>> {

	public static <T extends Comparable<T>> List<T> sortOutOfPlace(List<T> list) {
		if (!list.isEmpty()) {
			T pivot = choosePivotByFirstValue(list);

			List<T> firstPartition = new LinkedList<T>();
			List<T> pivotList = new LinkedList<T>();
			List<T> secondPartition = new LinkedList<T>();

			// Values partition according to the pivot
			for (T value : list) {
				if (value.compareTo(pivot) < 0) {
					firstPartition.add(value);
				} else if (value.compareTo(pivot) > 0) {
					secondPartition.add(value);
				} else {
					pivotList.add(value);
				}
			}

			firstPartition = sortOutOfPlace(firstPartition);
			secondPartition = sortOutOfPlace(secondPartition);

			firstPartition.addAll(pivotList);
			firstPartition.addAll(secondPartition);
			return firstPartition;
		}
		return list;
	}

	public static <T extends Comparable<T>> void sortInPlace(List<T> list) {
		sortInPlace(list, 0, list.size() - 1);
	}

	private static <T extends Comparable<T>> void sortInPlace(List<T> list, int startIndex, int endIndex) {
		System.out.println(list.size() + " startIndex:" + startIndex + ", endIndex:" + endIndex);

		if (startIndex >= endIndex) {
			return;
		}

		T pivot = list.get(startIndex);
		int left = startIndex;
		int right = endIndex;

		while (left <= right) {
			while (list.get(left).compareTo(pivot) < 0) {
				left++;
			}

			while (list.get(right).compareTo(pivot) > 0) {
				right--;
			}

			if (left <= right) {
				swap(list, left, right);
				left++;
				right--;
			}

			sortInPlace(list, startIndex, right);
			sortInPlace(list, left, endIndex);
		}
	}

	private static <T extends Comparable<T>> T choosePivotByFirstValue(List<T> list) {
		return list.get(0);
	}

	private static <T extends Comparable<T>> void swap(List<T> list, int index1, int index2) {
		T temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}
}

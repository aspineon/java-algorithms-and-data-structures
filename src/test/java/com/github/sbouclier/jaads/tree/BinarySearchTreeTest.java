package com.github.sbouclier.jaads.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.github.sbouclier.jaads.tree.BinarySearchTree;

/**
 * BinarySearchTree test
 * 
 * @author Stéphane Bouclier
 *
 */
public class BinarySearchTreeTest {

	@Test
	public void should_search_value() {
		BinarySearchTree<String> tree = new BinarySearchTree<>();
		assertFalse(tree.search("B").isPresent());

		tree.addValue("B");
		tree.addValue("E");
		tree.addValue("C");
		tree.addValue("D");

		assertFalse(tree.search("A").isPresent());
		assertTrue(tree.search("B").isPresent());
		assertTrue(tree.search("C").isPresent());
		assertTrue(tree.search("D").isPresent());
		assertTrue(tree.search("E").isPresent());
	}

	@Test
	public void should_return_optional_empty_if_no_min_value() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();

		assertFalse(tree.getMinValue().isPresent());
		assertEquals(Optional.empty(), tree.getMinValue());
	}

	@Test
	public void should_return_min_value_if_only_root() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.addValue(8);

		assertEquals(8, tree.getMinValue().get().intValue());
	}

	@Test
	public void should_return_min_value_if_only_left_child() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.addValue(8);
		tree.addValue(4);

		assertEquals(4, tree.getMinValue().get().intValue());
	}

	@Test
	public void should_return_min_value_if_only_right_child() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.addValue(8);
		tree.addValue(9);

		assertEquals(8, tree.getMinValue().get().intValue());
	}

	public void should_return_min_value() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.addValue(8);
		tree.addValue(4);
		tree.addValue(6);
		tree.addValue(7);
		tree.addValue(2);
		tree.addValue(3);

		assertEquals(2, tree.getMinValue().get().intValue());
	}

	@Test
	public void should_return_optional_empty_if_no_max_value() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();

		assertFalse(tree.getMaxValue().isPresent());
		assertEquals(Optional.empty(), tree.getMaxValue());
	}

	@Test
	public void should_return_max_value_if_only_root() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.addValue(8);

		assertEquals(8, tree.getMaxValue().get().intValue());
	}

	@Test
	public void should_return_max_value_if_only_left_child() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.addValue(8);
		tree.addValue(4);

		assertEquals(8, tree.getMaxValue().get().intValue());
	}

	@Test
	public void should_return_max_value_if_only_right_child() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.addValue(8);
		tree.addValue(9);

		assertEquals(9, tree.getMaxValue().get().intValue());
	}

	public void should_return_max_value() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.addValue(8);
		tree.addValue(4);
		tree.addValue(6);
		tree.addValue(7);
		tree.addValue(9);
		tree.addValue(3);

		assertEquals(9, tree.getMaxValue().get().intValue());
	}

	@Test
	public void should_get_to_string() {
		BinarySearchTree<String> tree = new BinarySearchTree<>();
		tree.addValue("ABC");
		assertEquals("BinarySearchTree [root=BinaryNode [value=ABC, children.size=0]]", tree.toString());
	}
}

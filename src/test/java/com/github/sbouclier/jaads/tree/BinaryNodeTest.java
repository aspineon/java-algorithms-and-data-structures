package com.github.sbouclier.jaads.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.sbouclier.jaads.tree.BinaryNode;

/**
 * BinaryNode test
 * 
 * @author Stéphane Bouclier
 *
 */
public class BinaryNodeTest {

	@Test
	public void should_have_max_degree_set_to_2() {
		BinaryNode<String> node = new BinaryNode<>("ABC");
		assertEquals(2, node.getMaxDegree());
	}

	@Test
	public void should_check_node_without_left_child() {
		BinaryNode<Integer> nodeWithoutChild = new BinaryNode<>(1);
		assertFalse(nodeWithoutChild.getLeft().isPresent());

		BinaryNode<Integer> nodeWithoutChild2 = new BinaryNode<>(2);
		nodeWithoutChild2.setLeft(null);
		assertFalse(nodeWithoutChild2.getLeft().isPresent());
	}

	@Test
	public void should_retrieve_left_child() {
		BinaryNode<Integer> nodeWithOneChild = new BinaryNode<>(1);
		nodeWithOneChild.addChild(new BinaryNode<Integer>(2));
		assertTrue(nodeWithOneChild.getLeft().isPresent());
		assertEquals(2, nodeWithOneChild.getLeft().get().getValue().intValue());

		BinaryNode<Integer> nodeWithLeftChild = new BinaryNode<>(3);
		nodeWithLeftChild.setLeft(new BinaryNode<Integer>(4));
		assertTrue(nodeWithLeftChild.getLeft().isPresent());
		assertEquals(4, nodeWithLeftChild.getLeft().get().getValue().intValue());
	}

	@Test
	public void should_check_node_without_right_child() {
		BinaryNode<Integer> nodeWithoutChild = new BinaryNode<>(1);
		assertFalse(nodeWithoutChild.getRight().isPresent());

		BinaryNode<Integer> nodeWithoutChild2 = new BinaryNode<>(2);
		nodeWithoutChild2.setRight(null);
		assertFalse(nodeWithoutChild2.getRight().isPresent());
	}

	@Test
	public void should_retrieve_right_child() {
		BinaryNode<Integer> nodeWithOneChild = new BinaryNode<>(1);
		nodeWithOneChild.addChild(new BinaryNode<Integer>(2));
		assertFalse(nodeWithOneChild.getRight().isPresent());

		nodeWithOneChild.addChild(new BinaryNode<Integer>(3));
		assertEquals(3, nodeWithOneChild.getRight().get().getValue().intValue());

		BinaryNode<Integer> nodeWithRightChild = new BinaryNode<>(4);
		assertFalse(nodeWithRightChild.getRight().isPresent());
		nodeWithRightChild.setRight(new BinaryNode<Integer>(5));
		assertTrue(nodeWithRightChild.getRight().isPresent());
		assertEquals(5, nodeWithRightChild.getRight().get().getValue().intValue());

		BinaryNode<Integer> nodeWithLeftRightChild = new BinaryNode<>(6);
		nodeWithLeftRightChild.setLeft(new BinaryNode<Integer>(7));
		nodeWithLeftRightChild.setRight(new BinaryNode<Integer>(8));
		assertTrue(nodeWithLeftRightChild.getRight().isPresent());
		assertEquals(8, nodeWithLeftRightChild.getRight().get().getValue().intValue());
	}

	@Test
	public void should_get_to_string() {
		BinaryNode<String> node = new BinaryNode<>("ABC");
		node.addChild(new BinaryNode<String>("DEF"));

		assertEquals("BinaryNode [value=ABC, children.size=1]", node.toString());
	}
}

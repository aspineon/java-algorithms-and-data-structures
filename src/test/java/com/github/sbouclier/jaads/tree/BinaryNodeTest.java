package com.github.sbouclier.jaads.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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
	public void should_clone_nodes() throws CloneNotSupportedException {
		BinaryNode<Integer> node1 = new BinaryNode<>(1);
		BinaryNode<Integer> node2 = new BinaryNode<>(2);
		BinaryNode<Integer> node3 = new BinaryNode<>(3);
		BinaryNode<Integer> node4 = new BinaryNode<>(4);
		BinaryNode<Integer> node5 = new BinaryNode<>(5);
		
		node1.setLeft(node2);
		node1.setRight(node3);
		
		node2.setLeft(node4);
		node3.setRight(node5);
		
		@SuppressWarnings("unchecked")
		BinaryNode<Integer> cloneNode1 = (BinaryNode<Integer>) node1.clone();
		BinaryNode<Integer> cloneNode2 = (BinaryNode<Integer>) cloneNode1.getLeft().get();
		BinaryNode<Integer> cloneNode3 = (BinaryNode<Integer>) cloneNode1.getRight().get();
		BinaryNode<Integer> cloneNode4 = (BinaryNode<Integer>) cloneNode2.getLeft().get();
		BinaryNode<Integer> cloneNode5 = (BinaryNode<Integer>) cloneNode3.getRight().get();
		
		assertEquals(node1.value, cloneNode1.value);
		assertEquals(node2.value, cloneNode2.value);
		assertEquals(node3.value, cloneNode3.value);
		assertEquals(node4.value, cloneNode4.value);
		assertEquals(node5.value, cloneNode5.value);
		
		assertNotEquals(node1, cloneNode1);
		assertNotEquals(node2, cloneNode2);
		assertNotEquals(node3, cloneNode3);
		assertNotEquals(node4, cloneNode4);
		assertNotEquals(node5, cloneNode5);
	}

	@Test
	public void should_get_to_string() {
		BinaryNode<String> node = new BinaryNode<>("ABC");
		node.addChild(new BinaryNode<String>("DEF"));

		assertEquals("BinaryNode [value=ABC, children.size=1]", node.toString());
	}
}

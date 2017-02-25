package com.github.sbouclier.jaads;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Node test
 * 
 * @author Stéphane Bouclier
 *
 */
public class NodeTest {

	@Test
	public void should_retrieve_node_value() {
		Node<String> node = new Node<>("ABC");
		assertEquals("ABC", node.getValue());
	}

	@Test
	public void should_set_node_max_degree() {
		Node<Integer> node1 = new Node<>(65);
		Node<Integer> node2 = new Node<>(65, 8);

		assertEquals(Node.MAX_DEGREE, node1.getMaxDegree());
		assertEquals(8, node2.getMaxDegree());
	}

	@Test
	public void should_retrieve_node_degree() {
		Node<Integer> node = new Node<>(1);

		assertEquals(0, node.getDegree());

		node.addChild(new Node<Integer>(2));
		node.addChild(new Node<Integer>(3));
		node.addChild(new Node<Integer>(4));
		node.addChild(null);

		assertEquals(3, node.getDegree());
	}

	@Test
	public void should_filter_null_for_degrees() {
		Node<Integer> node = new Node<>(1);
		node.addChild(new Node<Integer>(2));
		node.addChild(null);

		assertEquals(2, node.children.size());
		assertEquals(1, node.getDegree());
	}

	@Test
	public void should_create_child_nodes() {
		Node<Integer> node = new Node<>(1);

		node.addChild(new Node<Integer>(2));
		node.addChild(new Node<Integer>(3));

		assertEquals(2, node.children.size());
		assertEquals(2, node.getChild(0).getValue().intValue());
		assertEquals(3, node.getChild(1).getValue().intValue());
	}

	@Test
	public void node_without_children_is_a_leaf() {
		Node<Integer> node1 = new Node<>(1);
		Node<Integer> node2 = new Node<>(2);
		Node<Integer> node3 = new Node<>(3);
		Node<Integer> node4 = new Node<>(4);

		node1.addChild(node2);
		node2.addChild(node3);
		node2.addChild(node4);
		node4.addChild(null);

		assertFalse(node1.isLeaf());
		assertFalse(node2.isLeaf());
		assertTrue(node3.isLeaf());
		assertTrue(node4.isLeaf());
	}

	@Test(expected = NodeOutOfBoundException.class)
	public void should_throw_exception_when_children_node_max_size_is_reached() {
		Node<Integer> node = new Node<>(1, 2);
		node.addChild(new Node<Integer>(2));
		node.addChild(new Node<Integer>(3));

		assertEquals(2, node.children.size());

		node.addChild(new Node<Integer>(4));
	}
	
	@Test
	public void should_get_to_string() {
		Node<String> node = new Node<>("ABC");
		node.addChild(new Node<String>("DEF"));

		assertEquals("Node [maxDegree=32767, value=ABC, children.size=1]", node.toString());
	}
}

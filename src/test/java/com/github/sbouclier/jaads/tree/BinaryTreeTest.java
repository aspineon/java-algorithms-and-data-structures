package com.github.sbouclier.jaads.tree;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * BinaryTree test
 * 
 * @author Stéphane Bouclier
 *
 */
public class BinaryTreeTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	private BinaryTree<String> tree;

	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));

		// level 1
		BinaryNode<String> root = new BinaryNode<>("F");

		// level 2
		BinaryNode<String> nodeB = new BinaryNode<>("B");
		BinaryNode<String> nodeG = new BinaryNode<>("G");
		root.setLeft(nodeB);
		root.setRight(nodeG);

		// level 3
		BinaryNode<String> nodeA = new BinaryNode<>("A");
		BinaryNode<String> nodeD = new BinaryNode<>("D");
		nodeB.addChild(nodeA);
		nodeB.addChild(nodeD);

		BinaryNode<String> nodeI = new BinaryNode<>("I");
		nodeG.addChild(nodeI);

		// level 4
		BinaryNode<String> nodeC = new BinaryNode<>("C");
		BinaryNode<String> nodeE = new BinaryNode<>("E");
		nodeD.addChild(nodeC);
		nodeD.addChild(nodeE);

		BinaryNode<String> nodeH = new BinaryNode<>("H");
		nodeI.addChild(nodeH);

		tree = new BinaryTree<>(root);
	}

	@After
	public void cleanUp() {
		System.setOut(null);
	}

	@Test
	public void should_get_height_equal_to_0_if_no_root() {
		BinaryTree<Integer> tree = new BinaryTree<>(null);
		assertEquals(0, tree.getHeight());
	}

	@Test
	public void should_get_height_equal_to_1_if_only_root() {
		BinaryTree<Integer> tree = new BinaryTree<>(new BinaryNode<>(3));
		assertEquals(1, tree.getHeight());
	}

	@Test
	public void should_get_height() {
		BinaryNode<Integer> root = new BinaryNode<>(1);
		BinaryNode<Integer> node2 = new BinaryNode<>(2);
		BinaryNode<Integer> node3 = new BinaryNode<>(3);
		BinaryNode<Integer> node4 = new BinaryNode<>(4);
		BinaryNode<Integer> node5 = new BinaryNode<>(5);

		BinaryTree<Integer> tree = new BinaryTree<>(root);
		root.setLeft(node2);
		assertEquals(2, tree.getHeight());

		root.setRight(node3);
		assertEquals(2, tree.getHeight());

		node2.setRight(node4);
		assertEquals(3, tree.getHeight());

		node4.setLeft(node5);
		assertEquals(4, tree.getHeight());
	}

	@Test
	public void test_traversal_order_strategy_enum() {
		assertEquals(
				Arrays.asList(BinaryTree.TraversalOrderStrategy.valueOf("PRE_ORDER"),
						BinaryTree.TraversalOrderStrategy.valueOf("IN_ORDER"),
						BinaryTree.TraversalOrderStrategy.valueOf("POST_ORDER")),
				Arrays.asList(BinaryTree.TraversalOrderStrategy.values()));
	}

	@Test
	public void should_traverse_tree_pre_order_in_depth() {
		final List<String> res = new ArrayList<>();
		tree.traverseDepthFirst(BinaryTree.TraversalOrderStrategy.PRE_ORDER, node -> res.add(node.getValue()));

		assertEquals("F,B,A,D,C,E,G,I,H", String.join(",", res));
	}

	@Test
	public void should_traverse_tree_in_order_in_depth() {
		final List<String> res = new ArrayList<>();
		tree.traverseDepthFirst(BinaryTree.TraversalOrderStrategy.IN_ORDER, node -> res.add(node.getValue()));

		assertEquals("A,B,C,D,E,F,H,I,G", String.join(",", res));
	}

	@Test
	public void should_traverse_tree_post_order_in_depth() {
		final List<String> res = new ArrayList<>();
		tree.traverseDepthFirst(BinaryTree.TraversalOrderStrategy.POST_ORDER, node -> res.add(node.getValue()));

		assertEquals("A,C,E,D,B,H,I,G,F", String.join(",", res));
	}

	@Test
	public void should_traverse_tree_in_breadth() {
		final List<String> res = new ArrayList<>();
		tree.traverseBreadthFirst(node -> res.add(node.getValue()));

		assertEquals("F,B,G,A,D,I,C,E,H", String.join(",", res));
	}

	@Test
	public void should_traverse_tree_in_breadth_and_return_empty_string_with_no_root() {
		final List<String> res = new ArrayList<>();

		BinaryTree<String> bt = new BinaryTree<>(null);
		bt.traverseBreadthFirst(node -> res.add(node.getValue()));

		assertEquals("", String.join(",", res));
	}

	@Test
	public void should_count_nodes() {
		assertEquals(0, new BinaryTree<>(null).countNodes());
		assertEquals(9, tree.countNodes());
	}

	@Test
	public void should_count_leafs() {
		assertEquals(0, new BinaryTree<>(null).countLeafs());
		assertEquals(1, new BinaryTree<>(new BinaryNode<Integer>(1)).countLeafs());
		assertEquals(4, tree.countLeafs());
	}

	@Test
	public void should_get_to_string() {
		BinaryTree<String> tree = new BinaryTree<>(new BinaryNode<>("ABC"));
		assertEquals("BinaryTree [root=BinaryNode [value=ABC, children.size=0]]", tree.toString());
	}
}

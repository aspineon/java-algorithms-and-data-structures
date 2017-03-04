package com.github.sbouclier.jaads.tree;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.sbouclier.jaads.tree.Node;
import com.github.sbouclier.jaads.tree.Tree;

/**
 * Tree test
 * 
 * @author Stéphane Bouclier
 *
 */
public class TreeTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void should_well_print_tree() {
		// level 1
		Node<String> root = new Node<>("1");

		// level 2
		Node<String> node11 = new Node<>("1.1");
		Node<String> node12 = new Node<>("1.2");
		root.addChild(node11);
		root.addChild(node12);

		// level 3
		Node<String> node121 = new Node<>("1.2.1");
		Node<String> node122 = new Node<>("1.2.2");
		Node<String> node123 = new Node<>("1.2.3");
		node12.addChild(node121);
		node12.addChild(node122);
		node12.addChild(node123);

		// level 4
		Node<String> node1221 = new Node<>("1.2.2.1");
		node122.addChild(node1221);

		Tree<String> tree = new Tree<>(root);
		tree.print();

		StringBuilder expectedOut = new StringBuilder("1").append("\n");
		expectedOut.append("├── 1.1").append("\n");
		expectedOut.append("└── 1.2").append("\n");
		expectedOut.append("    ├── 1.2.1").append("\n");
		expectedOut.append("    ├── 1.2.2").append("\n");
		expectedOut.append("    │   └── 1.2.2.1").append("\n");
		expectedOut.append("    └── 1.2.3").append("\n");

		assertEquals(expectedOut.toString(), outContent.toString());
	}

	@Test
	public void should_print_nothing_without_node() {
		Tree<String> tree = new Tree<>(null);
		tree.print();

		assertEquals("", outContent.toString());
	}

	@Test
	public void should_get_to_string() {
		Node<Integer> root = new Node<>(1);
		Tree<Integer> tree = new Tree<>(root);

		assertEquals("Tree [root=Node [maxDegree=32767, value=1, children.size=0]]", tree.toString());
	}
}

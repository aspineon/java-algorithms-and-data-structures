package com.github.sbouclier.jaads.tree;

/**
 * A tree is a hierarchical data without cycle which contains nodes. The first
 * tree node (without parent) is called 'root node'.
 * 
 * @author Stéphane Bouclier
 *
 * @param <T>
 *            type of tree node value
 */
public class Tree<T> {

	protected Node<T> root;

	public Tree(Node<T> root) {
		this.root = root;
	}

	public void print() {
		print(root, "", true);
	}

	/**
	 * Recursive algorithm for printing nodes
	 * 
	 * @param node
	 *            current node
	 * @param prefix
	 *            to print before node value
	 * @param isLastNode
	 *            last node of parent node
	 */
	private void print(Node<T> node, String prefix, boolean isLastNode) {
		if (node != null) {
			String nextPrefix = prefix + (isLastNode ? "    " : "│   ");

			if (node == root) {
				prefix = nextPrefix = "";
				System.out.println(prefix + node.value);
			} else {
				System.out.println(prefix + (isLastNode ? "└── " : "├── ") + node.value);
			}

			// print all nodes except last
			for (int i = 0; i < node.children.size() - 1; i++) {
				print(node.children.get(i), nextPrefix, false);
			}

			// print last node
			if (node.children.size() > 0) {
				print(node.children.get(node.children.size() - 1), nextPrefix, true);
			}
		}
	}

	@Override
	public String toString() {
		return "Tree [root=" + root + "]";
	}
}

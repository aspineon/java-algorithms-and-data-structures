package com.github.sbouclier.jaads.tree;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * A binary tree is a tree where each node has at most 2 child nodes, usually
 * called left node and right node.
 * 
 * @author Stéphane Bouclier
 *
 * @param <T>
 *            type of binary tree node value
 */
public class BinaryTree<T> extends Tree<T> {

	public enum TraversalOrderStrategy {
		PRE_ORDER, IN_ORDER, POST_ORDER
	}

	// ----------------
	// - CONSTRUCTORS -
	// ----------------

	public BinaryTree(BinaryNode<T> root) {
		super(root);
	}

	// -----------
	// - METHODS -
	// -----------

	/**
	 * Traverse the tree and apply an action according to a strategy
	 * 
	 * @param traverseOrderstrategy
	 *            pre/in/post order strategy
	 * @param action
	 *            to apply
	 */
	public void traverse(TraversalOrderStrategy orderStrategy, Consumer<BinaryNode<T>> action) {
		traverse(Optional.ofNullable((BinaryNode<T>) root), orderStrategy, action);
	}

	private void traverse(Optional<BinaryNode<T>> node, TraversalOrderStrategy orderStrategy,
			Consumer<BinaryNode<T>> action) {
		if (node.isPresent()) {

			if (orderStrategy == TraversalOrderStrategy.PRE_ORDER) {
				action.accept(node.get());
			}

			traverse(node.get().getLeft(), orderStrategy, action);

			if (orderStrategy == TraversalOrderStrategy.IN_ORDER) {
				action.accept(node.get());
			}

			traverse(node.get().getRight(), orderStrategy, action);

			if (orderStrategy == TraversalOrderStrategy.POST_ORDER) {
				action.accept(node.get());
			}
		}
	}

	public int getHeight() {
		return getHeight(Optional.ofNullable((BinaryNode<T>) root));
	}

	private int getHeight(Optional<BinaryNode<T>> node) {
		if (!node.isPresent()) {
			return 0;
		} else {
			return 1 + Math.max(getHeight(node.get().getLeft()), getHeight(node.get().getRight()));
		}
	}

	public int countNodes() {
		return countNodes(Optional.ofNullable((BinaryNode<T>) root));
	}

	private int countNodes(Optional<BinaryNode<T>> node) {
		if (!node.isPresent()) {
			return 0;
		} else {
			return countNodes(node.get().getLeft()) + countNodes(node.get().getRight()) + 1;
		}
	}

	public int countLeafs() {
		return countLeafs(Optional.ofNullable((BinaryNode<T>) root));
	}

	private int countLeafs(Optional<BinaryNode<T>> node) {
		if (!node.isPresent()) {
			return 0;
		} else {
			final BinaryNode<T> n = node.get();
			if (!n.getLeft().isPresent() && !n.getRight().isPresent()) {
				return 1;
			} else {
				return countLeafs(n.getLeft()) + countLeafs(n.getRight());
			}
		}
	}

	// -------------
	// - TO STRING -
	// -------------

	@Override
	public String toString() {
		return "BinaryTree [root=" + root + "]";
	}
}

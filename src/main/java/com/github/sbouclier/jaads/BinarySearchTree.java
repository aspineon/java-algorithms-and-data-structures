package com.github.sbouclier.jaads;

import java.util.Optional;

/**
 * A binary search tree is a binary tree where each left node is lower than
 * right node.
 * 
 * @author Stéphane Bouclier
 *
 * @param <T>
 *            type of binary tree node value
 */
class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public BinarySearchTree() {
		super(null);
	}

	public void addValue(T value) {
		if (root == null) {
			root = new BinaryNode<T>(value);
		} else {
			insert((BinaryNode<T>) root, value);
		}
	}

	private void insert(BinaryNode<T> node, T value) {
		if (value.compareTo(node.value) < 0) {
			if (node.getLeft().isPresent()) {
				insert(node.getLeft().get(), value);
			} else {
				node.setLeft(new BinaryNode<T>(value));
			}
		}

		if (value.compareTo(node.value) > 0) {
			if (node.getRight().isPresent()) {
				insert(node.getRight().get(), value);
			} else {
				node.setRight(new BinaryNode<T>(value));
			}
		}
	}

	public Optional<T> search(T value) {
		return search(Optional.ofNullable((BinaryNode<T>) root), value);
	}

	private Optional<T> search(Optional<BinaryNode<T>> node, T value) {
		if (!node.isPresent()) {
			return Optional.empty();
		} else if (node.get().value.compareTo(value) == 0) {
			return Optional.of(value);
		} else if (value.compareTo(node.get().value) < 0) {
			return search(node.get().getLeft(), value);
		} else {
			return search(node.get().getRight(), value);
		}
	}

	public Optional<T> getMinValue() {
		return getMinValue(Optional.ofNullable((BinaryNode<T>) root));
	}

	private Optional<T> getMinValue(Optional<BinaryNode<T>> srcNode) {
		Optional<T> minValue;

		if (!srcNode.isPresent()) {
			minValue = Optional.empty();
		} else if (!srcNode.get().getLeft().isPresent()) {
			minValue = Optional.of(srcNode.get().getValue());
		} else {
			minValue = getMinValue(srcNode.get().getLeft());
		}

		return minValue;
	}

	public Optional<T> getMaxValue() {
		return getMaxValue(Optional.ofNullable((BinaryNode<T>) root));
	}

	private Optional<T> getMaxValue(Optional<BinaryNode<T>> srcNode) {
		Optional<T> maxValue;

		if (!srcNode.isPresent()) {
			maxValue = Optional.empty();
		} else if (!srcNode.get().getRight().isPresent()) {
			maxValue = Optional.of(srcNode.get().getValue());
		} else {
			maxValue = getMaxValue(srcNode.get().getRight());
		}

		return maxValue;
	}

	// -------------
	// - TO STRING -
	// -------------

	@Override
	public String toString() {
		return "BinarySearchTree [root=" + root + "]";
	}
}

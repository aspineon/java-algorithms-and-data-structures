package com.github.sbouclier.jaads.tree;

import java.util.Optional;

/**
 * A binary node is a node whith at most 2 child nodes
 * 
 * @author Stéphane Bouclier
 *
 * @param <T>
 *            type of binary node value
 */
public class BinaryNode<T> extends Node<T> implements Cloneable {

	public BinaryNode(T value) {
		super(value, 2);
	}

	public Optional<BinaryNode<T>> getLeft() {
		return Optional.ofNullable((BinaryNode<T>) (children.size() > 0 ? children.get(0) : null));
	}

	public Optional<BinaryNode<T>> getRight() {
		return Optional.ofNullable((BinaryNode<T>) (children.size() > 1 ? children.get(1) : null));
	}

	public void setLeft(Node<T> node) {
		children.add(0, node);
	}

	public void setRight(Node<T> node) {
		// if there is no left node, we need to allocate left node with a
		// null value to have the right node indexed to 1
		if (children.isEmpty()) {
			addChild(null);
		}
		children.add(1, node);
	}

	@SuppressWarnings("unchecked")
	public Object clone() throws CloneNotSupportedException {
		BinaryNode<T> cloneNode = new BinaryNode<T>(value);

		if (getLeft().isPresent()) {
			cloneNode.setLeft((BinaryNode<T>) getLeft().get().clone());
		}

		if (getRight().isPresent()) {
			cloneNode.setRight((BinaryNode<T>) getRight().get().clone());
		}

		return cloneNode;
	}

	@Override
	public String toString() {
		return "BinaryNode [value=" + value + ", children.size=" + children.size() + "]";
	}
}

package com.github.sbouclier.jaads;

import java.util.ArrayList;
import java.util.List;

/**
 * A node can have child nodes. A node without children is called 'leaf'. A node
 * without parent node is called 'root'
 * 
 * @author Stéphane Bouclier
 *
 * @param <T>
 *            type of node value
 */
public class Node<T> {
	public static short MAX_DEGREE = Short.MAX_VALUE;

	protected final int maxDegree;
	protected T value;
	protected List<Node<T>> children = new ArrayList<Node<T>>();

	// ----------------
	// - CONSTRUCTORS -
	// ----------------

	public Node(T value, int maxDegree) {
		this.value = value;
		this.maxDegree = maxDegree;
	}

	public Node(T value) {
		this(value, MAX_DEGREE);
	}

	// -----------
	// - METHODS -
	// -----------

	public void addChild(Node<T> node) {
		synchronized (this.children) {
			if (this.children.size() == maxDegree) {
				throw new NodeOutOfBoundException(maxDegree);
			} else {
				this.children.add(node);
			}
		}
	}

	public Node<T> getChild(int index) {
		return children.get(index);
	}

	public int getDegree() {
		if (children.isEmpty()) {
			return 0;
		} else {
			return (int) children.stream().filter(n -> n != null).count();
		}
	}

	public boolean isLeaf() {
		if (children.isEmpty()) {
			return true;
		} else {
			return !(children.stream().filter(n -> n != null).count() > 0);
		}
	}

	// -------------
	// - TO STRING -
	// -------------

	@Override
	public String toString() {
		return "Node [maxDegree=" + maxDegree + ", value=" + value + ", children.size=" + children.size() + "]";
	}

	// -------------------
	// - SETTERS/GETTERS -
	// -------------------

	public T getValue() {
		return value;
	}

	public int getMaxDegree() {
		return maxDegree;
	}
}

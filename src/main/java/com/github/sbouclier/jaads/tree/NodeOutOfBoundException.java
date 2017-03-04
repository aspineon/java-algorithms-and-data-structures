package com.github.sbouclier.jaads.tree;

/**
 * Exception launched when adding a node into another node exceed the max degree
 * of the parent node
 * 
 * @author Stéphane Bouclier
 *
 */
public class NodeOutOfBoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NodeOutOfBoundException(int maxSize) {
		super("Max children node size exceeded: " + maxSize);
	}
}

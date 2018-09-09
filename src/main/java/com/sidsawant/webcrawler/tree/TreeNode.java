package com.sidsawant.webcrawler.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeNode<String> {

	private TreeNode<String> node;
	private String content;

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	private List<TreeNode<String>> children;

	/**
	 * @return the children
	 */
	public List<TreeNode<String>> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<TreeNode<String>> children) {
		this.children = children;
	}

	/**
	 * @return the node
	 */
	public TreeNode<String> getNode() {
		return node;
	}

	/**
	 * @param node
	 *            the node to set
	 */
	public void setNode(TreeNode<String> node) {
		this.node = node;
	}

	public TreeNode(TreeNode<String> parent, String content) {
		this.node = parent;
		this.content = content;
		if (parent != null) {
			// if it's a Collections.emptyList(), then we need to make it a list we can add
			// to
			if (parent.children == Collections.EMPTY_LIST) {
				parent.children = new ArrayList<>();
			}
			parent.children.add(this);
		}else {
			children = Collections.EMPTY_LIST;
		}

		

	}

	public void display() {

	}
}

package com.sidsawant.webcrawler.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class TreeNode<String> {

	private static final Logger LOGGER = Logger.getLogger(TreeNode.class.getName());
	
	
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

	

	
	
	public TreeNode(String content) {
		this.content = content;
		children = new ArrayList<TreeNode<String>>();
	}
	
	

	public void display() {
		
		for(int i=0;i<children.size();i++) {
			System.out.println(children.get(i).content);
			if(children.get(i).children.size()>0) {
				children.get(i).display();
			}
		}

	}
}

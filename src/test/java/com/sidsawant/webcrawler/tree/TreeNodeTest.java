package com.sidsawant.webcrawler.tree;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TreeNodeTest {
	
	TreeNode<String> treeNode ;

	@Before
	public void setUp() throws Exception {
	
		//TODO REMOVE string constants
		treeNode = new TreeNode<>(null, "root");
		TreeNode<String> firtChild = new TreeNode<>(treeNode,"firstChild");
		//TreeNode<String> secondChild = new TreeNode<>(firtChild,"secondChilde");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testDisplay() {
		assert (treeNode.getChildren().size() > 0);
		
	}

}

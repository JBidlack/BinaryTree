/*
 * John Bidlack
 * CMSC 350 7380
 * 11/27/21
 * 
 * Purpose: framework to create and read a binary tree to tell if it's full, proper, 
 * count its nodes and height, and show its inorder format
 */
package cmsc350Project3;

import java.util.Stack;


public class BinaryTree {

	static class Tree{
		Node root;
		Node node;
		StringBuffer str = new StringBuffer();
		StringBuffer treeOut = new StringBuffer();
		private int count = 0;
		Stack<Character> paren = new Stack<Character>();
		Stack<Character> tree = new Stack<Character>();
		
		//constructor method
		public Tree() {
			
		}
		
		//setters and getters
		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
		
		//method to take the user input and add it to a stack
		public void constructTree(String input) {
			
			for(int i = input.length()-1; i>=0; i--) {
				tree.push(input.charAt(i));
			}
			root = makeTree(tree);
		}
		
		//method to create the binary tree using recursion
		// to direct each node based on its parentheses values
		public Node makeTree(Stack<Character> c) {
			while(!c.isEmpty()) {
				if(Character.isLetter(c.peek()) || 
						Character.isDigit(c.peek())) {
					throw new InvalidTreeSyntax("Invalid format");
				}
				if (c.peek() != '(') {
					return null;
				}
				c.pop();
				if(c.peek() =='(' || c.peek()==')') {
					throw new InvalidTreeSyntax("Node value expected");
				}
				String nodeValue = Character.toString(c.pop());
				Node left = makeTree(c);
				Node right = makeTree(c);
				if(c.peek() != ')') {
					throw new InvalidTreeSyntax("')' expected");
				}
				c.pop();

				return new Node(nodeValue, left, right);
				
			}
			return null;
		}
		
		// method to print the tree in inorder format using recursion.
		//this also serves to display the fully constructed 
		//tree when completed
		public String inOrder() {
			if (root == null) {
				return "";
			}
			else {
			return inOrder(root);
			}
		}
		// private overloaded class to allow the public class some level of security
		private String inOrder(Node node) {

			if(node == null) {
				return "";
			}
			
			if(node !=null) {
				str.append("(");
				inOrder(node.leftNode);
				str.append(node.data);
				inOrder(node.rightNode);
				str.append(")");
			}
			return str.toString();
		}
		
		//public method to count the number of nodes through recursion		
		public String numNodes() {
			return numNodes(root) + " Nodes";
		}
		
		//private method to count the number of nodes through recursion
		private String numNodes(Node node) {
						
			if(node == null) {
				return null;
			}
			
			if(node !=null) {
				numNodes(node.leftNode);
				count += 1;
				numNodes(node.rightNode);
			}
			return Integer.toString(count);
		}
		
		// public method to find the max height of the tree
		public int height() {
			return height(root);
		}
		
		// private method to use recursion to count the height of each side
		// of the tree and return the largest number
		private int height(Node node) {
			if (node == null) {
				return -1;
			}
			else {
				int lHeight = height(node.leftNode)+1;
				int rHeight = height(node.rightNode)+1;
				return Math.max(lHeight,  rHeight);
			}
		}
		
		//public method to tell if the tree is full
		public String isFull() {
			String answer = "";
			if(isFull(root) == true) {
				answer = "The tree is full.";
			}
			else if(isFull(root) == false) {
				answer = "The tree is not full.";
			}
			return answer;
		}
		
		// private method to tell if the tree is full. 
		// since a tree can have (2^(height+1))-1 nodes
		// if the number of nodes in the tree is not equal to 
		// the total possible nodes, it is not full
		private boolean isFull(Node node) {
			int full = (int) (Math.pow(2, height()+1)-1);
			int count = Integer.parseInt(numNodes());
			boolean valid = false;
			if (node == null) {
				valid = true;
			}
			if (full == count) {
				valid = true;
			}
			else if (full != count){
				valid = false;
			}
			return valid;
		}
		
		//public method to tell if the tree is balanced
		public String isBalanced() {
			String answer = "";
			if (isBalanced(root) == true) {
				answer = "The tree is balanced";
			}
			if (isBalanced(root) == false) {
				answer = "The tree is not balanced";
			}
			return answer;
		}
		
		//private method using recursion to tell
		// if the tree is balanced
		private boolean isBalanced(Node node) {
			
			if(node == null) {
				return true;
			}
			
			return Math.abs(balHeight(node.leftNode)-balHeight(node.rightNode))<2;
		}
		
		// method to use recursion to find if the difference between each subtree is more than 1
		private int balHeight(Node node) {
			if (node == null) {
				return -1;
			}
			int height = 1+ Math.max(height(node.leftNode), height(node.rightNode));
			return height;
		}
		
		// method to tell if a tree is proper
		public String isProper() {
			String answer = "";
			if (isProper(root) == true) {
				answer = "The tree is proper";
			}
			if (isProper(root)== false) {
				answer = "The tree is not proper";
			}
			
			return answer;
		}
		
		//private overloaded method to use recursion
		// to tell if a tree is proper
		private boolean isProper(Node node) {
			if (node == null) {
				return true;
			}
			if (node.leftNode == null && node.rightNode == null) {
				return true;
			}
			if (node.leftNode != null && node.rightNode != null) {
				return (isProper(node.leftNode) && 
						isProper(node.rightNode));
			}
			if (node.leftNode != null && node.rightNode == null) {
				return false;
			}
			return false;
		}
	}
}

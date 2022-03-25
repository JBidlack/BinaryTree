/*
 * John Bidlack
 * CMSC 350 7380
 * 11/27/21
 * 
 * Purpose: act as a constructor for a binary tree node
 */
package cmsc350Project3;

class Node {
	String data;
	Node leftNode;
	Node rightNode;
	
	public Node () {
		
	}
	
	public Node(String data) {
		this.data = data;
	}
	public Node(String data, Node leftNode, Node rightNode) {
		this.data = data;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}
	
}

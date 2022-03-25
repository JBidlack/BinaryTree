/*
 * John Bidlack
 * CMSC 350 7380
 * 11/27/21
 * 
 * Purpose: GUI to create and read a binary tree to tell if it's full, proper, 
 * count its nodes and height, and show its inorder format
 */
package cmsc350Project3;

import javax.swing.*;
import java.awt.event.*;

public class Main {
	
	// create the base framework of the GUI
	static class BinaryTreeGUI extends JFrame{

		private static final long serialVersionUID = 1L;
		JFrame window= new JFrame("Binary Tree Categorizer");
		JPanel panel = new JPanel();
		JButton makeTree = new JButton("Make Tree");
		JButton isBalanced = new JButton("Is Balanced?");
		JButton isFull = new JButton("Is Full?");
		JButton isProper = new JButton("Is Proper?");
		JButton height = new JButton("Height");
		JButton nodes = new JButton("Nodes");
		JButton inOrder = new JButton("Inorder");
		static JTextField inputTxt = new JTextField(20);
		static JTextField outputTxt = new JTextField(20);
		JLabel result = new JLabel("Result:");
		JLabel enter = new JLabel("Enter Expression:");
		
		// create constructor
		public BinaryTreeGUI() {		
				
			// set the layout and add buttons to the JPanel
			panel.setLayout(null);
			panel.add(makeTree);
			panel.add(isBalanced);
			panel.add(isFull);
			panel.add(isProper);
			panel.add(height);
			panel.add(nodes);
			panel.add(inOrder);
			panel.add(inputTxt);
			panel.add(outputTxt);
			panel.add(enter);
			panel.add(result);
			
			//set size and location of buttons
			makeTree.setBounds(50, 70, 110, 40);
			isBalanced.setBounds(170, 70, 110, 40);
			isFull.setBounds(290, 70, 110, 40);
			isProper.setBounds(410, 70, 110, 40);
			height.setBounds(530, 70, 110, 40);
			nodes.setBounds(650, 70, 110, 40);
			inOrder.setBounds(770, 70, 110, 40);
			
			inputTxt.setBounds(140,20,760,30);
			outputTxt.setBounds(120,140,760,30);
			outputTxt.setEditable(false);
			enter.setBounds(30, 20, 250, 30);
			result.setBounds(60,140,850,30);
			
									
			// call an instance of the lower event class to add Action listeners to the buttons
			event action = new event();
			makeTree.addActionListener(action);
			isBalanced.addActionListener(action);
			isFull.addActionListener(action);
			isProper.addActionListener(action);
			height.addActionListener(action);
			nodes.addActionListener(action);
			inOrder.addActionListener(action);
			
			
		} // end constructor class
	
	
	// set action listeners
	static class event implements ActionListener{
		BinaryTree.Tree tree = new  BinaryTree.Tree();
		
		@Override
		public void actionPerformed(ActionEvent convertAction) {
			
			// create a string to read the actionCommand of the action taken
			String operator = convertAction.getActionCommand();
			
			//use an if loop to direct the users choice to the proper direction
			if (operator.equals("Make Tree")) {
				try {
					String input = inputTxt.getText();
					tree.constructTree(input);
					tree.str.delete(0, tree.str.length());
					outputTxt.setText(tree.inOrder());
				} 
				catch(InvalidTreeSyntax e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}				
			}	
			else if (operator.equals("Nodes")) {
				try {
					tree.setCount(0);
					outputTxt.setText(tree.numNodes());
				}
				catch(InvalidTreeSyntax e) {
					
				}
			}
			
			else if (operator.equals("Inorder")) {
				try {
					tree.str.delete(0, tree.str.length());
					outputTxt.setText(tree.inOrder());
				}
				catch(InvalidTreeSyntax e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			
			else if (operator.equals("Height")) {
				try {
					outputTxt.setText(Integer.toString(tree.height()));
				}
				catch(InvalidTreeSyntax e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			else if (operator.equals("Is Full?")) {
				try {
					outputTxt.setText(tree.isFull());
				}
				catch(InvalidTreeSyntax e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			else if (operator.equals("Is Balanced?")) {
				try {
					outputTxt.setText(tree.isBalanced());
				}
				catch(InvalidTreeSyntax e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			else if (operator.equals("Is Proper?")) {
				try {
					outputTxt.setText(tree.isProper());
				}
				catch(InvalidTreeSyntax e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			
			
			
		} // end action performed method
	
	} // end event class

	}	
	
	// Main method
		public static void main(String[] args) {
			BinaryTreeGUI GUI = new BinaryTreeGUI();
			
			//add the JPanel
			GUI.window.add(GUI.panel);
			
			
			// set the GUI attributes
			GUI.window.setSize(950, 230);
			GUI.window.setLocation(500, 300);
			GUI.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			GUI.window.setVisible(true);
		}
	
	}



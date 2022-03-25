/*
 * John Bidlack
 * CMSC 350 7380
 * 11/27/21
 * 
 * Purpose: custom exception for binary tree formatting
 * 
 */
package cmsc350Project3;

public class InvalidTreeSyntax extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidTreeSyntax(String message) {
		super(message);
	}
}

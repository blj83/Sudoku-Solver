import java.awt.GridLayout;

import javax.swing.JFrame;

/*******************************************************
 * This is the main class for the project that contains 
 * the main method for the program. It creates a new 
 * JFrame and calls the constructor for the main panel
 * for the the interface. 
 *******************************************************/

public class SudokuSolver {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Sudoku Solver");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SudokuTopPanel top = new SudokuTopPanel();
		//SudokuPanel panel = new SudokuPanel();
		
		//frame.getContentPane().add(panel);
		frame.getContentPane().add(top);
		frame.pack();
		frame.setVisible(true);
	}

	
}

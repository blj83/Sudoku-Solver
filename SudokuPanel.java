import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*************************************************************
 * 
 * The main panel for the project. It contains all the gui
 * objects the user will interact with including the JButtons
 * and JTextFields that represent a Sudoku board. 
 * 
 *************************************************************/

public class SudokuPanel extends JPanel {
	
	/*
	 * Local variables needed for the class
	 */
	
	private JTextField[][] board;
	private int[][] numBoard;
	
	/*
	 * The Constructor for the class it will initialize the local 
	 * variables. After it initializes the variables, it will add
	 * action listeners to the buttons. It will then add the gui
	 * components in the correct order in order to construct the 
	 * interface. A for loop is used to generate an array of 
	 * JTextFields that will serve as the board. Finally it will
	 * set the desired size and background color.
	 */
	
	public SudokuPanel(){
		board = new JTextField[9][9];
		numBoard = new int[9][9];
		
		GridLayout grid = new GridLayout(9,9,8,8);
		setLayout(grid);
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				board[i][j] = new JTextField();;
				board[i][j].setColumns(1);
				add(board[i][j]);
			}
		}
		
		setPreferredSize(new Dimension(300, 350));
		setBackground(Color.red);
		
	}
	
	/*
	 *	Getter method that will return the reference to the board
	 * variable 
	 */
	
	public JTextField[][] getBoard(){
		return board;
	}
	
	/*
	 * Will return the reference to the numBoard variable
	 */
	
	public int[][] getNumBoard(){
		return numBoard;
	}
	
	/*
	 * Will draw black lines in between the 3x3 boxes
	 */
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(100,0,100,350);
        g.drawLine(200,0,200,350);
        g.drawLine(0,113,300,113);
        g.drawLine(0,230,300,230);
	}
}

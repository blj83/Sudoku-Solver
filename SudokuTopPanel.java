import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;


/*
 * This panel is used to separate the gui objects added into the 
 * main panel. It will create a thin black line that is used to
 * separate the JButton and the JTextFields
 */


public class SudokuTopPanel extends JPanel {
	
	private JTextField[][] board;
	private JButton start, clear;
	private SudokuPanel main;
	
	public SudokuTopPanel(){
		
		main = new SudokuPanel();
		board = main.getBoard();
		start = new JButton("Start");
		clear = new JButton("Clear");
		
		start.addActionListener(new startListener());
		clear.addActionListener(new clearListener());
		
		add(start);
		add(clear);
		add(main);
		setBackground(Color.black);
		setPreferredSize(new Dimension(300, 400));
	}
	
	/*
	 * The listener for the start button. When the button is
	 * pressed it will form a multi-demensional array of ints
	 * that will represent the numbers entered in the text
	 * fields. It will then call the recursive method solve 
	 * that will throw an exception if a solution is found.
	 * It will then call a method to place the numbers on the 
	 * board in the interface.
	 */
	
	private class startListener implements ActionListener{
		public void actionPerformed (ActionEvent event){
			start.setSelected(true);
			makeNumBoard();
			try{
		        solve(0,0);
		    }
		    catch(Exception e){
		    }
		    placeNumbers();
		}
	}
	
	/*
	 * The listener for the clear button. It will call methods that
	 * clear the interface board and the int representation of it.
	 */
	
	private class clearListener implements ActionListener{
		public void actionPerformed (ActionEvent event){
			clearBoard();
			clearNumBoard();
		}
	}
	
	/*
	 * This method has a loop that will loop through all the elements of
	 * numBoard. For each element it will look at the text in the text
	 * field. If there is nothing there it will set that element in the 
	 * int array equal to 0. If there is something in the text field it 
	 * will set the int array value equal to the number.
	 */
	
	public void makeNumBoard(){
		for(int i = 0; i < main.getNumBoard().length; i++){
			for(int j = 0; j< main.getNumBoard().length; j++){
				if ((board[i][j].getText()).equalsIgnoreCase("")){
					main.getNumBoard()[i][j] = 0;
				}
				else{
					main.getNumBoard()[i][j] = Integer.parseInt(board[i][j].getText());
				}
			}
		}
	}
	
	/*
	 * Method that loops through all the elements in the array that are
	 * in the row indicated by the first parameter and checks to see if
	 * it finds any of the elements are equal to the number entered as 
	 * the second parameter. Returns false if it finds one, true if it 
	 * doesn't.
	 */
	
	public boolean checkRow(int row, int num){
		for( int col = 0; col < 9; col++ ){
	         if (main.getNumBoard()[row][col] == num ){
	            return false ;
	         }
		}
		return true;
	}
	
	/*
	 * Method that loops through all the elements in the array that are
	 * in the column indicated by the first parameter and checks to see if
	 * it finds any of the elements are equal to the number entered as 
	 * the second parameter. Returns false if it finds one, true if it 
	 * doesn't.
	 */
	
	public boolean checkCol(int col, int num){
		for(int row = 0; row < 9; row++){
			if (main.getNumBoard()[row][col] == num){
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Method that loops through all the elements in the array that are
	 * in the same box. It decides what box the loop checks by taking the 
	 * row and column indicated by the first two parameters and finding the
	 * mod and subtracts it from the original value. It will then loop 
	 * through the values in the box and check to see if it finds the 
	 * number indicated by the third parameter. Returns false if it finds 
	 * the number, true if it doesn't.
	 */
	
	public boolean checkBox(int row, int col, int num){
		row = row - (row % 3);
		col = col - (col % 3);
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				if(main.getNumBoard()[row + i][col + j] == num){
					return false;
				}
			}
		}
		return true;
		
	}
	
	/*
	 * The recursive method. It takes two parameters that represent the
	 * row and the column. The base case is if the value for row is greater 
	 * than eight than a solution has been found and it throws an exception 
	 * that exits the recursive call. It will then go through and check if the 
	 * current value is full. If it is it checks to see if the column is greater
	 * than 8 and calls the appropriate recursive call. If it is empty it will
	 * go through a loop the first value that is found that is not in the same
	 * row, column, or box it will assign it and determine the next appropriate 
	 * recursive call. If a valid number is not found then it will backtrack and 
	 * find the next valid number for the one before.
	 */
	
	public void solve(int row, int col) throws Exception{
		
		if (row > 8){
			start.setSelected(false);
			throw new Exception("Solution found");
		}
		
			if (main.getNumBoard()[row][col] != 0){
				if(col < 8){
			         solve(row, col + 1);
			      }
				else{
			         solve(row + 1, 0);
			    }
			}
			
			else{
				for(int num = 1; num < 10; num++){
		            if(checkRow(row,num) && checkCol(col,num) && checkBox(row,col,num)){
		            	main.getNumBoard()[row][col] = num;
		            	if(col < 8){
		            		solve(row, col + 1);
		            	}
		      	      	else{
		      	      		solve(row + 1, 0);
		      	      	}
		            }
		            
		         }
				main.getNumBoard()[row][col] = 0;
			}
	}
	
	/*
	 * This is a method that goes through the elements of the
	 * board array and prints the number represented by the 
	 * value in the int array of the board in the interface.
	 */
	
	public void placeNumbers(){
		String temp;
		for (int i = 0; i < board.length ; i++){
			for(int j = 0; j < board.length; j++){
				temp = "" + main.getNumBoard()[i][j];
				board[i][j].setText(temp);
			}
		}
	}
	
	/*
	 * This method will go through the elements of the board 
	 * array and sets the text in the text field to blank.
	 */
	
	public void clearBoard(){
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				board[i][j].setText("");				
			}
		}
	}
	
	/*
	 * This method will go through all the elements of main.getNumBoard()
	 * and set them equal to 0;
	 */
	
	public void clearNumBoard(){
		for(int i = 0; i < main.getNumBoard().length; i++){
			for(int j = 0; j< main.getNumBoard().length; j++){				
				main.getNumBoard()[i][j] = 0;
			}
		}
	}
	
	
}
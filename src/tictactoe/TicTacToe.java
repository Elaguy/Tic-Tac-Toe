package tictactoe;

import java.util.Scanner;

/*
 * Handles all of the Tic Tac Toe stuff (the board, the marks, etc),
 * and handles the human player.
 * 
 */

public class TicTacToe {
	
	TicTacToeAI tictactoeai;
	
	Scanner scanner = new Scanner(System.in);
	
	private String[][] board;
	private String playerMark;
	private String opponentMark;
	
	private int rows;
	private int cols;
	
	private boolean gameRunning;
	private boolean markChosen;
	
    public TicTacToe() {
        init();
        initBoard();
    }

	public void init() { // initializes the variables 
		tictactoeai = new TicTacToeAI(this);
		board = new String[3][3]; // initializes 3 x 3 array
		
		rows = board.length;
		cols = board[0].length;
		
		gameRunning = true;
		playerMark = " ";
		markChosen = false;
	}
	
	public void initBoard() { // Sets up the board
		
		if((rows != 3) && (cols != 3)) {
			System.err.println("ERROR: Board must have 3 rows and 3 columns");
		}
		
		else {
			for(int i = 0; i < board.length; i++) { // rows
				for(int j = 0; j < board[i].length; j++) { // columns
					board[i][j] = " ";
				}
			}
		}
	}
	
	public void displayBoard() { // prints the boards
		System.out.println("-------------");
		
		for(int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for(int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	
	public void addMark(int pos1, int pos2) { // adds a mark to the board
		int addPos1 = pos1 - 1;
		int addPos2 = pos2 - 1;
		if((addPos1 < 3) && (addPos1 >= 0) && (addPos2 < 3) && (addPos2 >= 0)) {
			if((checkExisting(addPos1, addPos2) != true)) {
				board[addPos1][addPos2] = playerMark;
			}
		}
		else {
			System.out.println("That spot already has a mark, or is out of the board's boundaries.");
		}
	}
	
	public boolean checkExisting(int pos1, int pos2) { // checks if an index already has a mark in it
		boolean isTrue = false;
		if(board[pos1][pos2] == playerMark) {
			isTrue = true;
		}
		return isTrue;
	}
	
	public boolean checkFull() { // checks if the board is full
		boolean isTrue = true;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == " ") {
					isTrue = false;
				}
			}
		}
		return isTrue;
	}
	
	public void choosePlayerMark() { // lets the user enter what mark they want to be: "X" or "O"
		
		System.out.println("Do you want to be 'X' or 'O' : ");
		while((!playerMark.equals("X")) && (!playerMark.equals("O")) && (!playerMark.equals("x")) && (!playerMark.equals("o"))) {
			String markInput = scanner.nextLine();
			
			if((markInput.equals("X")) || (markInput.equals("x"))) {
				playerMark = "X";
				opponentMark = "O";
			}
			
			else if((markInput.equals("O")) || (markInput.equals("o"))) {
				playerMark = "O";
				opponentMark = "X";
			}
			
			else {
				System.out.println("Wrong input. Please enter 'X' or 'O', or 'x' or 'o'.");
			}
		}
		markChosen = true;
	}
	
	public void checkWin() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == playerMark || board[i][j] == opponentMark) {
					checkHorizontal();
					checkVertical();
					checkDiagonal();
					break;
				}
			}
		}
		if(checkFull()) { // if the board is full and but there is no win, it is a draw
			displayDraw();
		}
	}
	
	public void checkHorizontal() { // checks for a horizontal win
		String whoWon = "";
		for(int i = 0; i < board.length; i++) {
			if(checkIndexes(board[i][0], board[i][1], board[i][2])) {
				if((board[i][0] == playerMark) && (board[i][1] == playerMark) && (board[i][2] == playerMark)) {
					whoWon = "player"; // the player has won, signal it
				}
				else {
					whoWon = "opponent";
				}
			}
		}
		if(whoWon == "player") { // display that the player has won
			displayPlayerWin();
		}
		else if(whoWon == "opponent") {
			displayOpponentWin();
		}
	}
	
	public void checkVertical() { // checks for a vertical win
		String whoWon = "";
		for(int j = 0; j < board[0].length; j++) {
			if(checkIndexes(board[0][j], board[1][j], board[2][j])) {
				if((board[0][j] == playerMark) && (board[1][j] == playerMark) && (board[2][j] == playerMark)) {
					whoWon = "player";
				}
				else {
					whoWon = "opponent";
				}
			}
		}
		if(whoWon == "player") {
			displayPlayerWin();
		}
		else if(whoWon == "opponent") {
			displayOpponentWin();
		}
	}
	
	public void checkDiagonal() { // checks for a diagonal win
		if(checkIndexes(board[0][0], board[1][1], board[2][2]) || checkIndexes(board[0][2], board[1][1], board[2][0])) {
			if((board[0][0] == playerMark) && (board[1][1] == playerMark) && (board[2][2] == playerMark)) {
				displayPlayerWin();
			}
			else if((board[0][2] == playerMark) && (board[1][1] == playerMark) && (board[2][0] == playerMark)) {
				displayPlayerWin();
			}
			else {
				displayOpponentWin();
			}
		}
	}
	
	public boolean checkIndexes(String a, String b, String c) { // checks for a win based on the passed indexes
		return ((a != " ") && (a == b) && (b == c));
	}
	
	public void displayDraw() {
		System.out.println("The board is full, so no-one won. The game is a draw.");
		gameRunning = false;
	}
	
	public void displayPlayerWin() {
		System.out.println("Congratulations! You have won!");
		gameRunning = false;
	}
	
	public void displayOpponentWin() {
		System.out.println("Sorry, you're opponent has won.");
		gameRunning = false;
	}
	
	public void inputAddMark() { // lets the player add a mark to the board
		System.out.println("Enter the row number you want to place your mark on: ");
		int rowInput = scanner.nextInt();
		System.out.println("Enter the column number you want to place your mark on: ");
		int colInput = scanner.nextInt();
		
		addMark(rowInput, colInput);
	}
	
	public String[][] getBoard() {
		return board;
	}
	
	public String getOppMark() {
		return opponentMark;
	}
	
	public void runGame() {
		while(gameRunning == true) {
			if(markChosen != true) {
				choosePlayerMark();
			}
			inputAddMark();
			displayBoard();
			checkWin();
			tictactoeai.doAIWinMove();
			displayBoard();
			checkWin();
		}
	}

	public static void main(String[] args) {
		TicTacToe tictactoe = new TicTacToe();
		tictactoe.runGame();
	}
}

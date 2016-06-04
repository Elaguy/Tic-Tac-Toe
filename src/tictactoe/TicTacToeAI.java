package tictactoe;

import java.util.Random;

/*
 * Handles the AI for the Tic Tac Toe game.
 * 
 */

public class TicTacToeAI {
	/*
	 * The AI needs to think about several things during the game:
	 * 1. If the AI has a winning move available, it needs to take that move.
	 * 2. If the human player has a winning move, block it.
	 */
	
    private TicTacToe game;
	
	Random random = new Random();
	
	private boolean winMoveHappened;
	
    public TicTacToeAI(TicTacToe game){
        this.game = game;
        initAI();
    }
    
    public void initAI() {
    	winMoveHappened = false;
    }

	public void doAIWinMove() {
		doAIHorizontal();
		doAIVertical();
		doAIDiagonal();
		
		if(winMoveHappened == false) {
			doRandomMark();
		}
	}
	
	public void doAIHorizontal() {
		for(int i = 0; i < game.getBoard().length; i++) {
			if(getWinIndex(game.getBoard()[i][0], game.getBoard()[i][1], game.getBoard()[i][2]) == "Type1") {
				game.getBoard()[i][0] = game.getOppMark();
				winMoveHappened = true;
			}
			else if(getWinIndex(game.getBoard()[i][0], game.getBoard()[i][1], game.getBoard()[i][2]) == "Type2") {
				game.getBoard()[i][1] = game.getOppMark();
				winMoveHappened = true;
			}
			else if(getWinIndex(game.getBoard()[i][0], game.getBoard()[i][1], game.getBoard()[i][2]) == "Type3") {
				game.getBoard()[i][2] = game.getOppMark();
				winMoveHappened = true;
			}
		}	
	}
	
	public void doAIVertical() {
		for(int j = 0; j < game.getBoard()[0].length; j++) {
			if(getWinIndex(game.getBoard()[0][j], game.getBoard()[1][j], game.getBoard()[2][j]) == "Type1") {
				game.getBoard()[0][j] = game.getOppMark();
				winMoveHappened = true;
			}
			else if(getWinIndex(game.getBoard()[0][j], game.getBoard()[1][j], game.getBoard()[2][j]) == "Type2") {
				game.getBoard()[1][j] = game.getOppMark();
				winMoveHappened = true;
			}
			else if(getWinIndex(game.getBoard()[0][j], game.getBoard()[1][j], game.getBoard()[2][j]) == "Type3") {
				game.getBoard()[2][j] = game.getOppMark();
				winMoveHappened = true;
			}
		}
	}
	
	public void doAIDiagonal() {
		if(getWinIndex(game.getBoard()[0][0], game.getBoard()[1][1], game.getBoard()[2][2]) == "Type1") {
			game.getBoard()[0][0] = game.getOppMark();
			winMoveHappened = true;
		}
		else if(getWinIndex(game.getBoard()[0][2], game.getBoard()[1][1], game.getBoard()[2][0]) == "Type1") {
			game.getBoard()[2][0] = game.getOppMark();
			winMoveHappened = true;
		}
		else if(getWinIndex(game.getBoard()[0][0], game.getBoard()[1][1], game.getBoard()[2][2]) == "Type2") {
			game.getBoard()[1][1] = game.getOppMark();
			winMoveHappened = true;
		}
		else if(getWinIndex(game.getBoard()[0][2], game.getBoard()[1][1], game.getBoard()[2][0]) == "Type2") {
			game.getBoard()[1][1] = game.getOppMark();
			winMoveHappened = true;
		}
		else if(getWinIndex(game.getBoard()[0][0], game.getBoard()[1][1], game.getBoard()[2][2]) == "Type3") {
			game.getBoard()[2][2] = game.getOppMark();
			winMoveHappened = true;
		}
		else if(getWinIndex(game.getBoard()[0][2], game.getBoard()[1][1], game.getBoard()[2][0]) == "Type3") {
			game.getBoard()[2][0] = game.getOppMark();
			winMoveHappened = true;
		}	
	}
	
	public String getWinIndex(String a, String b, String c) {
		String winIndex = " ";
		if((a == " ") && (b == game.getOppMark()) && (c == game.getOppMark())) {
			winIndex = "Type1";
		}
		else if((a == game.getOppMark()) && (b == " ") && (c == game.getOppMark())) {
			winIndex = "Type2";
		}
		else if((a == game.getOppMark()) && (b == game.getOppMark()) && (c == " ")) {
			winIndex = "Type3";
		}
		return winIndex;
	}
	
	public void doRandomMark() {
		int randIndex1 = random.nextInt(3);
		int randIndex2 = random.nextInt(3);
		
		while(game.getBoard()[randIndex1][randIndex2] != " ") {
			randIndex1 = random.nextInt(3);
			randIndex2 = random.nextInt(3);
		}
		game.getBoard()[randIndex1][randIndex2] = game.getOppMark();
	}
	
}

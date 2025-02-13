import java.util.Random;
import java.util.Scanner;

class TicTacToe
{
	static char[][] board;
	
	//CONSTRUCTOR.
	public TicTacToe() 
	{
		board = new char[3][3];
		initBoard();
	}
	
	//Initialising the board
	void initBoard()
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++) 
			{
				board[i][j]=' ';
			}
		}
	}
	
	//Displaying the board
	static void dispBoard() 
	{
		System.out.println("-------------");
		for(int i=0;i<board.length;i++)
		{
			System.out.print("| ");
			for(int j=0;j<board[i].length;j++) 
			{
				System.out.print(board[i][j]+ " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	
	//Placing character inside the board.
	static void placeMark(int row,int col, char mark)
	{
		if(row>=0 && row<=2 && col>=0 && col<=2)
		{
			board[row][col]=mark;
		}
		else 
		{
			System.out.println("Invalid Position!!!!");	
		}
	}
	
	//Checking For Column Win!!!!
	static boolean checkColWin()
	{
		for(int j=0;j<=2;j++) 
		{
			if(board[0][j] !=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j])
			{
				return true;
			}
		}
		return false;
	}
	
	//Checking For Row Win!!!
	static boolean checkRowWin()
	{
		for(int i=0;i<=2;i++) 
		{
			if(board[i][0] !=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2])
			{
				return true;
			}
		}
		return false;
	}
	
	static boolean checkDiagWin()
	{
		for(int i=0;i<=2;i++) 
		{
			if(board[0][0] !=' ' && board[0][0]==board[1][1] && board[1][1]==board[1][1]
					|| board[0][2] !=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0])
			{
				return true;
			}
		}
		return false;
	}
	
	static boolean checkDraw()
	{
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(board[i][j]== ' ')
				{
					return false;
				}
			}
		}
		return true;
	}
}

/****************************************************************/
abstract class Player
{
	String name;
	char mark;
	
	abstract void makeMove();
	
	boolean isValidMove(int row, int col)
	{
		if(row>=0&&row<=2 && col>=0&&col<=2)
		{
			if(TicTacToe.board[row][col] == ' ')
			{
				return true;
			}
		}
		return false;
	}
}

/****************************************************************/

class HumanPlayer extends Player
{
	
	
	HumanPlayer(String name, char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	
	void makeMove()
	{
		Scanner scan = new Scanner(System.in);
		int row,col;
		do {
		System.out.println("Enter the row and col");
		row = scan.nextInt();
		col = scan.nextInt();
		}while(!isValidMove(row,col));
		
		TicTacToe.placeMark(row, col, mark);
	}
	
	
}

/****************************************************************/

class AIPlayer extends Player
{
	
	
	AIPlayer(String name, char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	
	void makeMove()
	{
		Scanner scan = new Scanner(System.in);
		int row,col;
		do 
		{
			Random r = new Random();
			row=r.nextInt(3);//Gives the limitation to randomly generate the number.
			col=r.nextInt(3);
		}while(!isValidMove(row,col));
		
		TicTacToe.placeMark(row, col, mark);
	}
}

/****************************************************************/

public class LaunchGame1 {

	public static void main(String[] args) {
		TicTacToe t = new TicTacToe();
		t.dispBoard();
//		t.placeMark(0, 0, 'o');
//		t.placeMark(1, 0, 'o');
//		t.placeMark(2, 0, 'o');
		
//		t.placeMark(0, 0, 'o');
//		t.placeMark(0, 1, 'o');
//		t.placeMark(0, 2, 'o');
//		
//		t.placeMark(0, 0, 'o');
//		t.placeMark(1, 1, 'o');
//		t.placeMark(2, 2, 'o');
//		
//		t.placeMark(0, 2, 'o');
//		t.placeMark(1, 1, 'o');
//		t.placeMark(2, 0, 'o');
		
//		t.dispBoard();
//		System.out.println(t.checkColWin());
//		System.out.println(t.checkRowWin());
//		System.out.println(t.checkDiagWin());
		
		HumanPlayer p1 = new HumanPlayer("Bob", 'x');
		AIPlayer p2 = new AIPlayer("AI", 'o');
		
		Player cp;
		cp=p1;
		
		while(true)
		{
			System.out.println(cp.name + " turn");
			cp.makeMove();
			
			TicTacToe.dispBoard();
			
			if(TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagWin())
			{
				System.out.println(cp.name + " has won");
				break;
			}
			else if(TicTacToe.checkDraw())
			{
				System.out.println("Game is a draw");
				break;
			}
			else
			{
				if(cp==p1)
				{
					cp=p2;
				}
				else
				{
					cp=p1;
				}
			}
		}
		
	}

}

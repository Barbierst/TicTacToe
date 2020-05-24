import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList();
	static ArrayList<Integer> cpuPositions = new ArrayList();
	static Boolean gameActive = true;

	public static void main(String[] args) {
		
		char[][] gameBoard = {
				{' ', '|', ' ', '|', ' '}, 
				{'-', '+', '-', '+', '-'}, 
				{' ', '|', ' ', '|', ' '}, 
				{'-', '+', '-', '+', '-'}, 
				{' ', '|', ' ', '|', ' '}
				};
		
		printGameBoard(gameBoard);
		
		Scanner scan = new Scanner(System.in);
		
		while (gameActive) {
			System.out.println("Select a space by typing a number between 1-9:");
			int playerPos = scan.nextInt();
			
			while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
				System.out.println("This position is taken, please pick another: ");
				playerPos = scan.nextInt();
			}
			
			placePiece(gameBoard, playerPos, "player");
			
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				cpuPos = rand.nextInt(9) + 1;
			}
			
			placePiece(gameBoard, cpuPos, "cpu");
			
			printGameBoard(gameBoard);	
			
			String winnerString = checkWinner();
			System.out.println(winnerString);
		}

	}
	
	public static void printGameBoard(char[][] gameBoard) {
		
		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(char[][] gameBoard, int position, String user) {
		char symbol = ' ';
		
		if (user.equals("player")) {
			symbol = 'X';
			playerPositions.add(position);
		} else if (user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(position);
		}
		
		switch(position) {
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
			default:
				break;
		}
	}
	
	public static String checkWinner() {
		List topRow = Arrays.asList(1, 2, 3);
		List middleRow = Arrays.asList(4, 5, 6);
		List bottomRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List middleCol = Arrays.asList(2, 5, 8);
		List bottomCol = Arrays.asList(3, 6, 9);
		List diagonalTopLeft = Arrays.asList(1, 5, 9);
		List diagonalTopRight = Arrays.asList(3, 5, 7);
		
		List<List> winningConditions = new ArrayList<List>();
		winningConditions.add(topRow);
		winningConditions.add(middleRow);
		winningConditions.add(bottomRow);
		winningConditions.add(leftCol);
		winningConditions.add(middleCol);
		winningConditions.add(bottomCol);
		winningConditions.add(diagonalTopLeft);
		winningConditions.add(diagonalTopRight);
		
		for (List l : winningConditions) {
			if (playerPositions.containsAll(l)) {
				gameActive = false;
				return "You won!";
			} else if (cpuPositions.containsAll(l)) {
				gameActive = false;
				return "You lost!";
			} else if (playerPositions.size() + cpuPositions.size() == 9) {
				gameActive = false;
				return "Tie!";
			}
		}
		
		return "";
	}

}

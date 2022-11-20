package mineSweeper;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner myObjScanner = new Scanner(System.in);
		System.out.println("Welcome to Minesweeper!");
		int length;
		int height;
		int mines;
		System.out.print("Please select your length: ");
		length = myObjScanner.nextInt();
		System.out.print("Please select your height: ");
		height = myObjScanner.nextInt();
		System.out.print("How many mines: ");
		mines = myObjScanner.nextInt();
		if (mines > length*height) {
			mines = length*height;
		}
		Mines newMines = new Mines(length, height, mines);
		Minesweeper game = new Minesweeper(length, height, newMines);
		System.out.printf("%s x %s grid set up", length, height);
		int numberX;
		int numberY;
		int move = 0;
		boolean gameStatus = true;
		boolean internalStatus;
		while(gameStatus) {
			internalStatus = true;
			while (internalStatus) {
				try {
					System.out.println();
					System.out.printf("Move: %s\n\n", move);
					game.displayGrid();
					System.out.println();
					System.out.print("Input y coordinate: ");
					numberX = myObjScanner.nextInt();
					System.out.print("Input x coordinate: ");
					numberY = myObjScanner.nextInt();
					gameStatus = game.setPositionGrid(numberY, numberX);
					internalStatus = false;
					move++;
				} catch (Exception e) {
					System.out.println("Incorrect Input. Input a letter to reload move.");
					myObjScanner.next();
				}
			}
			
		}
	}

}

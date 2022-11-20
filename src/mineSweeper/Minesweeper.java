package mineSweeper;

import java.util.ArrayList;

public class Minesweeper extends Grid{
	protected Grid grid;
	protected Grid displayGrid;
	protected ArrayList<Integer> displayRow = new ArrayList<>();
	
	
	public Minesweeper(int length, int height, Mines mines) {
		super(length, height, mines);
		createDisplayRow();
		grid = new Grid(length, height, mines);
	}
	
	public ArrayList<Row> getGrid() {
		ArrayList<Row> rows = grid.returnGrid();
		return rows;
	}
	
	public void showMines() {
		grid.showMines();
	}
	
	public void displayMines() {
		grid.displayMinesToGrid();
	}
	
	public boolean setPositionGrid(int row, int col) {
	
		return grid.setPosition(row, col);
		
	}
	
	public void displayGrid() {
		
		System.out.println("  " + displayRow);
		grid.showGrid();
	}
	
	public void createDisplayRow() {
		for (int i = 0; i < length; i++) {
			displayRow.add(i);
		}
	}
	
	
}

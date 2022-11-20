package mineSweeper;

import java.util.ArrayList;


public class Grid extends Row{
	
	protected int height;
	protected ArrayList<Row> gridList = new ArrayList<Row>();
	public Row row;
	protected Mines mines;
	
	public Grid(int length, int height, Mines mines) {
		super(length);
		
		this.height = height;
		this.mines = mines;
		createList();
	}
	
	
	
	public void createList() {
		
		for (int i = 0; i < height; i++) {
			Row thisRow = new Row(length);
			thisRow.createRow(i,mines);
			gridList.add(thisRow);
		}
		
	}
	
	public ArrayList<Row> returnGrid() {
		return gridList;
	}
	
	public void showGrid() {
		int index = 0;
		for (Row item: gridList) {
			System.out.println(index + " " + item.getDisplayRow());
			index++;
		}
	}
	
	public void showMines() {
		System.out.println(mines.getMines());
	}
	
	public boolean setPosition(int row, int col) {
		if (gridList.get(col).getRowIndex(row) < 0) {
			System.out.println("BOOM! You lose...");
			displayMinesToGrid();
			showGrid();
			return false;
		}
		for (int colP = col-1; colP < (col+2); colP++) {
			for (int rowP = row-1; rowP < (row+2); rowP++) {
				if ((rowP >= 0) && (colP >= 0) && (rowP < length) && (colP < height) && (gridList.get(colP).getRowIndex(rowP) >= 0) && (gridList.get(colP).getDisplayIndexString(rowP) == " ")) {
					int lowest = mines.findClosest(rowP, colP);
					gridList.get(colP).setRowIndex(rowP, 0);
					gridList.get(colP).setDisplayRowIndex(rowP, lowest);
				} 
			}
		}
		System.out.println("Placement successfull at x"+row+" y"+col);
		return gameContinues();
	}
	
	public void displayMinesToGrid () {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < length; j++) {
				if (gridList.get(i).getRowIndex(j) == -1) {
					gridList.get(i).setDisplayRowIndex(j, "*");
				}
			}
		}
	}
	
	public boolean gameContinues() {
		for (Row rows: gridList) {
			for (int i = 0; i < length; i++) {
				if (rows.getRowIndex(i) > 0) {
					return true;
				}
			}
		}
		System.out.println("You win!");
		displayMinesToGrid();
		showGrid();
		return false;
	}
}

	
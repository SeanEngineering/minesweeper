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
	
	public void revealTile(int row, int col) {
		
		if (gridList.get(col).getDisplayIndexString(row) == "■") {
			ArrayList<Integer[]> adjacentMines = new ArrayList<>();
			ArrayList<Integer[]> tiles = nearbyTiles(col, row);
			for (Integer[] mine: mines.getMines()) {
				for (Integer[] tile: tiles) {
					if (tile[0] == mine[0] && tile[1] == mine[1]) {
						adjacentMines.add(tile);
					}
				}
			}
			if (adjacentMines.size() == 0) {
				gridList.get(col).setDisplayRowIndex(row, "□");
				for (Integer[] tile: tiles) {
					revealTile(tile[0], tile[1]);
				}
				
			} else {
				gridList.get(col).setDisplayRowIndex(row, adjacentMines.size());
			};
			
		}

	}
	
	public ArrayList<Integer[]> nearbyTiles(int row, int col) {
		ArrayList<Integer[]> tiles = new ArrayList<>();
		for (int xOffset = -1; xOffset < 2; xOffset++) {
			for (int yOffset = -1; yOffset < 2; yOffset++) {
				if ((xOffset+row) > -1 && (xOffset+row) < length && (yOffset+col) > -1 && (yOffset+col) < height) {
					Integer[] tile = {(col + yOffset),(row + xOffset)};
					tiles.add(tile);
				}
			}
		}
		return tiles;
		
	}
	
	public boolean setPosition(int row, int col) {
		if (gridList.get(col).getRowIndex(row) < 0) {
			System.out.println("BOOM! You lose...");
			displayMinesToGrid();
			showGrid();
			return false;
		} else if (gridList.get(col).getDisplayIndexString(row) !=  "■") {
			System.out.println("invalid move");
			return true;
		}
		revealTile(row, col);
		
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

	
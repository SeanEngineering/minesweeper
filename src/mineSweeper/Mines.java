package mineSweeper;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Mines {
	public ArrayList<Integer> mines;
	int length;
	int height;
	int numMines;
	
	public Mines (int length, int height, int numMines) {
		this.length = length;
		this.height = height;
		this.numMines = numMines;
		generateMines();
		
	}
	public void generateMines() {
		ArrayList<Integer> numberCounter = new ArrayList<Integer>();
		boolean inArray = false;
		boolean check = true;
		
		for (int i = 0; i < numMines; i++) {
			check = true;
			while(check) {
				inArray = false;
				int randomNum = (int) (Math.random() * length * height);
				for (int numbers: numberCounter) {
					if (randomNum == numbers) {
						inArray = true;
						break;
					}
				}
				if (inArray == false) {
					numberCounter.add(randomNum);
					check=false;
				}
			}
		}
		
		mines = (ArrayList<Integer>) numberCounter.stream().sorted().collect(Collectors.toList());
	}
	
	public int findClosest(int row, int col) {
		//To get a mine column we take the row mod and divide by the length
		int lowest = length-1;
		for (int mine: mines) {
			int rowM = mine%length;
			int colM = (mine-rowM)/height;
			int abs = Math.abs(row-rowM) + Math.abs(col-colM);
			if (abs < lowest) {
				lowest = abs;
			}
		}
		return lowest;
		
		
	}
	
	public ArrayList<Integer> getMines(){
		return mines;
	}
	
}

package mineSweeper;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Mines {
	public ArrayList<Integer[]> mines;
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
		ArrayList<Integer[]> numberCounter = new ArrayList<Integer[]>();
		boolean inArray = false;
		boolean check = true;
		
		for (int i = 0; i < numMines; i++) {
			check = true;
			while(check) {
				inArray = false;
				int randomNumX = (int) (Math.random() * length);
				int randomNumY = (int) (Math.random() * height);
				for (Integer[] numbers: numberCounter) {
					if (randomNumX == numbers[0] && randomNumY == numbers[1]) {
						inArray = true;
						break;
					}
				}
				if (inArray == false) {
					Integer[] thisIndex = {randomNumX, randomNumY};
					numberCounter.add(thisIndex);
					check=false;
				}
			}
		}
		
		mines = (ArrayList<Integer[]>) numberCounter.stream().collect(Collectors.toList());
	}
	
	
	public ArrayList<Integer[]> getMines(){
		return mines;
	}
	
}

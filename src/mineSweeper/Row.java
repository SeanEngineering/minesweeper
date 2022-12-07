package mineSweeper;

import java.util.ArrayList;
import java.util.Collections;

public class Row {
	protected int length;
	protected ArrayList<Integer> rowArrayList= new ArrayList<Integer>();
	protected ArrayList<String> displayArrayList;
	public Row(int length) {
		this.length = length;
		displayArrayList = new ArrayList<>(Collections.nCopies(length, "■"));
	}
	
	public void createRow(int column, Mines mines) {
		for (int i = 0; i < length; i++){
			if (checkMinesOnRow(mines.getMines(), i, column)) {
				this.rowArrayList.add(-1);
			} else {
				this.rowArrayList.add(i);
			}
		}
	}
	
	public ArrayList<Integer> getRow(){
		return rowArrayList;
	}
	public void showRow() {
		System.out.println(rowArrayList);
	}
	public void setRowIndex (int index, int value) {
		this.rowArrayList.set(index, value);
	}
	
	public int getRowIndex (int index) {
		return this.rowArrayList.get(index);
		
	}
	
	
	public <T> void setDisplayRowIndex (int index, T value) {
		this.displayArrayList.set(index, value.toString() );
	}
	public void showDisplayRow() {
		System.out.println(displayArrayList);
	}
	public ArrayList<String> getDisplayRow(){
		return displayArrayList;
	}
	
	public String getDisplayIndexString (int index) {
		return this.displayArrayList.get(index);
	}
	
	public boolean checkMinesOnRow(ArrayList<Integer[]> mines, int xPos, int yPos) {
		for (Integer[] mine: mines) {
			if (mine[0] == xPos && mine[1] == yPos) {
				return true;
			}
		}
		return false;
	}

}

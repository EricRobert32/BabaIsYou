package model;

import java.util.ArrayList;

public class Cell {
	private final ArrayList<Block> element;
	
	public Cell() {
		this.element = new ArrayList<>();
	}
	
	public void addBlock(Block block) {
		element.add(block);
	}
	
	public void removeBlock(Block block) {
		element.remove(block);
	}
	
	public boolean containBlock(Block block) {
		return element.contains(block);
	}
	
	public Block getBlock(int index) {
		return element.get(index);
	}
}

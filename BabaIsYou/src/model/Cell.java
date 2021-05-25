package model;

import java.util.ArrayList;

public class Cell {
	private final ArrayList<Block> element;
	
	public Cell() {
		this.element = new ArrayList<>();
	}
	
	public void addBlock(Block block) {
		/* TODO FAIRE EN SORTE D'AVOIR PLUSIEURS ELEMENTS AU MEME ENDROIT */
		if (element.size() != 0) {
			element.remove(0);
		}
		element.add(block);
	}
	
	public void removeBlock(Block block) {
		element.remove(block);
		/* REMPLACE PAR NULL */
		if (element.isEmpty()) {
			this.addBlock(null);
		}
	}
	
	public boolean containBlock(Block block) {
		return element.contains(block);
	}
	
	public Block getBlock(int index) {
		return element.get(index);
	}
}

package modele;

import java.util.ArrayList;

import model.elementList.EnumWord;

public class Cell {
	private final ArrayList<Block> element;
	
	public Cell() {
		this.element = new ArrayList<>();
	}
	
	public void addBlock(Block bloc) {
		element.add(bloc);
	}
	
	public void removeBlock(Block bloc) {
		element.remove(bloc);
	}
	
	public boolean containBlock(Block bloc) {
		return element.contains(bloc);
	}
	

}

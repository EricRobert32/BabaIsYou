package model;

import model.elementList.EnumWord;

/**
 * Class that represent a element block
 * @author Éric Robert
 * @author Romain Barbé
 * @version 1
 */
public final class ElementBlock extends AbstractBlock {
	/**
	 * ElementBlock constructor
	 * @param name
	 * @param x
	 * @param y
	 */
	public ElementBlock(int size_grid, EnumWord name, int x, int y) {
		super(size_grid, name, x, y);
	}
	
	/*public void destroy() {
		
	}*/
}

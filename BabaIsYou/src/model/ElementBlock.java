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
	public ElementBlock(int sizeGridX, int sizeGridY, EnumWord name, int x, int y) {
		super(sizeGridX, sizeGridY, name, x, y);
	}
	
	/*public void destroy() {
		
	}*/
	
	/**
	 * Return a string representation of the element block
	 * @return a String with the name, x and y of the element block
	 */
	@Override
	public String toString() {
		return super.getName() + " (" + super.getX() + ", " + super.getY() + ") ";
	}
}

package model;

import model.elementList.EnumCategory;
import model.elementList.EnumWord;

/**
 * Class that represent a word block
 * @author Éric Robert
 * @author Romain Barbé
 * @version 1
 */
public final class WordBlock extends AbstractBlock {
	private EnumCategory category;

	/**
	 * WordBlock constructor
	 * @param name
	 * @param x
	 * @param y
	 * @param category
	 */
	public WordBlock(int sizeGridX, int sizeGridY, EnumWord name, int x, int y, EnumCategory category) {
		super(sizeGridX, sizeGridY, name, x, y);
		this.category = category;
	}
	
	/**
	 * Send back the category of the word block
	 * @return the category of the word block
	 */
	public EnumCategory getCategory() {
		return this.category;
	}
	
	/**
	 * Return a string representation of the word block
	 * @return a String with the name, x and y of the word block
	 */
	@Override
	public String toString() {
		return super.getName() + " (" + super.getX() + ", " + super.getY() + ") ";
	}
}

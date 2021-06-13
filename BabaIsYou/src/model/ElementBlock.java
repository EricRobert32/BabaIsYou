package model;

import model.elementList.EnumWord;

/**
 * Class that represent a element block
 * @author ROBERT Eric
 * @author BARBÉ Romain
 * @version 1
 */
public final class ElementBlock extends AbstractBlock {
	/**
	 * ElementBlock constructor
	 * @param name
	 */
	public ElementBlock(EnumWord name) {
		super(name);
	}
	
	/**
	 * Return a string representation of the element block
	 * @return a String with the name of the element block
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}

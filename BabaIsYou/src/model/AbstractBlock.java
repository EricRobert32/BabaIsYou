package model;

import model.elementList.EnumWord;
import view.Sprite;

/**
 * Class that represent a element block
 * @author Éric Robert
 * @author Romain Barbé
 * @version 1
 */
abstract sealed class AbstractBlock implements Block permits WordBlock, ElementBlock{
	private final EnumWord name;
	private Sprite image;
	
	/**
	 * Block constructor
	 * @param name
	 */
	public AbstractBlock(EnumWord name) {
		this.name = name;
		
		if (this.getClass() == ElementBlock.class) {
			this.image = new Sprite(this.name, "E-");
		}
		else {
			this.image = new Sprite(this.name, "W-");
		}
	}
	
	/**
	 * Send back the name of the block
	 * @return the name
	 */
	public EnumWord getName() {
		return this.name;
	}
	
	public Sprite getImage() {
		return this.image;
	}
	
	@Override
	public String toString() {
		return this.name.toString();
	}
	
	/*@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AbstractBlock that = (AbstractBlock) o;
		return x == that.x && y == that.y && name == that.name;
	}


	@Override
	public int hashCode() {
		return Objects.hash(name, x, y);
	}*/
}

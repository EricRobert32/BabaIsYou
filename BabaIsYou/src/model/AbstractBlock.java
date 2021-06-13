package model;

import java.util.Objects;

import model.elementList.EnumWord;
import view.Sprite;

/**
 * Class that represent a element block
 * @author ROBERT Eric
 * @author BARBÉ Romain
 * @version 1
 */
abstract sealed class AbstractBlock implements Block permits WordBlock, ElementBlock{
	private final EnumWord name;
	private Sprite image;
	
	/**
	 * Block constructor
	 * @param name Element of the block
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
	 * Return the element of the block
	 * @return element of the block
	 */
	public EnumWord getName() {
		return this.name;
	}
	
	/**
	 * Return the image attached to the element
	 * @return Sprite of the element
	 */
	public Sprite getImage() {
		return this.image;
	}
	
	
	@Override
	public String toString() {
		return this.name.toString();
	}
	
	/**
	 * Compares the specified object with this AbstractBlock for equality
	 * @return True if they are equals, 0 otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AbstractBlock that = (AbstractBlock) o;
		return name.equals(that.name) && image.equals(that.image);
	}

	/**
	 * Returns the hash code value for this AbstackBlock
	 * @return hash of the specified object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name)+ image.hashCode();
	}
}

package model;

import java.util.Objects;

import model.elementList.EnumDirection;
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
	private int x;
	private int y;
	private Sprite image;
	
	/**
	 * Block constructor
	 * @param name
	 * @param x
	 * @param y
	 */
	public AbstractBlock(int sizeGridX, int sizeGridY, EnumWord name, int x, int y) {
		if (x < 0 || x >= sizeGridX) {
			throw new IllegalArgumentException("Not a valid X coordinate");
		}
		if (y < 0 || y >= sizeGridY) {
			throw new IllegalArgumentException("Not a valid Y coordinate");
		}
		
		this.name = name;
		this.x = x;
		this.y = y;
		
		if (this.getClass() == ElementBlock.class) {
			this.image = new Sprite(this.name, "E-");
		}
		else {
			this.image = new Sprite(this.name, "W-");
		}
	}
	
	/**
	 * Send back the name of the block
	 * @return the coordinate x
	 */
	public EnumWord getName() {
		return this.name;
	}

	/**
	 * Send back the coordinate x of the block
	 * @return the coordinate x
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Send back the coordinate y of the block
	 * @return the coordinate y
	 */
	public int getY() {
		return this.y;
	}
	
	public Sprite getImage() {
		return this.image;
	}
	
	/**
	 * Move the block in a certain direction
	 * @param dir	direction in which the piece is pushed
	 *
	 */
	public void move(EnumDirection dir) {
		switch (dir) {
			case EAST -> y++;
			case WEST -> y--;
			case NORTH -> x--;
			case SOUTH -> x++;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AbstractBlock that = (AbstractBlock) o;
		return x == that.x && y == that.y && name == that.name;
	}


	@Override
	public int hashCode() {
		return Objects.hash(name, x, y);
	}
}

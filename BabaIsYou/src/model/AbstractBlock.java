package model;

import fr.umlv.zen5.ApplicationContext;
import model.elementList.EnumDirection;
import model.elementList.EnumWord;

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
	
	/**
	 * Move the block in a certain direction
	 * @param direction		direction in which the piece is pushed
	 * @return true is the block has been pushed or then false
	 */
	public boolean move(ApplicationContext context, Model model, int sizeGridX, int sizeGridY, EnumDirection direction) {
		float width = context.getScreenInfo().getWidth();
		float height = context.getScreenInfo().getHeight();
		
		if (direction == EnumDirection.NORTH && (this.x - 1) * (height / sizeGridX) >= 0) {
			model.removeBlock(this.x, this.y, this);
			this.x = this.x - 1;
			model.addBlocks(this.x, this.y, this);
			return true;
		}
		if (direction == EnumDirection.SOUTH && (this.x + 1) * (height / sizeGridX) < height) {
			model.removeBlock(this.x, this.y, this);
			this.x = this.x + 1;
			model.addBlocks(this.x, this.y, this);
			return true;
		}
		if (direction == EnumDirection.EAST && (this.y + 1) * (width / sizeGridY) < width) {
			model.removeBlock(this.x, this.y, this);
			this.y = this.y + 1;
			model.addBlocks(this.x, this.y, this);
			return true;
		}
		if (direction == EnumDirection.WEST && (this.y - 1) * (width / sizeGridY) >= 0) {
			model.removeBlock(this.x, this.y, this);
			this.y = this.y - 1;
			model.addBlocks(this.x, this.y, this);
			return true;
		}
		return false;
	}
}

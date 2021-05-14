package modele;

import fr.umlv.zen5.ApplicationContext;

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
	public AbstractBlock(int size_grid, EnumWord name, int x, int y) {
		if (x < 0 || x >= size_grid) {
			throw new IllegalArgumentException("Not a valid X coordinate");
		}
		if (y < 0 || y >= size_grid) {
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
	public boolean move(ApplicationContext context, int size_grid, EnumDirection direction) {
		float width = context.getScreenInfo().getWidth();
		float height = context.getScreenInfo().getHeight();
		
		if (direction == EnumDirection.NORTH && (this.y - 1) * (height / size_grid) >= 0) {
			this.y = this.y - 1;
			return true;
		}
		else if (direction == EnumDirection.SOUTH && (this.y + 1) * (height / size_grid) < height) {
			this.y = this.y + 1;
			return true;
		}
		else if (direction == EnumDirection.EAST && (this.x + 1) * (width / size_grid) < width) {
			this.x = this.x + 1;
			return true;
		}
		else if (direction == EnumDirection.WEST && (this.x - 1) * (width / size_grid) >= 0) {
			this.x = this.x - 1;
			return true;
		}
		return false;
	}
}

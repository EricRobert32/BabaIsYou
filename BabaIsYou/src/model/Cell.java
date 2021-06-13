package model;

import java.util.ArrayList;

/**
 * A cell of the game grid containing multiple element
 * @author ROBERT Eric
 * @author BARBÉ Romain
 *
 */
public class Cell {
	private final ArrayList<Block> element;
	
	/**
	 * Constructor for Cell
	 */
	public Cell() {
		this.element = new ArrayList<>();
	}
	
	/**
	 * Add a block to the cell
	 * @param block block to add
	 */
	public void addBlock(Block block) {
		element.add(block);
	}
	
	/**
	 * Remove a block from the cell
	 * @param i Index of the block to remove
	 */	
	public boolean removeBlockAt(int i) {
		if (i < this.element.size()) {
			element.remove(i);
			return true;
		}
		return false;
	}
	
	/**
	 * Look if a block is inside this cell
	 * @param block block to look at
	 * @return true if it's in the cell, false otherwise
	 */
	public boolean containBlock(Block block) {
		return element.contains(block);
	}
	
	/**
	 * Return the amount of element in this cell
	 * @return the amount of element
	 */
    public int size(){
        return element.size();
    }
	
    /**
     * Returns a block at the specified position 
     * @param index position
     * @return the element at the specified position in this cell
     */
	public Block getBlock(int index) {
		return element.get(index);
	}
	
	
	/**
	 * Returns a string representation of this cell
	 * @return a string representing the cell
	 */
    @Override
    public String toString() {
        if (element.isEmpty()) {
            return "[]";
        } else {
            return element.toString();
        }
    }
    
    /**
     * Look if the cell is empty
     * @return True if the cell is empty, False otherwise
     */
    public boolean isEmpty(){
        return element.isEmpty();
    }
}

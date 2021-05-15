package model;

import java.util.HashMap;

import model.elementList.EnumCategory;
import model.elementList.EnumDirection;
import model.elementList.EnumWord;

public class Model {
	private Cell[][] grid;
	private HashMap<EnumWord, EnumWord> rule;

	public Model(int line, int column) {
		this.grid = new Cell[line][column];
		this.rule = new HashMap<>();
	}

	public int getNbLine() {
		return grid.length;
	}

	public int getNbColumn() {
		return grid[0].length;
	}
	
	public EnumWord getRule(EnumWord element) {
		return rule.get(element);
	}
	
	private void addRule(EnumWord word1, EnumWord word2) {
		rule.put(word1, word2);
	}
	private void removeRule(EnumWord element) {
		rule.remove(element);
	}

	public void addBlocks(int line, int column, Block... bloc) {
		for (int i = 0; i < bloc.length; i++) {
			grid[line][column].addBlock(bloc[i]);
		}
	}
	
	public void removeBlock(int line, int column,Block bloc) {
		grid[line][column].removeBlock(bloc);
	}
	
	public void moveBlock(int line,int column,EnumDirection dir) {
		
	}
	
	public void displayGrid() {
		
	}

	public boolean verifySentence(WordBlock w1, WordBlock w2, WordBlock w3) {
		if (w1.getCategory() == EnumCategory.NOUN && w2.getCategory() == EnumCategory.OPERATOR
				&& (w3.getCategory() == EnumCategory.NOUN || w3.getCategory() == EnumCategory.ATTRIBUTE)) {
			System.out.println("The 3 words are in the order '" + w1.getCategory() + " - " + w2.getCategory() + " - "
					+ w3.getCategory() + "'");
			if (w1.getX() == w2.getX() && w2.getX() == w3.getX()) {
				System.out.println("The 3 words are on the same column");
				if (w1.getY() == (w2.getY() - 1) && (w2.getY() + 1) == w3.getY()) {
					System.out.println("The 3 words are one after the other");
					return true;
				}
				System.out.println("The 3 words are NOT one after the other");
				return false;
			} else if (w1.getY() == w2.getY() && w2.getY() == w3.getY()) {
				System.out.println("The 3 words are on the same line");
				if (w1.getX() == (w2.getX() - 1) && (w2.getX() + 1) == w3.getX()) {
					System.out.println("The 3 words are one after the other");
					return true;
				}
				System.out.println("The 3 words are NOT one after the other");
				return false;
			}
			System.out.println("The 3 words are NOT on the same column or on the same line");
			return false;
		}
		System.out.println("The 3 words are NOT in the order '" + w1.getCategory() + " - " + w2.getCategory() + " - "
				+ w3.getCategory() + "'");
		return false;
	}

}

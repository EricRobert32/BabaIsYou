package model;

import java.util.HashSet;

import model.elementList.EnumCategory;
import model.elementList.EnumDirection;
import model.input.InputData;

public class Model {
	private Cell[][] grid;
	private HashSet<Rule> rules;

	public Model(String file) {
		this.grid = InputData.readFile(file);
		this.rules = this.generateRules(grid);
	}

	public int getNbLine() {
		return grid.length;
	}

	public int getNbColumn() {
		return grid[0].length;
	}

	public HashSet<Rule> getRules() {
		return this.rules;
	}

	/*
	 * public EnumWord getRule(EnumWord element) { return rules.get(element); }
	 * 
	 * private void addRule(EnumWord word1, EnumWord word2) { rules.put(word1,
	 * word2); }
	 * 
	 * private void removeRule(EnumWord element) { rules.remove(element); }
	 */

	public void addBlocks(int line, int column, Block... bloc) {
		for (int i = 0; i < bloc.length; i++) {
			grid[line][column].addBlock(bloc[i]);
		}
	}

	public void removeBlock(int line, int column, Block bloc) {
		grid[line][column].removeBlock(bloc);
	}

	public void moveBlock(int line, int column, EnumDirection dir) {

	}

	public void displayGrid() {
		System.out.println("\n****** DISPLAY GRID ******\n");
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j].getBlock(0));
			}
			System.out.println("");
		}
		System.out.println("\n**** FIN DISPLAY GRID ****\n");
	}

	public Cell[][] getGrid() {
		return grid;
	}

	/* MÉTHODE PLUS UTILISÉE */
	public boolean verifySentence(WordBlock w1, WordBlock w2, WordBlock w3) {
		if (w1.getCategory() == EnumCategory.NOUN && w2.getCategory() == EnumCategory.OPERATOR
				&& (w3.getCategory() == EnumCategory.NOUN || w3.getCategory() == EnumCategory.ATTRIBUTE)) {
			/*
			 * System.out.println("The 3 words are in the order '" + w1.getCategory() +
			 * " - " + w2.getCategory() + " - " + w3.getCategory() + "'");
			 */
			if (w1.getX() == w2.getX() && w2.getX() == w3.getX()) {
				/* System.out.println("The 3 words are on the same column"); */
				if (w1.getY() == (w2.getY() - 1) && (w2.getY() + 1) == w3.getY()) {
					/* System.out.println("The 3 words are one after the other"); */
					return true;
				}
				/* System.out.println("The 3 words are NOT one after the other"); */
				return false;
			} else if (w1.getY() == w2.getY() && w2.getY() == w3.getY()) {
				/* System.out.println("The 3 words are on the same line"); */
				if (w1.getX() == (w2.getX() - 1) && (w2.getX() + 1) == w3.getX()) {
					/* System.out.println("The 3 words are one after the other"); */
					return true;
				}
				/* System.out.println("The 3 words are NOT one after the other"); */
				return false;
			}
			/*
			 * System.out.
			 * println("The 3 words are NOT on the same column or on the same line");
			 */
			return false;
		}
		/*
		 * System.out.println("The 3 words are NOT in the order '" + w1.getCategory() +
		 * " - " + w2.getCategory() + " - " + w3.getCategory() + "'");
		 */
		return false;
	}

	public HashSet<Rule> generateRules(Cell[][] grid) {
		HashSet<Rule> rules = new HashSet<>();

		System.out.println("\n***** GENERATE RULES *****\n");

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				Block block = grid[i][j].getBlock(0);
				Block blockX1 = null;
				Block blockX2 = null;
				Block blockY1 = null;
				Block blockY2 = null;

				if (j < grid[0].length - 2) {
					blockX1 = grid[i][j + 1].getBlock(0);
					blockX2 = grid[i][j + 2].getBlock(0);
				}
				if (i < grid.length - 2) {
					blockY1 = grid[i + 1][j].getBlock(0);
					blockY2 = grid[i + 2][j].getBlock(0);
				}

				if (block != null) {
					/* Vérifier phrase sur la ligne */
					if (blockX1 != null && blockX2 != null) {
						if (block.getClass() == WordBlock.class && blockX1.getClass() == WordBlock.class
								&& blockX2.getClass() == WordBlock.class) {
							if (((WordBlock) block).getCategory() == EnumCategory.NOUN && ((WordBlock) blockX1).getCategory() == EnumCategory.OPERATOR
									&& (((WordBlock) blockX2).getCategory() == EnumCategory.NOUN || ((WordBlock) blockX2).getCategory() == EnumCategory.ATTRIBUTE)) {
								System.out.println("Les mots " + block + blockX1 + blockX2 + "forment une phrase");
								rules.add(new Rule(block.getName(), blockX2.getName()));
							}
						}
					}

					/* Vérifier phrase sur la colonne */
					if (blockY1 != null && blockY2 != null) {
						if (block.getClass() == WordBlock.class && blockY1.getClass() == WordBlock.class
								&& blockY2.getClass() == WordBlock.class) {
							if (((WordBlock) block).getCategory() == EnumCategory.NOUN && ((WordBlock) blockY1).getCategory() == EnumCategory.OPERATOR
									&& (((WordBlock) blockY2).getCategory() == EnumCategory.NOUN || ((WordBlock) blockY2).getCategory() == EnumCategory.ATTRIBUTE)) {
								System.out.println("Les mots " + block + blockY1 + blockY2 + "forment une phrase");
								rules.add(new Rule(block.getName(), blockY2.getName()));
							}
						}
					}
				}
			}
		}

		System.out.println("\n*** FIN GENERATE RULES ***\n");

		return rules;
	}
}

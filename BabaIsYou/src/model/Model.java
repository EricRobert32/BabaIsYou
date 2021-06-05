package model;

import java.util.HashSet;

import model.elementList.EnumCategory;
import model.elementList.EnumDirection;
import model.elementList.EnumWord;
import model.input.InputData;

public class Model {
	private Cell[][] grid;
	private HashSet<Rule> rules;

	public Model(String file) {
		this.grid = InputData.readFile(file);
		this.rules = this.generateRules(this.grid);
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
	
	public void refreshRules() {
		this.rules = this.generateRules(this.grid);
	}

	/*
	 * public EnumWord getRule(EnumWord element) { return rules.get(element); }
	 * 
	 * private void addRule(EnumWord word1, EnumWord word2) { rules.put(word1,
	 * word2); }
	 * 
	 * private void removeRule(EnumWord element) { rules.remove(element); }
	 */

	/*
	 * public void addBlocks(int line, int column, Block... bloc) { for (int i = 0;
	 * i < bloc.length; i++) { grid[line][column].addBlock(bloc[i]); } }
	 * 
	 * public void removeBlock(int line, int column, Block bloc) {
	 * grid[line][column].removeBlock(bloc); }
	 */

	private boolean verifiedBlock(int line, int column, Block block) {
		for (Rule r : rules) {
			if (r.getRuleFirstWord() == block.getName()) {
				if (block.getClass() == WordBlock.class) {
					return true;
				}
				if (r.getRuleSecondWord() == EnumWord.STOP) {
					return false;
				} else if (r.getRuleSecondWord() == EnumWord.PUSH) {
					return true;
				}
			}
		}
		return true;
	}

	public boolean moveBlock(int line, int column, Block block, EnumDirection dir) {

		/* For each block, if a block can t interact */
		for (int i = 0; i < grid[line][column].size(); i++) {
			if (!verifiedBlock(line, column, grid[line][column].getBlock(i))) {
				return false;
			}
		}
		switch (dir) {
		case EAST:
			if (column + 1 >= grid[0].length) {
				return false;
			}
			if (grid[line][column + 1].isEmpty()) {
				System.out.println(grid[line][column].removeBlock(block));
				grid[line][column + 1].addBlock(block);
				return true;
			}
			for (int i = 0; i < grid[line][column + 1].size(); i++) {
				System.out.println(grid[line][column + 1].getBlock(i));
				if (moveBlock(line, column + 1, grid[line][column + 1].getBlock(i), dir)) {
					System.out.println(grid[line][column].removeBlock(block));
					grid[line][column + 1].addBlock(block);
					return true;

				}
			}
			return false;
		case WEST:
			if (column - 1 < 0) {
				return false;
			}
			if (grid[line][column - 1].isEmpty()) {
				System.out.println(grid[line][column].removeBlock(block));
				grid[line][column - 1].addBlock(block);
				return true;
			}
			for (int i = 0; i < grid[line][column - 1].size(); i++) {
				System.out.println(grid[line][column - 1].getBlock(i));
				if (moveBlock(line, column - 1, grid[line][column - 1].getBlock(i), dir)) {
					System.out.println(grid[line][column].removeBlock(block));
					grid[line][column - 1].addBlock(block);
					return true;

				}
			}
			return false;
		case NORTH:
			if (line - 1 < 0) {
				return false;
			}
			if (grid[line - 1][column].isEmpty()) {
				System.out.println(grid[line][column].removeBlock(block));
				grid[line - 1][column].addBlock(block);
				return true;
			}
			for (int i = 0; i < grid[line - 1][column].size(); i++) {
				System.out.println(grid[line - 1][column].getBlock(i));
				if (moveBlock(line - 1, column, grid[line - 1][column].getBlock(i), dir)) {
					System.out.println(grid[line][column].removeBlock(block));
					grid[line - 1][column].addBlock(block);
					return true;
				}
			}
			return false;
		case SOUTH:
			if (line + 1 >= grid.length) {
				return false;
			}
			if (grid[line + 1][column].isEmpty()) {
				System.out.println(grid[line][column].removeBlock(block));
				grid[line + 1][column].addBlock(block);
				return true;
			}
			for (int i = 0; i < grid[line + 1][column].size(); i++) {
				System.out.println(grid[line + 1][column].getBlock(i));
				if (moveBlock(line + 1, column, grid[line + 1][column].getBlock(i), dir)) {
					System.out.println(grid[line][column].removeBlock(block));
					grid[line + 1][column].addBlock(block);
					return true;
				}
			}
			return false;
		default:
			throw new IllegalArgumentException("Wrong direction : " + dir);
		}

	}

	public void displayGrid() {
		System.out.println("\n****** DISPLAY GRID ******\n");
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				str.append(grid[i][j].toString());
				str.append(" ");

			}
			System.out.println(str);
			str.setLength(0);
			System.out.println("");
		}
		System.out.println("\n**** FIN DISPLAY GRID ****\n");
	}

	public Cell[][] getGrid() {
		return grid;
	}

//	/* MÉTHODE PLUS UTILISÉE */
//	public boolean verifySentence(WordBlock w1, WordBlock w2, WordBlock w3) {
//		if (w1.getCategory() == EnumCategory.NOUN && w2.getCategory() == EnumCategory.OPERATOR
//				&& (w3.getCategory() == EnumCategory.NOUN || w3.getCategory() == EnumCategory.ATTRIBUTE)) {
//			/*
//			 * System.out.println("The 3 words are in the order '" + w1.getCategory() +
//			 * " - " + w2.getCategory() + " - " + w3.getCategory() + "'");
//			 */
//			if (w1.getX() == w2.getX() && w2.getX() == w3.getX()) {
//				/* System.out.println("The 3 words are on the same column"); */
//				if (w1.getY() == (w2.getY() - 1) && (w2.getY() + 1) == w3.getY()) {
//					/* System.out.println("The 3 words are one after the other"); */
//					return true;
//				}
//				/* System.out.println("The 3 words are NOT one after the other"); */
//				return false;
//			} else if (w1.getY() == w2.getY() && w2.getY() == w3.getY()) {
//				/* System.out.println("The 3 words are on the same line"); */
//				if (w1.getX() == (w2.getX() - 1) && (w2.getX() + 1) == w3.getX()) {
//					/* System.out.println("The 3 words are one after the other"); */
//					return true;
//				}
//				/* System.out.println("The 3 words are NOT one after the other"); */
//				return false;
//			}
//			/*
//			 * System.out.
//			 * println("The 3 words are NOT on the same column or on the same line");
//			 */
//			return false;
//		}
//		/*
//		 * System.out.println("The 3 words are NOT in the order '" + w1.getCategory() +
//		 * " - " + w2.getCategory() + " - " + w3.getCategory() + "'");
//		 */
//		return false;
//	}

	public HashSet<Rule> generateRules(Cell[][] grid) {
		HashSet<Rule> rules = new HashSet<>();

		System.out.println("\n***** GENERATE RULES *****\n");

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].isEmpty()) {
					continue;
				}
				Block block = grid[i][j].getBlock(0);
				Block blockX1 = null;
				Block blockX2 = null;
				Block blockY1 = null;
				Block blockY2 = null;

				if (j < grid[0].length - 2) {
                    if (!(grid[i][j + 1].isEmpty()) && !(grid[i][j + 2].isEmpty())) {
                        blockX1 = grid[i][j + 1].getBlock(0);
                        blockX2 = grid[i][j + 2].getBlock(0);
                    }
                }
                if (i < grid.length - 2) {
                    if (!(grid[i + 1][j].isEmpty()) && !(grid[i + 2][j].isEmpty())) {
                        blockY1 = grid[i + 1][j].getBlock(0);
                        blockY2 = grid[i + 2][j].getBlock(0);
                    }
                }

				if (block != null) {
					/* Vérifier phrase sur la ligne */
					if (blockX1 != null && blockX2 != null) {
						if (block.getClass() == WordBlock.class && blockX1.getClass() == WordBlock.class
								&& blockX2.getClass() == WordBlock.class) {
							if (((WordBlock) block).getCategory() == EnumCategory.NOUN
									&& ((WordBlock) blockX1).getCategory() == EnumCategory.OPERATOR
									&& (((WordBlock) blockX2).getCategory() == EnumCategory.NOUN
											|| ((WordBlock) blockX2).getCategory() == EnumCategory.ATTRIBUTE)) {
								System.out.println("Les mots " + block + blockX1 + blockX2 + "forment une phrase");
								rules.add(new Rule(block.getName(), blockX2.getName()));
							}
						}
					}

					/* Vérifier phrase sur la colonne */
					if (blockY1 != null && blockY2 != null) {
						if (block.getClass() == WordBlock.class && blockY1.getClass() == WordBlock.class
								&& blockY2.getClass() == WordBlock.class) {
							if (((WordBlock) block).getCategory() == EnumCategory.NOUN
									&& ((WordBlock) blockY1).getCategory() == EnumCategory.OPERATOR
									&& (((WordBlock) blockY2).getCategory() == EnumCategory.NOUN
											|| ((WordBlock) blockY2).getCategory() == EnumCategory.ATTRIBUTE)) {
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

package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import model.elementList.EnumCategory;
import model.elementList.EnumDirection;
import model.elementList.EnumWord;
import model.input.InputData;

public class Model {
	private Cell[][] grid;
	private HashMap<EnumWord, Set<EnumWord>> rules;

	public Model(String file) {
		try {
			this.grid = InputData.readFile(file);
		} catch (IOException e) {
			throw new IllegalArgumentException("File " + file + " do not exist");
		}
		this.rules = this.generateRules(this.grid);
	}

	public int getNbLine() {
		return grid.length;
	}

	public int getNbColumn() {
		return grid[0].length;
	}

	public HashMap<EnumWord, Set<EnumWord>> getRules() {
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

	private String verifiedBlock(Block block) {
		if (rules.containsKey(block.getName()) && block.getClass() == ElementBlock.class) {
			var rule = rules.get(block.getName());
			if (rule.contains(EnumWord.STOP)) {
				return "STOP";
			} else if (rule.contains(EnumWord.PUSH)) {
				return "PUSH";
			} else if (rule.contains(EnumWord.YOU)) {
				return "YOU";
			} else if (rule.contains(EnumWord.DEFEAT)) {
				return "DEFEAT";
			}
		}
		return "OTHER";

	}

	public boolean moveBlock(int line, int column, Block block, EnumDirection dir) {
		System.out.println("Calling on grid[" + line + "][" + column + "] :");
		if (line < 0 || column < 0 || line >= grid.length || column >= grid[0].length) {
			System.out.println("     Out of grid !!!!!");
			return false;
		} else if (grid[line][column].isEmpty()) {
			System.out.println("     Nothing here, you can go here !!!!!");
			return true;
		}
		int lineNext;
		int columnNext;
		switch (dir) {
		case RIGHT: {
			System.out.println("RIGHT");
			lineNext = line;
			columnNext = column + 1;
			if (moveBlock(lineNext, columnNext, block, dir)) {
				for (int i = 0; i < grid[line][column].size(); i++) {
					Block actualBlock = grid[line][column].getBlock(i);
					if (verifiedBlock(actualBlock) == "STOP") {
						return false;
					}
				}
				for (int i = 0; i < grid[line][column].size(); i++) {
					Block actualBlock = grid[line][column].getBlock(i);
					System.out.println(verifiedBlock(actualBlock));
					if (verifiedBlock(actualBlock) == "PUSH" || verifiedBlock(actualBlock) == "YOU"
							|| actualBlock.getClass() == WordBlock.class) {
						grid[lineNext][columnNext].addBlock(actualBlock);
						grid[line][column].removeBlockAt(i);
					}
				}
				return true;
			} else {
				for (int i = 0; i < grid[line][column].size(); i++) {
					Block actualBlock = grid[line][column].getBlock(i);
					if (verifiedBlock(actualBlock) == "STOP" || verifiedBlock(actualBlock) == "PUSH" || actualBlock.getClass() == WordBlock.class) {
						return false;
					}
				}
				return true;
			}
		}
		case LEFT: {
			System.out.println("LEFT");
			lineNext = line;
			columnNext = column - 1;
			if (moveBlock(lineNext, columnNext, block, dir)) {
				for (int i = 0; i < grid[line][column].size(); i++) {
					Block actualBlock = grid[line][column].getBlock(i);
					if (verifiedBlock(actualBlock) == "STOP") {
						return false;
					}
				}
				for (int i = 0; i < grid[line][column].size(); i++) {
					Block actualBlock = grid[line][column].getBlock(i);
					System.out.println(verifiedBlock(actualBlock));
					if (verifiedBlock(actualBlock) == "PUSH" || verifiedBlock(actualBlock) == "YOU"
							|| actualBlock.getClass() == WordBlock.class) {
						grid[lineNext][columnNext].addBlock(actualBlock);
						grid[line][column].removeBlockAt(i);
					}
				}
				return true;
			} else {
				for (int i = 0; i < grid[line][column].size(); i++) {
					Block actualBlock = grid[line][column].getBlock(i);
					if (verifiedBlock(actualBlock) == "STOP" || verifiedBlock(actualBlock) == "PUSH" || actualBlock.getClass() == WordBlock.class) {
						return false;
					}
				}
				return true;
			}
		}
		case TOP: {
			System.out.println("TOP");
			lineNext = line - 1;
			columnNext = column;
			if (moveBlock(lineNext, columnNext, block, dir)) {
				for (int i = 0; i < grid[line][column].size(); i++) {
					Block actualBlock = grid[line][column].getBlock(i);
					if (verifiedBlock(actualBlock) == "STOP") {
						return false;
					}
				}
				for (int i = 0; i < grid[line][column].size(); i++) {
					Block actualBlock = grid[line][column].getBlock(i);
					System.out.println(verifiedBlock(actualBlock));
					if (verifiedBlock(actualBlock) == "PUSH" || verifiedBlock(actualBlock) == "YOU"
							|| actualBlock.getClass() == WordBlock.class) {
						grid[lineNext][columnNext].addBlock(actualBlock);
						grid[line][column].removeBlockAt(i);
					}
				}
				return true;
			} else {
				for (int i = 0; i < grid[line][column].size(); i++) {
					Block actualBlock = grid[line][column].getBlock(i);
					if (verifiedBlock(actualBlock) == "STOP" || verifiedBlock(actualBlock) == "PUSH" || actualBlock.getClass() == WordBlock.class) {
						return false;
					}
				}
				return true;
			}
		}
		case BOTTOM: {
			System.out.println("BOTTOM");
			lineNext = line + 1;
			columnNext = column;
			if (moveBlock(lineNext, columnNext, block, dir)) {
				for (int i = 0; i < grid[line][column].size(); i++) {
					Block actualBlock = grid[line][column].getBlock(i);
					if (verifiedBlock(actualBlock) == "STOP") {
						return false;
					}
				}
				for (int i = 0; i < grid[line][column].size(); i++) {
					Block actualBlock = grid[line][column].getBlock(i);
					System.out.println(verifiedBlock(actualBlock));
					if (verifiedBlock(actualBlock) == "PUSH" || verifiedBlock(actualBlock) == "YOU"
							|| actualBlock.getClass() == WordBlock.class) {
						grid[lineNext][columnNext].addBlock(actualBlock);
						grid[line][column].removeBlockAt(i);
					}
				}
				return true;
			} else {
				for (int i = 0; i < grid[line][column].size(); i++) {
					Block actualBlock = grid[line][column].getBlock(i);
					if (verifiedBlock(actualBlock) == "STOP" || verifiedBlock(actualBlock) == "PUSH" || actualBlock.getClass() == WordBlock.class) {
						return false;
					}
				}
				return true;
			}
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + dir);
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

	public HashMap<EnumWord, Set<EnumWord>> generateRules(Cell[][] grid) {
		HashMap<EnumWord, Set<EnumWord>> rules = new HashMap<>();

		rules = fillHashMap();

		/* System.out.println("\n***** GENERATE RULES *****\n"); */

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].isEmpty()) {
					continue;
				}
				for (int k = 0; k < grid[i][j].size(); k++) {
					Block block = grid[i][j].getBlock(k);
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
									/*
									 * System.out.println("Les mots " + block.getName() + " " + blockX1.getName() +
									 * " " + blockX2.getName() + " forment une phrase");
									 */

									Set<EnumWord> set = rules.get(block.getName());
									set.add(blockX2.getName());
									rules.put(block.getName(), set);
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
									/*
									 * System.out.println("Les mots " + block.getName() + " " + blockY1.getName() +
									 * " " + blockY2.getName() + " forment une phrase");
									 */

									Set<EnumWord> set = rules.get(block.getName());
									set.add(blockY2.getName());
									rules.put(block.getName(), set);
								}
							}
						}
					}
				}
			}
		}

		/* System.out.println("\n*** FIN GENERATE RULES ***\n"); */

		return rules;
	}

	private HashMap<EnumWord, Set<EnumWord>> fillHashMap() {
		HashMap<EnumWord, Set<EnumWord>> rules = new HashMap<>();

		rules.put(EnumWord.BABA, new HashSet<EnumWord>());
		rules.put(EnumWord.FLAG, new HashSet<EnumWord>());
		rules.put(EnumWord.WALL, new HashSet<EnumWord>());
		rules.put(EnumWord.WATER, new HashSet<EnumWord>());
		rules.put(EnumWord.SKULL, new HashSet<EnumWord>());
		rules.put(EnumWord.LAVA, new HashSet<EnumWord>());
		rules.put(EnumWord.ROCK, new HashSet<EnumWord>());

		return rules;
	}
}

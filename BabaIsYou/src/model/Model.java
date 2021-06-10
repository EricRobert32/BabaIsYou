package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
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

	private String verifiedBlock(int line, int column, Block block) {
		if (rules.containsKey(block.getName()) && !(block.getClass() == WordBlock.class)) {
			var rule = rules.get(block.getName());
			if (rule.contains(EnumWord.STOP)) {
				return "STOP";
			} else if (rule.contains(EnumWord.PUSH)) {
				return "PUSH";
			} else if (rule.contains(EnumWord.DEFEAT)) {
				return "DEFEAT"; /* LE BLOCK QUI ARRIVE MEURT */
			} else if (rule.contains(EnumWord.SINK)) {
				return "SINK"; /* LE BLOCK QUI ARRIVE MEURT */
			} else if (rule.contains(EnumWord.MELT)) {
				return "MELT"; /* EST DETRUIT SI LE BLOCK QUI ARRIVE EST HOT */
			} else if (rule.contains(EnumWord.WIN)) {
				return "WIN"; /* LE BLOCK QUI ARRIVE GAGNE */
			} else if (rule.contains(EnumWord.HOT)) {
				return "HOT"; /* DETRUIT LE BLOCK QUI ARRIVE SI CE BLOCK EST MELT */
			}
		}
		return "NOTHING";
	}

	public boolean moveBlock(int line, int column, Block block, EnumDirection dir) {
		/* For each block, if a block can t interact */
		for (int i = 0; i < grid[line][column].size(); i++) {
			if (verifiedBlock(line, column, grid[line][column].getBlock(i)) == "STOP") {
				return false;
			}
		}
		switch (dir) {
		case RIGHT:
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
		case LEFT:
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
		case TOP:
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
		case BOTTOM:
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

	public HashMap<EnumWord, Set<EnumWord>> generateRules(Cell[][] grid) {
		HashMap<EnumWord, Set<EnumWord>> rules = new HashMap<>();

		rules = fillHashMap();

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
								System.out.println("Les mots " + block.getName() + " " + blockX1.getName() + " " + blockX2.getName() + " forment une phrase");

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
								System.out.println("Les mots " + block.getName() + " " + blockY1.getName() + " " + blockY2.getName() + " forment une phrase");

								Set<EnumWord> set = rules.get(block.getName());
								set.add(blockY2.getName());
								rules.put(block.getName(), set);
							}
						}
					}
				}
			}
		}

		System.out.println("\n*** FIN GENERATE RULES ***\n");

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

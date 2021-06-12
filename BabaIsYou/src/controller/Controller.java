package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;
import model.Block;
import model.Cell;
import model.ElementBlock;
import model.Model;
import model.elementList.EnumDirection;
import model.elementList.EnumWord;
import view.Sprite;
import view.View;

public class Controller {
	public static void main(String[] args) {
		Application.run(Color.BLACK, context -> {
			String level0 = "level0.txt";
			String level1 = "level1.txt";
			String level2 = "level2.txt";
			String level3 = "level3.txt";
			String level4 = "level4.txt";
			String level5 = "level5.txt";
			String level6 = "level6.txt";

			System.out.println("Level 0");
			playLevel(context, level0);
			System.out.println("Level 1");
			playLevel(context, level1);
			System.out.println("Level 2");
			playLevel(context, level2);
			System.out.println("Level 3");
			playLevel(context, level3);
			System.out.println("Level 4");
			playLevel(context, level4);
			System.out.println("Level 5");
			playLevel(context, level5);
			System.out.println("Level 6");
			playLevel(context, level6);

			System.out.println("End of the game !");
			context.exit(0);
		});
	}

	public static void playLevel(ApplicationContext context, String level) {
		Model model = new Model(level);
		Cell[][] grid = model.getGrid();
		/*
		 * grid[0][0].addBlock(new ElementBlock(EnumWord.SKULL));
		 * grid[1][0].addBlock(new ElementBlock(EnumWord.WATER));
		 * grid[2][0].addBlock(new ElementBlock(EnumWord.WATER));
		 * grid[3][0].addBlock(new ElementBlock(EnumWord.LAVA));
		 */
		/* model.displayGrid(); */

		/******** Test des rules ********/
		System.out.println("\n***** TEST DES RULES *****\n");
		HashMap<EnumWord, Set<EnumWord>> rules = model.getRules();
		for (var rule : rules.entrySet()) {
			if (!(rule.getValue().isEmpty())) {
				for (var value : rule.getValue()) {
					System.out.println(rule.getKey() + " IS " + value);
				}
			}
		}
		System.out.println("\n*** FIN TEST DES RULES ***\n");
		/****** Fin test des rules ******/

		int sizeGridX = model.getNbLine();
		int sizeGridY = model.getNbColumn();
		System.out.println("sizeX (nb lignes) = " + sizeGridX + " | sizeY (nb colonnes) = " + sizeGridY);

		ScreenInfo screenInfo = context.getScreenInfo();
		float width = screenInfo.getWidth();
		float height = screenInfo.getHeight();

		System.out.println("size of the screen (" + width + " x " + height + ")");

		for (;;) {
			View.draw(context, sizeGridX, sizeGridY, model.getGrid());
			rules = model.getRules();
			Event event = context.pollOrWaitEvent(100);
			if (event == null) { // no event
				continue;
			}
			Action action = event.getAction();
			if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.Q) {
				System.out.println("Leaving the game !");
				context.exit(0);
				return;
			} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.R || noMoreXIsYou(rules, grid)) {
				System.out.println("Restarting this level !");
				playLevel(context, level);
				return;
			} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.N || blockIsYouAndWin(rules)
					|| blockYouIsOnBlockWin(rules, grid)) {
				System.out.println("You won this level, leaving this level !");
				return;
			} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.UP) {
				ArrayList<Block> blocks = new ArrayList<Block>();
				ArrayList<Integer[]> pos = new ArrayList<>();

				System.out.println("Going up !");
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						if (grid[i][j].isEmpty()) {
							continue;
						}
						for (int k = 0; k < grid[i][j].size(); k++) {
							Block block = grid[i][j].getBlock(k);
							if (block != null) {
								if (block.getClass() == ElementBlock.class) {
									Set<EnumWord> set = rules.get(block.getName());
									if (set.contains(EnumWord.YOU)) {
										/*
										 * System.out.println("MOVING ! elem block BABA from x = " + i + " & y = " + j +
										 * " to coords x = " + (i - 1) + " & y = " + j);
										 */
										blocks.add(block);
										Integer[] tmp = { i, j };
										pos.add(tmp);
									}
								}
							}
						}
					}
				}

				for (int i = 0; i < blocks.size(); i++) {
					model.moveBlock(pos.get(i)[0], pos.get(i)[1], blocks.get(i), EnumDirection.TOP);
				}

			} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.DOWN) {
				ArrayList<Block> blocks = new ArrayList<Block>();
				ArrayList<Integer[]> pos = new ArrayList<>();

				System.out.println("Going down !");
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						if (grid[i][j].isEmpty()) {
							continue;
						}
						for (int k = 0; k < grid[i][j].size(); k++) {
							Block block = grid[i][j].getBlock(k);
							if (block != null) {
								if (block.getClass() == ElementBlock.class) {
									Set<EnumWord> set = rules.get(block.getName());
									if (set.contains(EnumWord.YOU)) {
										/*
										 * System.out.println("MOVING ! elem block BABA from x = " + i + " & y = " + j +
										 * " to coords x = " + (i + 1) + " & y = " + j);
										 */
										blocks.add(block);
										Integer[] tmp = { i, j };
										pos.add(tmp);
									}
								}
							}
						}
					}
				}

				for (int i = blocks.size() - 1; i >= 0; i--) {
					model.moveBlock(pos.get(i)[0], pos.get(i)[1], blocks.get(i), EnumDirection.BOTTOM);
				}

			} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.RIGHT) {
				ArrayList<Block> blocks = new ArrayList<Block>();
				ArrayList<Integer[]> pos = new ArrayList<>();

				System.out.println("Going right !");
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						if (grid[i][j].isEmpty()) {
							continue;
						}
						for (int k = 0; k < grid[i][j].size(); k++) {
							Block block = grid[i][j].getBlock(k);
							if (block != null) {
								if (block.getClass() == ElementBlock.class) {
									Set<EnumWord> set = rules.get(block.getName());
									if (set.contains(EnumWord.YOU)) {
										/*
										 * System.out.println("MOVING ! elem block BABA from x = " + i + " & y = " + j +
										 * " to coords x = " + i + " & y = " + (j + 1));
										 */
										blocks.add(block);
										Integer[] tmp = { i, j };
										pos.add(tmp);
									}
								}
							}
						}
					}
				}

				for (int i = blocks.size() - 1; i >= 0; i--) {
					model.moveBlock(pos.get(i)[0], pos.get(i)[1], blocks.get(i), EnumDirection.RIGHT);
				}

			} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.LEFT) {
				ArrayList<Block> blocks = new ArrayList<Block>();
				ArrayList<Integer[]> pos = new ArrayList<>();

				System.out.println("Going left !");
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						if (grid[i][j].isEmpty()) {
							continue;
						}
						for (int k = 0; k < grid[i][j].size(); k++) {
							Block block = grid[i][j].getBlock(k);
							if (block != null) {
								if (block.getClass() == ElementBlock.class) {
									Set<EnumWord> set = rules.get(block.getName());
									if (set.contains(EnumWord.YOU)) {
										/*
										 * System.out.println("MOVING ! elem block BABA from x = " + i + " & y = " + j +
										 * " to coords x = " + i + " & y = " + (j - 1));
										 */
										blocks.add(block);
										Integer[] tmp = { i, j };
										pos.add(tmp);
									}
								}
							}
						}
					}
				}

				for (int i = 0; i < blocks.size(); i++) {
					model.moveBlock(pos.get(i)[0], pos.get(i)[1], blocks.get(i), EnumDirection.LEFT);
				}
			}

			model.refreshRules();

			/******** Test des rules ********/
			/*System.out.println("\n***** TEST DES RULES *****\n");
			rules = model.getRules();
			for (var rule : rules.entrySet()) {
				if (!(rule.getValue().isEmpty())) {
					for (var value : rule.getValue()) {
						System.out.println(rule.getKey() + " IS " + value);
					}
				}
			}
			System.out.println("\n*** FIN TEST DES RULES ***\n");*/
			/****** Fin test des rules ******/

			destroyBlockXIfIsOnBlockY(EnumWord.YOU, EnumWord.DEFEAT, rules, grid);
			destroyBlockXIfIsOnBlockY(EnumWord.MELT, EnumWord.HOT, rules, grid);
			destroyBlockXAndBlockSinkIsInTheSameCell(rules, grid);
			transformABlockIfARuleSaidSo(rules, grid);
		}
	}

	public static boolean noMoreXIsYou(HashMap<EnumWord, Set<EnumWord>> rules, Cell[][] grid) {
		Set<EnumWord> setBlocOfYou = new HashSet<EnumWord>();
		for (var rule : rules.entrySet()) {
			if (!(rule.getValue().isEmpty())) {
				for (var value : rule.getValue()) {
					if (value == EnumWord.YOU) {
						/* return false; *//* Vérifier si un "value" au moins est dans le jeu */
						setBlocOfYou.add(rule.getKey());
					}
				}
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				for (EnumWord word : setBlocOfYou) {
					for (int k = 0; k < grid[i][j].size(); k++) {
						Block block = grid[i][j].getBlock(k);
						if (block.getName() == word && block.getClass() == ElementBlock.class) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public static boolean blockIsYouAndWin(HashMap<EnumWord, Set<EnumWord>> rules) {
		boolean blockIsYou, blockIsWin;
		for (var rule : rules.entrySet()) {
			if (!(rule.getValue().isEmpty())) {
				blockIsYou = false;
				blockIsWin = false;
				for (var value : rule.getValue()) {
					if (value == EnumWord.YOU) {
						blockIsYou = true;
					} else if (value == EnumWord.WIN) {
						blockIsWin = true;
					}
				}
				if (blockIsYou && blockIsWin) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean blockYouIsOnBlockWin(HashMap<EnumWord, Set<EnumWord>> rules, Cell[][] grid) {
		boolean cellHasYou, cellHasWin;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				cellHasYou = false;
				cellHasWin = false;
				for (int k = 0; k < grid[i][j].size(); k++) {
					Block block = grid[i][j].getBlock(k);
					if (rules.get(block.getName()) != null && block.getClass() == ElementBlock.class) {
						if (rules.get(block.getName()).contains(EnumWord.YOU)) {
							cellHasYou = true;
						} else if (rules.get(block.getName()).contains(EnumWord.WIN)) {
							cellHasWin = true;
						}
					}
				}
				if (cellHasYou && cellHasWin) {
					return true;
				}
			}
		}
		return false;
	}

	public static void destroyBlockXIfIsOnBlockY(EnumWord enumWordX, EnumWord enumWordY,
			HashMap<EnumWord, Set<EnumWord>> rules, Cell[][] grid) {
		boolean cellHasX, cellHasY;
		HashMap<Integer, Block> mapBlockX;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				mapBlockX = new HashMap<>();
				cellHasX = false;
				cellHasY = false;
				for (int k = 0; k < grid[i][j].size(); k++) {
					Block block = grid[i][j].getBlock(k);
					if (rules.get(block.getName()) != null && block.getClass() == ElementBlock.class) {
						if (rules.get(block.getName()).contains(enumWordX)) {
							cellHasX = true;
							mapBlockX.put(k, block);
						}
						if (rules.get(block.getName()).contains(enumWordY)) {
							cellHasY = true;
						}
					}
				}
				if (cellHasX && cellHasY) {
					Iterator<Entry<Integer, Block>> iterator = mapBlockX.entrySet().iterator();
					while (iterator.hasNext()) {
						var pair = iterator.next();
						grid[i][j].removeBlockAt(pair.getKey());
						iterator.remove();
					}
				}
			}
		}
	}

	public static void destroyBlockXAndBlockSinkIsInTheSameCell(HashMap<EnumWord, Set<EnumWord>> rules, Cell[][] grid) {
		boolean cellHasOther, cellHasSink;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				cellHasOther = false;
				cellHasSink = false;
				for (int k = 0; k < grid[i][j].size(); k++) {
					Block block = grid[i][j].getBlock(k);
					if (rules.get(block.getName()) != null && block.getClass() == ElementBlock.class) {
						if (rules.get(block.getName()).contains(EnumWord.SINK)) {
							cellHasSink = true;
						} else {
							cellHasOther = true;
						}
					}
				}
				if (cellHasOther && cellHasSink) {
					grid[i][j].removeBlockAt(1);
					grid[i][j].removeBlockAt(0);
				}
			}
		}
	}

	public static void transformABlockIfARuleSaidSo(HashMap<EnumWord, Set<EnumWord>> rules, Cell[][] grid) {
		for (var rule : rules.entrySet()) {
			if (!(rule.getValue().isEmpty())) {
				for (var value : rule.getValue()) {
					switch (value.toString()) {
					case "BABA": {
						tranformAllBlockXToBlockY(rule.getKey(), EnumWord.BABA, grid);
						break;
					}
					case "FLAG": {
						tranformAllBlockXToBlockY(rule.getKey(), EnumWord.FLAG, grid);
						break;
					}
					case "WALL": {
						tranformAllBlockXToBlockY(rule.getKey(), EnumWord.WALL, grid);
						break;
					}
					case "WATER": {
						tranformAllBlockXToBlockY(rule.getKey(), EnumWord.WATER, grid);
						break;
					}
					case "SKULL": {
						tranformAllBlockXToBlockY(rule.getKey(), EnumWord.SKULL, grid);
						break;
					}
					case "LAVA": {
						tranformAllBlockXToBlockY(rule.getKey(), EnumWord.LAVA, grid);
						break;
					}
					case "ROCK": {
						tranformAllBlockXToBlockY(rule.getKey(), EnumWord.ROCK, grid);
						break;
					}
					default:
					}
				}
			}
		}
	}

	public static void tranformAllBlockXToBlockY(EnumWord enumWordX, EnumWord enumWordY, Cell[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				for (int k = 0; k < grid[i][j].size(); k++) {
					Block block = grid[i][j].getBlock(k);
					if (block.getName() == enumWordX && block.getClass() == ElementBlock.class) {
						grid[i][j].removeBlockAt(k);
						grid[i][j].addBlock(new ElementBlock(enumWordY));
					}
				}
			}
		}
	}
}

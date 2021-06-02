package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;
import model.Block;
import model.Cell;
import model.ElementBlock;
import model.Model;
import model.Rule;
import model.WordBlock;
import model.elementList.EnumCategory;
import model.elementList.EnumDirection;
import model.elementList.EnumWord;
import view.View;

public class Controller {
	public static void main(String[] args) {
		Model model = new Model("level0.txt");
		Cell[][] grid = model.getGrid();
		model.displayGrid();

		/******** Test des rules ********/
		System.out.println("\n***** TEST DES RULES *****\n");
		HashSet<Rule> rules = model.getRules();
		for (Rule rule : rules) {
			System.out.println(rule.getRuleFirstWord() + " IS " + rule.getRuleSecondWord());
		}
		System.out.println("\n*** FIN TEST DES RULES ***\n");
		/****** Fin test des rules ******/

		Application.run(Color.BLACK, context -> {
			int sizeGridX = model.getNbLine();
			int sizeGridY = model.getNbColumn();
			System.out.println("sizeX (nb lignes) = " + sizeGridX + " | sizeY (nb colonnes) = " + sizeGridY);
			/*
			 * WordBlock baba = new WordBlock(sizeGridX, sizeGridY, EnumWord.BABA, 0, 1,
			 * EnumCategory.NOUN); WordBlock is = new WordBlock(sizeGridX, sizeGridY,
			 * EnumWord.IS, 0, 2, EnumCategory.OPERATOR); WordBlock you = new
			 * WordBlock(sizeGridX, sizeGridY, EnumWord.YOU, 0, 3, EnumCategory.ATTRIBUTE);
			 * 
			 * ElementBlock joueur = new ElementBlock(sizeGridX, sizeGridY, EnumWord.YOU, 0,
			 * 0);
			 * 
			 * if (model.verifySentence(baba, is, you)) {
			 * System.out.println("The sentence '" + baba + " " + is + " " + you +
			 * "' is a valid sentence"); } else { System.out.println("The sentence '" + baba
			 * + " " + is + " " + you + "' is NOT a valid sentence"); }
			 * 
			 * baba.move(context, model, sizeGridX, sizeGridY, EnumDirection.EAST);
			 * 
			 * if (model.verifySentence(baba, is, you)) {
			 * System.out.println("The sentence '" + baba + " " + is + " " + you +
			 * "' is a valid sentence"); } else { System.out.println("The sentence '" + baba
			 * + " " + is + " " + you + "' is NOT a valid sentence"); }
			 * 
			 * is.move(context, model, sizeGridX, sizeGridY, EnumDirection.EAST);
			 * 
			 * if (model.verifySentence(baba, is, you)) {
			 * System.out.println("The sentence '" + baba + " " + is + " " + you +
			 * "' is a valid sentence"); } else { System.out.println("The sentence '" + baba
			 * + " " + is + " " + you + "' is NOT a valid sentence"); }
			 * 
			 * you.move(context, model, sizeGridX, sizeGridY, EnumDirection.EAST);
			 * 
			 * if (model.verifySentence(baba, is, you)) {
			 * System.out.println("The sentence '" + baba + " " + is + " " + you +
			 * "' is a valid sentence"); } else { System.out.println("The sentence '" + baba
			 * + " " + is + " " + you + "' is NOT a valid sentence"); }
			 */

			// get the size of the screen
			ScreenInfo screenInfo = context.getScreenInfo();
			float width = screenInfo.getWidth();
			float height = screenInfo.getHeight();
			System.out.println("size of the screen (" + width + " x " + height + ")");

			/*
			 * Block[] blocks = {joueur, baba, is, you}; View.draw(context, sizeGridX,
			 * sizeGridY, blocks);
			 */

			View.draw(context, sizeGridX, sizeGridY, model.getGrid());

			/* Area area = new Area(); */
			for (;;) {
				Event event = context.pollOrWaitEvent(10);
				if (event == null) { // no event
					continue;
				}
				Action action = event.getAction();
				if (event.getKey() == KeyboardKey.Q) {
					System.out.println("abort abort !");
					context.exit(0);
					return;
				} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.UP) {
					/*
					 * FAIRE UN TABLEAU DE BLOCKS ET APRÈS LA DOUBLE BOUCLE FOR APPELER MOVE SUR
					 * CHACUN DES BLOCS DU TABLEAU DE BLOCKS
					 */
					ArrayList<Block> blocks = new ArrayList<Block>();

					System.out.println("Going up !");
					for (int i = 0; i < grid.length; i++) {
						for (int j = 0; j < grid[0].length; j++) {
							if (grid[i][j].isEmpty()) {
								continue;
							}
							Block block = grid[i][j].getBlock(0);
							if (block != null) {
								if (block.getName() == EnumWord.BABA && block.getClass() == ElementBlock.class) {
									System.out.println("MOVING ! elem block BABA from x = " + block.getX() + " & y = "
											+ block.getY() + " to coords x = " + (block.getX() - 1) + " & y = "
											+ block.getY());
									/*
									 * block.move(context, model, sizeGridX, sizeGridY, EnumDirection.NORTH);
									 */
									blocks.add(block);
								}
							}
						}
					}

					for (Block block : blocks) {
						model.moveBlock(block.getX(), block.getY(), block, EnumDirection.NORTH);
					}

				} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.DOWN) {
					/*
					 * FAIRE UN TABLEAU DE BLOCKS ET APRÈS LA DOUBLE BOUCLE FOR APPELER MOVE SUR
					 * CHACUN DES BLOCS DU TABLEAU DE BLOCKS
					 */
					ArrayList<Block> blocks = new ArrayList<Block>();

					System.out.println("Going down !");
					for (int i = 0; i < grid.length; i++) {
						for (int j = 0; j < grid[0].length; j++) {
							if (grid[i][j].isEmpty()) {
								continue;
							}
							Block block = grid[i][j].getBlock(0);
							if (block != null) {
								if (block.getName() == EnumWord.BABA && block.getClass() == ElementBlock.class) {
									System.out.println("MOVING ! elem block BABA from x = " + block.getX() + " & y = "
											+ block.getY() + " to coords x = " + (block.getX() + 1) + " & y = "
											+ block.getY());
									/*
									 * block.move(context, model, sizeGridX, sizeGridY, EnumDirection.SOUTH);
									 */
									blocks.add(block);
								}
							}
						}
					}

					for (Block block : blocks) {
						model.moveBlock(block.getX(), block.getY(), block, EnumDirection.SOUTH);
					}

				} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.RIGHT) {
					/*
					 * FAIRE UN TABLEAU DE BLOCKS ET APRÈS LA DOUBLE BOUCLE FOR APPELER MOVE SUR
					 * CHACUN DES BLOCS DU TABLEAU DE BLOCKS
					 */
					ArrayList<Block> blocks = new ArrayList<Block>();

					System.out.println("Going right !");
					for (int i = 0; i < grid.length; i++) {
						for (int j = 0; j < grid[0].length; j++) {
							if (grid[i][j].isEmpty()) {
								continue;
							}
							Block block = grid[i][j].getBlock(0);
							if (block != null) {
								if (block.getName() == EnumWord.BABA && block.getClass() == ElementBlock.class) {
									System.out.println("MOVING ! elem block BABA from x = " + block.getX() + " & y = "
											+ block.getY() + " to coords x = " + block.getX() + " & y = "
											+ (block.getY() + 1));
									/*
									 * block.move(context, model, sizeGridX, sizeGridY, EnumDirection.EAST);
									 */
									blocks.add(block);
								}
							}
						}
					}

					for (Block block : blocks) {
						model.moveBlock(block.getX(), block.getY(), block, EnumDirection.EAST);
					}

				} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.LEFT) {
					/*
					 * FAIRE UN TABLEAU DE BLOCKS ET APRÈS LA DOUBLE BOUCLE FOR APPELER MOVE SUR
					 * CHACUN DES BLOCS DU TABLEAU DE BLOCKS
					 */
					ArrayList<Block> blocks = new ArrayList<Block>();

					System.out.println("Going left !");
					for (int i = 0; i < grid.length; i++) {
						for (int j = 0; j < grid[0].length; j++) {
							if (grid[i][j].isEmpty()) {
								continue;
							}
							Block block = grid[i][j].getBlock(0);
							if (block != null) {
								if (block.getName() == EnumWord.BABA && block.getClass() == ElementBlock.class) {
									System.out.println("MOVING ! elem block BABA from x = " + block.getX() + " & y = "
											+ block.getY() + " to coords x = " + block.getX() + " & y = "
											+ (block.getY() - 1));
									/*
									 * block.move(context, model, sizeGridX, sizeGridY, EnumDirection.WEST);
									 */
									blocks.add(block);
								}
							}
						}
					}

					for (Block block : blocks) {
						model.moveBlock(block.getX(), block.getY(), block, EnumDirection.WEST);
					}

				}

				/*
				 * else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.UP) {
				 * System.out.println("Going up !"); grid[4][1].getBlock(0).move(context, model,
				 * sizeGridX, sizeGridY, EnumDirection.NORTH); } else if (action ==
				 * Action.KEY_PRESSED && event.getKey() == KeyboardKey.DOWN) {
				 * grid[4][1].getBlock(0).move(context, model, sizeGridX, sizeGridY,
				 * EnumDirection.SOUTH); } else if (action == Action.KEY_PRESSED &&
				 * event.getKey() == KeyboardKey.RIGHT) { System.out.println("Going right !");
				 * grid[4][1].getBlock(0).move(context, model, sizeGridX, sizeGridY,
				 * EnumDirection.EAST); } else if (action == Action.KEY_PRESSED &&
				 * event.getKey() == KeyboardKey.LEFT) { System.out.println("Going left !");
				 * grid[4][1].getBlock(0).move(context, model, sizeGridX, sizeGridY,
				 * EnumDirection.WEST); }
				 */

				View.draw(context, sizeGridX, sizeGridY, model.getGrid());
				model.displayGrid();

				model.refreshRules();
				System.out.println("\n***** TEST DES RULES *****\n");
				HashSet<Rule> rule_temp = model.getRules();
				for (Rule rule : rule_temp) {
					System.out.println(rule.getRuleFirstWord() + " IS " + rule.getRuleSecondWord());
				}
				System.out.println("\n*** FIN TEST DES RULES ***\n");

				System.out.println(event);
			}
		});
	}

}

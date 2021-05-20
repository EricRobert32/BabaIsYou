package controller;

import java.awt.Color;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;
import model.Block;
import model.Cell;
import model.ElementBlock;
import model.Model;
import model.WordBlock;
import model.elementList.EnumCategory;
import model.elementList.EnumDirection;
import model.elementList.EnumWord;
import view.View;

public class Controller {
	public static void main(String[] args) {
		Model model = new Model("level0.txt");
		model.displayGrid();

		Application.run(Color.BLACK, context -> {
			int sizeGridX = 11;
			int sizeGridY = 9;
			WordBlock baba = new WordBlock(sizeGridX, sizeGridY, EnumWord.BABA, 0, 1, EnumCategory.NOUN);
			WordBlock is = new WordBlock(sizeGridX, sizeGridY, EnumWord.IS, 0, 2, EnumCategory.OPERATOR);
			WordBlock you = new WordBlock(sizeGridX, sizeGridY, EnumWord.YOU, 0, 3, EnumCategory.ATTRIBUTE);

			ElementBlock joueur = new ElementBlock(sizeGridX, sizeGridY, EnumWord.YOU, 0, 0);

			if (model.verifySentence(baba, is, you)) {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is a valid sentence");
			} else {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is NOT a valid sentence");
			}

			baba.move(context, model, sizeGridX, sizeGridY, EnumDirection.EAST);

			if (model.verifySentence(baba, is, you)) {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is a valid sentence");
			} else {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is NOT a valid sentence");
			}

			is.move(context, model, sizeGridX, sizeGridY, EnumDirection.EAST);

			if (model.verifySentence(baba, is, you)) {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is a valid sentence");
			} else {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is NOT a valid sentence");
			}

			you.move(context, model, sizeGridX, sizeGridY, EnumDirection.EAST);

			if (model.verifySentence(baba, is, you)) {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is a valid sentence");
			} else {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is NOT a valid sentence");
			}

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
				}/* else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.UP) {
					System.out.println("Going up !");
					Cell[][] grid = model.getGrid();
					for (int j = 0; j < grid[0].length; j++) {
						for (int i = 0; i < grid.length; i++) {
							Block block = grid[i][j].getBlock(0);
							if (block != null) {
								if (block.getName() == EnumWord.BABA && block.getClass() == ElementBlock.class) {
									System.out.println("MOVING !");
									grid[i][j].getBlock(0).move(context, model, sizeGridX, sizeGridY,
											EnumDirection.NORTH);
								}
							}
						}
					}
				} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.DOWN) {
					System.out.println("Going down !");
					Cell[][] grid = model.getGrid();
					for (int j = 0; j < grid[0].length; j++) {
						for (int i = 0; i < grid.length; i++) {
							Block block = grid[i][j].getBlock(0);
							if (block != null) {
								if (block.getName() == EnumWord.BABA && block.getClass() == ElementBlock.class) {
									System.out.println("MOVING !");
									grid[i][j].getBlock(0).move(context, model, sizeGridX, sizeGridY,
											EnumDirection.SOUTH);
								}
							}
						}
					}
				} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.RIGHT) {
					System.out.println("Going right !");
					Cell[][] grid = model.getGrid();
					for (int j = 0; j < grid[0].length; j++) {
						for (int i = 0; i < grid.length; i++) {
							Block block = grid[i][j].getBlock(0);
							if (block != null) {
								if (block.getName() == EnumWord.BABA && block.getClass() == ElementBlock.class) {
									System.out.println("MOVING !");
									grid[i][j].getBlock(0).move(context, model, sizeGridX, sizeGridY,
											EnumDirection.EAST);
								}
							}
						}
					}
				} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.LEFT) {
					System.out.println("Going left !");
					Cell[][] grid = model.getGrid();
					for (int j = 0; j < grid[0].length; j++) {
						for (int i = 0; i < grid.length; i++) {
							Block block = grid[i][j].getBlock(0);
							if (block != null) {
								if (block.getName() == EnumWord.BABA && block.getClass() == ElementBlock.class) {
									System.out.println("MOVING !");
									grid[i][j].getBlock(0).move(context, model, sizeGridX, sizeGridY,
											EnumDirection.WEST);
								}
							}
						}
					}
				}*/
				
				else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.UP) {
					System.out.println("Going up !");
					model.getGrid()[1][4].getBlock(0).move(context, model, sizeGridX, sizeGridY, EnumDirection.NORTH);
				} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.DOWN) {
					System.out.println("Going down !");
					model.getGrid()[1][4].getBlock(0).move(context, model, sizeGridX, sizeGridY, EnumDirection.SOUTH);
				} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.RIGHT) {
					System.out.println("Going right !");
					model.getGrid()[1][4].getBlock(0).move(context, model, sizeGridX, sizeGridY, EnumDirection.EAST);
				} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.LEFT) {
					System.out.println("Going left !");
					model.getGrid()[1][4].getBlock(0).move(context, model, sizeGridX, sizeGridY, EnumDirection.WEST);
				}

				View.draw(context, sizeGridX, sizeGridY, model.getGrid());
				System.out.println(event);
			}
		});
	}

}

package controller;

import java.awt.Color;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;
import model.Block;
import model.ElementBlock;
import model.Model;
import model.WordBlock;
import model.elementList.EnumCategory;
import model.elementList.EnumDirection;
import model.elementList.EnumWord;
import view.View;

public class Controller {
	public static void main(String[] args) {
		Model modele = new Model(10, 10);
		
		Application.run(Color.BLACK, context -> {
			int size_grid = 10;
			WordBlock baba = new WordBlock(size_grid, EnumWord.BABA, 0, 1, EnumCategory.NOUN);
			WordBlock is = new WordBlock(size_grid, EnumWord.IS, 0, 2, EnumCategory.OPERATOR);
			WordBlock you = new WordBlock(size_grid, EnumWord.YOU, 0, 3, EnumCategory.ATTRIBUTE);

			ElementBlock joueur = new ElementBlock(size_grid, EnumWord.YOU, 0, 0);

			if (modele.verifySentence(baba, is, you)) {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is a valid sentence");
			} else {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is NOT a valid sentence");
			}

			baba.move(context, size_grid, EnumDirection.EAST);

			if (modele.verifySentence(baba, is, you)) {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is a valid sentence");
			} else {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is NOT a valid sentence");
			}

			is.move(context, size_grid, EnumDirection.EAST);

			if (modele.verifySentence(baba, is, you)) {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is a valid sentence");
			} else {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is NOT a valid sentence");
			}

			you.move(context, size_grid, EnumDirection.EAST);

			if (modele.verifySentence(baba, is, you)) {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is a valid sentence");
			} else {
				System.out.println("The sentence '" + baba + " " + is + " " + you + "' is NOT a valid sentence");
			}

			// get the size of the screen
			ScreenInfo screenInfo = context.getScreenInfo();
			float width = screenInfo.getWidth();
			float height = screenInfo.getHeight();
			System.out.println("size of the screen (" + width + " x " + height + ")");
			
			Block[] blocks = {joueur, baba, is, you};
			
			View.draw(context, size_grid, blocks);

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
					System.out.println("Going up !");
					joueur.move(context, size_grid, EnumDirection.NORTH);
				} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.DOWN) {
					System.out.println("Going down !");
					joueur.move(context, size_grid, EnumDirection.SOUTH);
				} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.RIGHT) {
					System.out.println("Going right !");
					joueur.move(context, size_grid, EnumDirection.EAST);
				} else if (action == Action.KEY_PRESSED && event.getKey() == KeyboardKey.LEFT) {
					System.out.println("Going left !");
					joueur.move(context, size_grid, EnumDirection.WEST);
				}
				
				View.draw(context, size_grid, blocks);
				System.out.println(event);
			}
		});
	}

}

package model.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

import model.Block;
import model.Cell;
import model.WordBlock;
import model.ElementBlock;
import model.Rule;
import model.elementList.EnumCategory;
import model.elementList.EnumWord;

public class InputData {
	public static Cell[][] readFile(String file) {
		System.out.println("\n******* INPUT DATA *******\n");
		int width = 0;
		int height = 0;

		Scanner sc = null;
		try {
			sc = new Scanner(new File("./src/levels/" + file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (sc.hasNextLine()) {
			Scanner sc2 = new Scanner(sc.nextLine());
			while (sc2.hasNext()) {
				sc2.next();
				width++;
			}
			sc2.close();
			height++;
		}
		width = width / height;
		sc.close();

		System.out.println("Height = " + height + " ; width = " + width);

		Cell[][] grid = new Cell[height][width];

		sc = null;
		try {
			sc = new Scanner(new File("./src/levels/" + file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int i = 0, j = 0;
		while (sc.hasNextLine()) {
			Scanner sc2 = new Scanner(sc.nextLine());
			j = 0;
			while (sc2.hasNext()) {
				String s = sc2.next();
				grid[i][j] = new Cell();
				/*
				 * System.out.println("Calling with height = " + height + " ; width = " + width
				 * + " ; i = " + i + " ; j = " + j);
				 */
				Block b = stringToBlock(height, width, i, j, s);
				if (b != null) {
					grid[i][j].addBlock(b);
				}
				j++;
			}
			sc2.close();
			i++;
		}
		sc.close();
		System.out.println("\n***** FIN INPUT DATA *****\n");
		return grid;
	}

	private static Block stringToBlock(int sizeGridX, int sizeGridY, int x, int y, String s) {
		Block block = null;
		return switch (s) {
		case "W-BABA" -> block = new WordBlock(EnumWord.BABA, EnumCategory.NOUN);
		case "W-FLAG" -> block = new WordBlock(EnumWord.FLAG, EnumCategory.NOUN);
		case "W-WALL" -> block = new WordBlock(EnumWord.WALL, EnumCategory.NOUN);
		case "W-WATER" -> block = new WordBlock(EnumWord.WATER, EnumCategory.NOUN);
		case "W-SKULL" -> block = new WordBlock(EnumWord.SKULL, EnumCategory.NOUN);
		case "W-LAVA" -> block = new WordBlock(EnumWord.LAVA, EnumCategory.NOUN);
		case "W-ROCK" -> block = new WordBlock(EnumWord.ROCK, EnumCategory.NOUN);
		case "W-IS" -> block = new WordBlock(EnumWord.IS, EnumCategory.OPERATOR);
		case "W-YOU" -> block = new WordBlock(EnumWord.YOU, EnumCategory.ATTRIBUTE);
		case "W-WIN" -> block = new WordBlock(EnumWord.WIN, EnumCategory.ATTRIBUTE);
		case "W-STOP" -> block = new WordBlock(EnumWord.STOP, EnumCategory.ATTRIBUTE);
		case "W-PUSH" -> block = new WordBlock(EnumWord.PUSH, EnumCategory.ATTRIBUTE);
		case "W-MELT" -> block = new WordBlock(EnumWord.MELT, EnumCategory.ATTRIBUTE);
		case "W-HOT" -> block = new WordBlock(EnumWord.HOT, EnumCategory.ATTRIBUTE);
		case "W-DEFEA" -> block = new WordBlock(EnumWord.DEFEAT, EnumCategory.ATTRIBUTE);
		case "W-SINK" -> block = new WordBlock(EnumWord.SINK, EnumCategory.ATTRIBUTE);
		case "E-BABA" -> block = new ElementBlock(EnumWord.BABA);
		case "E-FLAG" -> block = new ElementBlock(EnumWord.FLAG);
		case "E-WALL" -> block = new ElementBlock(EnumWord.WALL);
		case "E-WATER" -> block = new ElementBlock(EnumWord.WATER);
		case "E-SKULL" -> block = new ElementBlock(EnumWord.SKULL);
		case "E-LAVA" -> block = new ElementBlock(EnumWord.LAVA);
		case "E-ROCK" -> block = new ElementBlock(EnumWord.ROCK);
		default -> block;
		};
	}
}

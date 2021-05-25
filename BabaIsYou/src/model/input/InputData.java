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
				/*System.out.println("Calling with height = " + height + " ; width = " + width + " ; i = " + i + " ; j = " + j);*/
				grid[i][j].addBlock(stringToBlock(height, width, i, j, s));
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
		case "W-BABA" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.BABA, x, y, EnumCategory.NOUN);
		case "W-FLAG" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.FLAG, x, y, EnumCategory.NOUN);
		case "W-WALL" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.WALL, x, y, EnumCategory.NOUN);
		case "W-WATER" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.WATER, x, y, EnumCategory.NOUN);
		case "W-SKULL" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.SKULL, x, y, EnumCategory.NOUN);
		case "W-LAVA" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.LAVA, x, y, EnumCategory.NOUN);
		case "W-ROCK" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.ROCK, x, y, EnumCategory.NOUN);
		case "W-IS" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.IS, x, y, EnumCategory.OPERATOR);
		case "W-YOU" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.YOU, x, y, EnumCategory.ATTRIBUTE);
		case "W-WIN" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.WIN, x, y, EnumCategory.ATTRIBUTE);
		case "W-STOP" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.STOP, x, y, EnumCategory.ATTRIBUTE);
		case "W-PUSH" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.PUSH, x, y, EnumCategory.ATTRIBUTE);
		case "W-MELT" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.MELT, x, y, EnumCategory.ATTRIBUTE);
		case "W-HOT" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.HOT, x, y, EnumCategory.ATTRIBUTE);
		case "W-DEFEAT" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.DEFEAT, x, y, EnumCategory.ATTRIBUTE);
		case "W-SINK" -> block = new WordBlock(sizeGridX, sizeGridY, EnumWord.SINK, x, y, EnumCategory.ATTRIBUTE);
		case "E-BABA" -> block = new ElementBlock(sizeGridX, sizeGridY, EnumWord.BABA, x, y);
		case "E-FLAG" -> block = new ElementBlock(sizeGridX, sizeGridY, EnumWord.FLAG, x, y);
		case "E-WALL" -> block = new ElementBlock(sizeGridX, sizeGridY, EnumWord.WALL, x, y);
		case "E-WATER" -> block = new ElementBlock(sizeGridX, sizeGridY, EnumWord.WATER, x, y);
		case "E-SKULL" -> block = new ElementBlock(sizeGridX, sizeGridY, EnumWord.SKULL, x, y);
		case "E-LAVA" -> block = new ElementBlock(sizeGridX, sizeGridY, EnumWord.LAVA, x, y);
		case "E-ROCK" -> block = new ElementBlock(sizeGridX, sizeGridY, EnumWord.ROCK, x, y);
		default -> block;
		};
	}
}

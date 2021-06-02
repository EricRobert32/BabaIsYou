package view;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import fr.umlv.zen5.ApplicationContext;
import model.Block;
import model.Cell;
import model.ElementBlock;
import model.WordBlock;
import model.elementList.EnumWord;

public class View {
	public static void draw(ApplicationContext context, int sizeGridX, int sizeGridY, Cell[][] grid) {
		float width = context.getScreenInfo().getWidth();
		float height = context.getScreenInfo().getHeight();

		context.renderFrame(graphics -> {
			graphics.setColor(Color.BLACK);
			graphics.fill(new Rectangle2D.Float(0, 0, width, height));
		});

		/*
		 * for (Block block : blocks) { float minX = block.getX() * (width / sizeGridX);
		 * float maxX = width / sizeGridX; float minY = block.getY() * (height /
		 * sizeGridY); float maxY = height / sizeGridY;
		 * 
		 * context.renderFrame(graphics -> { if (block.getClass() == ElementBlock.class)
		 * { graphics.setColor(Color.WHITE); } else { graphics.setColor(Color.RED); }
		 * graphics.fill(new Rectangle2D.Float(minX, minY, maxX, maxY)); }); }
		 */

		context.renderFrame(graphics -> {
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					int minY = (int) (j * (width / sizeGridY));
					int maxY = (int) (width / sizeGridY);
					int minX = (int) (i * (height / sizeGridX));
					int maxX = (int) (height / sizeGridX);

					Block block;
					if (grid[i][j].isEmpty()) {
						block = null;
					} else {
						block = grid[i][j].getBlock(0);
					}

					if (block != null) {
						/*
						 * if (block.getClass() == ElementBlock.class) { graphics.setColor(Color.WHITE);
						 * } else if (block.getClass() == WordBlock.class) {
						 * graphics.setColor(Color.RED); } graphics.fill(new Rectangle2D.Float(minY,
						 * minX, maxY, maxX));
						 */
						/*try {
							if (block.getClass() == ElementBlock.class && block.getName() == EnumWord.BABA) {
								var image = ImageIO
										.read(Objects.requireNonNull(View.class.getResourceAsStream("../sprites/baba.png")));
								graphics.drawImage(image, minY, minX, maxY, maxX, null);
							} else {
								if (block.getClass() == ElementBlock.class) {
									graphics.setColor(Color.WHITE);
								} else if (block.getClass() == WordBlock.class) {
									graphics.setColor(Color.RED);
								}
								graphics.fill(new Rectangle2D.Float(minY, minX, maxY, maxX));
							}
						} catch (IOException e) {
							e.printStackTrace();
						}*/
						
						block.getImage().draw(graphics, minY, minX, maxY, maxX);
					}
				}
			}
		});
	}
}

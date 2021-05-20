package view;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import fr.umlv.zen5.ApplicationContext;
import model.Block;
import model.Cell;
import model.ElementBlock;
import model.WordBlock;

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
			for (int j = 0; j < grid[0].length; j++) {
				for (int i = 0; i < grid.length; i++) {
					float minX = i * (width / sizeGridX);
					float maxX = width / sizeGridX;
					float minY = j * (height / sizeGridY);
					float maxY = height / sizeGridY;

					Block block = grid[i][j].getBlock(0);

					if (block != null) {
						if (block.getClass() == ElementBlock.class) {
							graphics.setColor(Color.WHITE);
						} else if (block.getClass() == WordBlock.class) {
							graphics.setColor(Color.RED);
						}
						graphics.fill(new Rectangle2D.Float(minX, minY, maxX, maxY));
					}
				}
			}
		});
	}
}

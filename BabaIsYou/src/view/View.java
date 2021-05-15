package view;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import fr.umlv.zen5.ApplicationContext;
import model.Block;
import model.ElementBlock;

public class View {
	public static void draw(ApplicationContext context, int size_grid, Block... blocks) {
		float width = context.getScreenInfo().getWidth();
		float height = context.getScreenInfo().getHeight();
		
		context.renderFrame(graphics -> {
			graphics.setColor(Color.BLACK);
			graphics.fill(new Rectangle2D.Float(0, 0, width, height));
		});
		
		for (Block block : blocks) {
			float minX = block.getX() * (width / size_grid);
			float maxX = width / 10;
			float minY = block.getY() * (height / size_grid);
			float maxY = height / 10;
			
			context.renderFrame(graphics -> {
				if (block.getClass() == ElementBlock.class) {
					graphics.setColor(Color.WHITE);
				}
				else {
					graphics.setColor(Color.RED);
				}
				graphics.fill(new Rectangle2D.Float(minX, minY, maxX, maxY));
			});
		}
		
		
	}
}

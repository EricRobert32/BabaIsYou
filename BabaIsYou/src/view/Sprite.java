package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import model.elementList.EnumWord;

/**
 * Sprite of the block
 * @author BARBÉ Romain
 * @author ROBERT Eric
 *
 */
public class Sprite {
	private BufferedImage image;
	
	/**
	 * Constructor of Sprite
	 * @param word element of the block
	 * @param type Element (E) or Word(W) type
	 */
	public Sprite(EnumWord word, String type) {
		try {
			this.image = ImageIO.read(Objects.requireNonNull(Sprite.class.getResourceAsStream("/" + type + word + ".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Draw a Sprite
	 * @param graphics
	 * @param minY 
	 * @param minX
	 * @param maxY
	 * @param maxX
	 */
	public void draw(Graphics2D graphics, int minY, int minX, int maxY, int maxX) {
		graphics.drawImage(this.image, minY, minX, maxY, maxX, null);
	}
}

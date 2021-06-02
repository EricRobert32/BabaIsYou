package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import model.elementList.EnumCategory;
import model.elementList.EnumWord;

public class Sprite {
	private BufferedImage image;
	public Sprite(EnumWord word, String type) {
		System.out.println(word);
		try {
			this.image = ImageIO.read(Objects.requireNonNull(Sprite.class.getResourceAsStream("/" + type + word + ".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D graphics, int minY, int minX, int maxY, int maxX) {
		graphics.drawImage(this.image, minY, minX, maxY, maxX, null);
	}
}

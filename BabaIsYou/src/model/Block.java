package model;

import fr.umlv.zen5.ApplicationContext;
import model.elementList.EnumDirection;
import model.elementList.EnumWord;
import view.Sprite;

/**
 * Interface that give methods for the blocks
 * @author Éric Robert
 * @author Romain Barbé
 * @version 1
 */
public sealed interface Block permits AbstractBlock{
	int getX();
	int getY();
	EnumWord getName();
	public Sprite getImage();
	void move(EnumDirection dir);
	String toString();
}

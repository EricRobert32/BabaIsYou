package model;

import model.elementList.EnumWord;
import view.Sprite;

/**
 * Interface that give methods for the blocks
 * @author Éric Robert
 * @author Romain Barbé
 * @version 1
 */
public sealed interface Block permits AbstractBlock{
	EnumWord getName();
	public Sprite getImage();
}

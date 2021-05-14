package modele;

import fr.umlv.zen5.ApplicationContext;

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
	boolean move(ApplicationContext context, int size_grid, EnumDirection direction);
}

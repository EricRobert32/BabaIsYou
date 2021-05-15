package model.elementList;

/**
 * Enum for the words
 * @author Éric Robert
 * @author Romain Barbé
 * @version 1
 */
public enum EnumWord  {
	BABA, FLAG, WALL, WATER, SKULL, LAVA, ROCK,

	IS,

	YOU, WIN, STOP, PUSH, MELT, HOT, DEFEAT, SINK;

	/*public boolean wordForElement(EnumWord p) {
		switch (p) {
		case IS: {
			return false;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + p);
		}
	}*/
}

package modele_GLOB;

public enum Prout {
	
	flag,rock,fire,wall,baba,water,skull,door,key,is,you,stop;
	
	public boolean wordForElement(Prout p) {
		
		switch (p) {
		case is: {
			return false;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + p);
		}
	}
	
}

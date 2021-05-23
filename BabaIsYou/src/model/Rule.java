package model;

import model.elementList.EnumWord;

public class Rule {
	private final EnumWord a;
	private final EnumWord b;

	public Rule(EnumWord a, EnumWord b) {
		this.a = a;
		this.b = b;
	}
	
	public EnumWord getRuleFirstWord() {
		return this.a;
	}
	
	public EnumWord getRuleSecondWord() {
		return this.b;
	}
}

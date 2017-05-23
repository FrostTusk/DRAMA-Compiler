package model;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class URL {

	public URL(String target) {
		this.target = target;
	}
	
	
	private final String target;
	
	
	public String getTarget() {
		return this.target;
	}
	
}

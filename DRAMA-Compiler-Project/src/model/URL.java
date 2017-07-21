package model;

import be.kuleuven.cs.som.annotate.Value;

/**
 * A class that represents a URL.
 * 
 * @author Mathijs Hubrechtsen
 *
 */
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

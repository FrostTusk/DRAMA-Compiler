package model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Value;

/**
 * A class that represents a URL.
 * @author	Mathijs Hubrechtsen
 */
@Value
public class URL {
	
	/**
	 * Create a new URL object that points to a given target.
	 * @param	target
	 * 			The target to which this URL points.
	 */
	public URL(String target) {
		this.target = target;
	}
	
	/**
	 * Variable containing the target of this URL.
	 */
	private final String target;
	
	
	/**
	 * Get the target of this URL.
	 */
	@Basic
	public String getTarget() {
		return this.target;
	}
	
}

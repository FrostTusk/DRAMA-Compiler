package model;

import be.kuleuven.cs.som.annotate.Value;

/**
 * A class representing a DRAMA label.
 * 
 * @author Mathijs Hubrechtsen
 *
 */
@Value
public class Label {
	
	public Label(String name, String location) {
		this.name = name;
		this.location = location;
	}
	
	
	private final String name;
	private final String location;
	
	
	public String getName() {
		return this.name;
	}
	
	public String getLocation() {
		return this.location;
	}
	
}

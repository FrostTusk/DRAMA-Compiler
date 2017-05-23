package model;

public class Label {
	
	
	public Label(String name, String location) {
		this.name = name;
		this.location = location;
	}
	
	
	private String name;
	private String location;
	
	
	public String getName() {
		return this.name;
	}
	
	public String getLocation() {
		return this.location;
	}
	
}

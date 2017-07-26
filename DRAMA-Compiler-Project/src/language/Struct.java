package language;

import java.util.List;
import java.util.NoSuchElementException;

import language.expressions.Storeable;
import model.Compilable;

public class Struct implements Compilable {
	
	public Struct(List<Storeable> fields, String name) {
		
	}
	
	
	private List<Storeable> fields;
	private String name;
	
	
	public List<Storeable> getFields() {
		return fields;
	}
	
	public String getName() {
		return name;
	}
	
	

	@Override
	public void compile() throws IllegalArgumentException, NoSuchElementException, NullPointerException {
		// TODO Auto-generated method stub
		
	}
	
}

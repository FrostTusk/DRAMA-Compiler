package model;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import language.Function;
import language.Struct;
import language.Variable;

/**
 * A class that represent a DRAMA program.
 * 
 * @author Mathijs Hubrechtsen
 *
 */
public class Program {

	/**
	 * Create a new Program object.
	 * 
	 * @param functions
	 * @param variables
	 * @param structs
	 */
	public Program(List<Function> functions, List<Variable> variables, List<Struct> structs) {
		setFunctions(functions);
	}
	
	
	private URL url;
	private Map<String, Function> functionsMap;
	
	
	public URL getUrl() {
		return this.url;
	}
	
	public Function getFunction(String name) {
		if (!functionsMap.containsKey(name))
			throw new NoSuchElementException();
		return functionsMap.get(name);
	}
	
	
	public boolean canHaveAsFunction(Function function) {
		for (String ownedName: functionsMap.keySet())
			if (ownedName.equals(function.getName()))
				return false;
		return true;
	}
	
	
	private void setURL(URL url) {
		this.url = url;
	}
	
	private void setFunctions(List<Function> functions) throws IllegalArgumentException {
		for (Function function: functions) {
			if (!canHaveAsFunction(function))
				throw new IllegalArgumentException();
			this.functionsMap.put(function.getName(), function);
		}
		
		try {
			getFunction("main");
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	
	
	public void compile(URL url) {
		setURL(url);
		getFunction("main").compile();
	}

}

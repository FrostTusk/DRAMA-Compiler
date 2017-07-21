package model;

import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.Function;
import language.Struct;
import language.expressions.VariableExpression;

/**
 * A class that represent a DRAMA program.
 * @invar	A Program must be able to have each of it's functions as a function.
 * @invar	A Program must be able to have each of it's structs as a struct. 
 * @invar	A Program must be able to have each of it's variables as a variable. 
 * 
 * @author Mathijs Hubrechtsen
 */
public class Program {

	/**
	 * Create a new Program object.
	 * @param	functions 
	 * 			the given function
	 * @param 	variables 
	 * 			the given variables
	 * @param 	structs 
	 * 			the given structs
	 */
	public Program(List<Function> functions, List<Struct> structs, List<VariableExpression> variables) {

		setFunctions(functions);
		setStructs(structs);
		setGlobalVars(variables);
	}
	
	
	/**
	 * A Map containing all the functions of this program and their names,
	 * with each function mapped to it's name/
	 */
	private Map<String, Function> functionsMap;
	/**
	 * A List containing all the structs of this program.
	 */
	private List<Struct> structs;
	/**
	 * A List containing all the global variables of this program.
	 */
	private List<VariableExpression> globalVars;

	
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
	
	public boolean canHaveAsStruct(Struct struct) {
		for (Struct ownedStruct: structs)
			if (ownedStruct.getName().equals(struct.getName()))
				return false;
		return true;
	}
	
	public boolean canHaveAsGlobalVar(VariableExpression variable) {
		for (VariableExpression ownedVariable: globalVars)
			if (ownedVariable.getName().equals(variable.getName()))
				return false;
		return true;
	}
	
	
	private void setFunctions(List<Function> functions) throws IllegalArgumentException {
		for (Function function: functions) {
			if (!canHaveAsFunction(function))
				throw new IllegalArgumentException();
			functionsMap.put(function.getName(), function);
		}
		try {
			getFunction("main");
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private void setStructs(List<Struct> structs) {
		for (Struct struct: structs) {
			if (!canHaveAsStruct(struct))
				throw new IllegalArgumentException();
			this.structs.add(struct);
		}
	}
	
	private void setGlobalVars(List<VariableExpression> globalVars) {
		for (VariableExpression var: globalVars) {
			if (!canHaveAsGlobalVar(var))
				throw new IllegalArgumentException();
			this.globalVars.add(var);
		}
	}

	
	
	private URL url;
	private String outputTracker;
	@SuppressWarnings("unused")
	private PrintStream console = new PrintStream(new FileOutputStream(FileDescriptor.out));
	private PrintStream file;

	
	@Basic @Raw
	public URL getUrl() {
		return this.url;
	}

	
	/**
	 * Sets the internal URL of this Program to the given URL, this will also update
	 * the internal file PrintStream. 
	 * @param 	url
	 * 			The new URL.
	 * 
	 * @throws 	FileNotFoundException
	 * 			The give URL does not point to a valid output path. 
	 */
	@Raw
	private void setURL(URL url) throws FileNotFoundException {
		this.url = url;
		file = new PrintStream(new BufferedOutputStream(new FileOutputStream(this.url.getTarget())), true);
	}
	
	/**
	 * Adds the given result String to the internal output tracker, this means
	 * it will be printed when compilation is completed.
	 * @param 	result
	 * 			The String to be added.
	 */
	@Raw
	public void addOutput(String result) {
		outputTracker += result + "\n ";
	}

	/**
	 * Writes the internal output tracker to the current file.
	 * @throws 	FileNotFoundException
	 * 			The current file is null.
	 */
	@Raw
	private void writeOutput() throws NullPointerException {
		if (file == null)
			throw new NullPointerException();
		System.setOut(file);
		System.out.println(outputTracker);
	}

	
	/**
	 * Compiles this Program Object to a DRAMA program in the file found at the given URL.
	 * @param 	url
	 * 			The URL to which the DRAMA program will be written. 
	 * @throws 	FileNotFoundException
	 * 			The given URL does not point to a valid output path.
	 */
	public void compile(URL url) throws FileNotFoundException {
		outputTracker = "";
		setURL(url);
		try {
			getFunction("main").compile();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
//			System.setOut(console);
//			System.out.println("! COMPILATION ERROR !");
//			return;
		}
		addOutput("STP");
		addOutput("HEAP: RESGR 200");
		addOutput("EINDPR");
		writeOutput();
	}
	
}

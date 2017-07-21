package model;

import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
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
		this.functionsMap = new HashMap<String, Function>();
		this.structsMap = new HashMap<String, Struct>();
		this.globalVarsMap = new HashMap<String, VariableExpression>();
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
	private Map<String, Struct> structsMap;
	/**
	 * A List containing all the global variables of this program.
	 */
	private Map<String, VariableExpression> globalVarsMap;

	
	public Function getFunction(String name) throws NoSuchElementException {
		if (!functionsMap.containsKey(name))
			throw new NoSuchElementException();
		return functionsMap.get(name);
	}
	
	public Struct getStruct(String name) throws NoSuchElementException {
		if (!structsMap.containsKey(name))
			throw new NoSuchElementException();
		return structsMap.get(name);
	}
	
	public VariableExpression getGlobalVariable(String name) throws NoSuchElementException {
		if (!globalVarsMap.containsKey(name))
			throw new NoSuchElementException();
		return globalVarsMap.get(name);
	}
	
	
	public boolean canHaveAsFunction(Function function) {
		for (String ownedName: functionsMap.keySet())
			if (ownedName.equals(function.getName()))
				return false;
		return true;
	}
	
	public boolean canHaveAsStruct(Struct struct) {
		for (String ownedStruct: structsMap.keySet())
			if (ownedStruct.equals(struct.getName()))
				return false;
		return true;
	}
	
	public boolean canHaveAsGlobalVar(VariableExpression variable) {
		for (String ownedVariable: globalVarsMap.keySet())
			if (ownedVariable.equals(variable.getName()))
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

	private void setStructs(List<Struct> structs) throws IllegalArgumentException {
		for (Struct struct: structs) {
			if (!canHaveAsStruct(struct))
				throw new IllegalArgumentException();
			this.structsMap.put(struct.getName(), struct);
		}
	}
	
	private void setGlobalVars(List<VariableExpression> globalVars) throws IllegalArgumentException {
		for (VariableExpression var: globalVars) {
			if (!canHaveAsGlobalVar(var))
				throw new IllegalArgumentException();
			this.globalVarsMap.put(var.getName(), var);
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

	
	
	private Map<String, Integer> labelsMap;
	
	
	public String requestLabel(Function requester, LabelType type) {
		int count = labelsMap.get(requester.getName() + type.name());
		String result = requester.getName() + type + Integer.toString(count);
		labelsMap.put(requester.getName() + type, count + 1);
		return result;
	}
	
	
	private void initializeLabels() {
		labelsMap = new HashMap<String, Integer>();
		for (String function: functionsMap.keySet())
			labelsMap.put(function + LabelType.FUNCTION.name(), 0);
//		for (String globalVar: globalVarsMap.keySet())
//			labelsMap.put(globalVar + LabelType.VARIABLE, 0);
	}
	
	

	public String getDRAMAGlobalVars() {
		String buffer = "";
		Helper helper = new Helper();
		for (String globalVar: globalVarsMap.keySet())
			buffer += globalVar + "VARIABLE" + ": RESGR " + 
					Integer.toString(helper.getDataTypeSize(getGlobalVariable(globalVar).getDataType())) +"\n ";
		return buffer.substring(0, buffer.length() - 3);
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
		initializeLabels();
		try {
			getFunction("main").compile();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
//			System.setOut(console);
//			System.out.println("! COMPILATION ERROR !");
//			return;
		}
		addOutput("STP");
		addOutput(getDRAMAGlobalVars());
		addOutput("HEAP: RESGR 200");
		addOutput("EINDPR");
		writeOutput();
	}
		
}

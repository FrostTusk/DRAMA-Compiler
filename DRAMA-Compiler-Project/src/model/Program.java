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
import util.Toolbox;
import util.URL;

/**
 * A class that represent a DRAMA program.
 * @invar	A Program must be able to have each of it's functions as a function.
 * 			for each function in functions: canHaveAsFunction(function)
 * @invar	A Program must be able to have each of it's structs as a struct. 
 * 			for each struct in structs: canHaveAsStruct(struct)
 * @invar	A Program must be able to have each of it's variables as a global variable.
 * 			for each variable in variables: canHaveAsGlobalVariable(variable) 
 * @author	Mathijs Hubrechtsen
 */
public class Program {

	/**
	 * Create a new Program object with the given functions, structs, variables.
	 * @param	functions 
	 * 			the given function
	 * @param 	structs 
	 * 			the given structs
	 * @param 	variables 
	 * 			the given variables
	 * @see	implementation
	 * @throws	IllegalArgumentException
	 * 			The Program cannot have each of the given functions/structs/variables
	 * 			as function/struct/variable.
	 * 			| !for each function/struct/variable in functions/structs/variables: 
	 * 			| this.canHaveAsFunction/Struct/Variable(function/struct/variable)
	 */
	public Program(List<Function> functions, List<Struct> structs, List<VariableExpression> variables) 
		throws IllegalArgumentException {
		this.functionsMap = new HashMap<String, Function>();
		this.structsMap = new HashMap<String, Struct>();
		this.globalVarsMap = new HashMap<String, VariableExpression>();
		setFunctions(functions);
		setStructs(structs);
		setGlobalVars(variables);
	}
	
	
	/**
	 * Map containing the Functions of this Program mapped to their name.
	 */
	private Map<String, Function> functionsMap;
	/**
	 * Map containing the Structs of this Program mapped to their name.
	 */
	private Map<String, Struct> structsMap;
	/**
	 * Map containing the VariableExpressions (global variables) of this Program mapped to their name.
	 */
	private Map<String, VariableExpression> globalVarsMap;

	
	/**
	 * Returns the function with the given name.
	 * @param 	name
	 * 			The name of the function to be returned. 
	 * @return	Returns the function with the given name.
	 * @throws 	NoSuchElementException
	 * 			There is no function with the given name.
	 */
	@Raw
	public Function getFunction(String name) throws NoSuchElementException {
		if (!functionsMap.containsKey(name))
			throw new NoSuchElementException();
		return functionsMap.get(name);
	}
	
	/**
	 * Returns the struct with the given name.
	 * @param 	name
	 * 			The name of the struct to be returned. 
	 * @return	Returns the struct with the given name.
	 * @throws 	NoSuchElementException
	 * 			There is no struct with the given name.
	 */
	@Raw
	public Struct getStruct(String name) throws NoSuchElementException {
		if (!structsMap.containsKey(name))
			throw new NoSuchElementException();
		return structsMap.get(name);
	}
	
	/**
	 * Returns the global variable with the given name.
	 * @param 	name
	 * 			The name of the variable to be returned. 
	 * @return	Returns the variable with the given name.
	 * @throws 	NoSuchElementException
	 * 			There is no variable with the given name.
	 */
	@Raw
	public VariableExpression getGlobalVariable(String name) throws NoSuchElementException {
		if (!globalVarsMap.containsKey(name))
			throw new NoSuchElementException();
		return globalVarsMap.get(name);
	}
	
	
	/**
	 * Checks whether this Program can have the give function as function.
	 * @param	function
	 * 			The function to be checked.
	 * @return	Returns true if it can have the function as function, 
	 * 			false if not.
	 */
	@Raw
	public boolean canHaveAsFunction(Function function) {
		for (String ownedName: functionsMap.keySet())
			if (ownedName.equals(function.getName()))
				return false;
		return true;
	}
	
	/**
	 * Checks whether this Program can have the give struct as struct.
	 * @param	struct
	 * 			The struct to be checked.
	 * @return	Returns true if it can have the struct as struct, 
	 * 			false if not.
	 */
	@Raw
	public boolean canHaveAsStruct(Struct struct) {
		for (String ownedStruct: structsMap.keySet())
			if (ownedStruct.equals(struct.getName()))
				return false;
		return true;
	}
	
	/**
	 * Checks whether this Program can have the give variable as global variable.
	 * @param	variable
	 * 			The variable to be checked.
	 * @return	Returns true if it can have the variable as global variable, 
	 * 			false if not.
	 */
	@Raw
	public boolean canHaveAsGlobalVar(VariableExpression variable) {
		for (String ownedVariable: globalVarsMap.keySet())
			if (ownedVariable.equals(variable.getName()))
				return false;
		return true;
	}
	
	
	/**
	 * Set the functions of this Program to the given functions if able.
	 * @param 	functions
	 * 			The new functions of this Program.
	 * @post	The functions of this Program have been set to the given functions.
	 * @throws	IllegalArgumentException
	 * 			The Function cannot have each of the given functions as function or
	 * 			there is no "main" function.
	 */
	@Raw
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

	/**
	 * Set the structs of this Program to the given structs if able.
	 * @param 	structs
	 * 			The new structs of this Program.
	 * @post	The structs of this Program have been set to the given structs.
	 * @throws	IllegalArgumentException
	 * 			The Program cannot have each of the given structs as struct.
	 * 			| !for each struct in structs: this.canHaveAsStruct(struct)
	 */
	@Raw
	private void setStructs(List<Struct> structs) throws IllegalArgumentException {
		for (Struct struct: structs) {
			if (!canHaveAsStruct(struct))
				throw new IllegalArgumentException();
			this.structsMap.put(struct.getName(), struct);
		}
	}
	
	/**
	 * Set the global variables of this Program to the given global variables if able.
	 * @param 	globalVars
	 * 			The new global variables of this Program.
	 * @post	The global variables of this Program have been set to the given global variables.
	 * @throws	IllegalArgumentException
	 * 			The Program cannot have each of the given global variables as global variable.
	 * 			| !for each variable in globalVars: this.canHaveAsGlobalVariable(variable)
	 */
	@Raw
	private void setGlobalVars(List<VariableExpression> globalVars) throws IllegalArgumentException {
		for (VariableExpression var: globalVars) {
			if (!canHaveAsGlobalVar(var))
				throw new IllegalArgumentException();
			this.globalVarsMap.put(var.getName(), var);
		}
	}

	
	/**
	 * Map containing the labels (without suffix) and the amount of times each label is used by this Program.
	 * The amount each label is mapped to the name of it's label.
	 */
	private Map<String, Integer> labelsMap;
	
	
	/**
	 * Returns an unused label of the given label type if able.
	 * @param	requester
	 * 			The Function that requested a label.
	 * @param 	type
	 * 			The type of the label.
	 * @return	Returns a label of the given label type.
	 * @throws	NoSuchElementException
	 * 			No label of the given type could be returned.
	 * @throws	NullPointerException
	 * 			The requesting Function was null.
	 * 			| requester == null
	 */
	@Raw
	public String requestLabel(Function requester, LabelType type) throws NoSuchElementException, NullPointerException {
		if ((labelsMap == null) || (!labelsMap.containsKey(requester.getName() + type.name())))
				throw new NoSuchElementException();
		
		int count = labelsMap.get(requester.getName() + type.name());
		String result = requester.getName() + type + Integer.toString(count);
		labelsMap.put(requester.getName() + type, count + 1);
		return result;
	}
	
	
	/**
	 * Initializes the labels map of this Program. The labels map
	 * will contain
	 * @see implemenation
	 */
	@Raw
	private void initializeLabels() {
		labelsMap = new HashMap<String, Integer>();
		for (String function: functionsMap.keySet())
			labelsMap.put(function + LabelType.FUNCTION.name(), 0);
	}

	
	
	/**
	 * Variable registering the console PrintStream.
	 */
	@SuppressWarnings("unused") // TODO: Will this ever be used?
	private PrintStream consoleStream = new PrintStream(new FileOutputStream(FileDescriptor.out));
	/**
	 * Variable registering the file PrintStream of this Program.
	 */
	private PrintStream fileStream;
	/**
	 * Variable that tracks the compilation output.
	 */
	private StringBuilder outputTracker;
	/**
	 * Variable registering the target (where the output needs to be written) URL of this Program.
	 */
	private URL url;
	
	
	/**
	 * Get the target URL of this Program.
	 */
	@Basic @Raw
	public URL getUrl() {
		return this.url;
	}


	/**
	 * Adds the given instruction String to the compilation output tracker.
	 * @param 	instruction
	 * 			The String to be added.
	 * @post	The given instruction String has been added to the compilation
	 * 			output tracker.
	 */
	@Raw
	public void addOutput(String instruction) {
		outputTracker.append(instruction + System.lineSeparator());
	}

	/**
	 * Adds the given instruction String to the compilation output tracker.
	 * @param 	instruction
	 * 			The String to be added.
	 * @post	The given instruction String has been added to the compilation
	 * 			output tracker.
	 */
	public void addOutput(Object instruction){
		outputTracker.append(instruction + System.lineSeparator());
	}
	
	/**
	 * Sets the target URL of this Program to the given URL, this will also update
	 * the internal file PrintStream (fileStream).
	 * @param 	url
	 * 			The new target URL.
	 * @see	implementation
	 * @throws 	FileNotFoundException
	 * 			The given URL does not point to a valid output path. 
	 */
	@Raw
	private void setURL(URL url) throws FileNotFoundException {
		this.url = url;
		fileStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(this.url.getTarget())), true);
	}
	
	
	/**
	 * Writes the compilation output tracker to the current URL. 
	 * @see implementation
	 * @throws 	NullPointerException
	 * 			The URL has never been set to a valid URL.
	 */
	@Raw
	private void writeOutput() throws NullPointerException {
		if (fileStream == null)
			throw new NullPointerException();
		System.setOut(fileStream);
		System.out.println(outputTracker);
	}

	
	/**
	 * Get the global variables of this Program in String DRAMA format.
	 * @see	implementation
	 * @return	Returns the global variables of this Program in String DRAMA format.
	 */ // TODO: Find better method name. TODO: Fix variable sizes.
	private String getDRAMAGlobalVars() {
		String buffer = "";
		if (globalVarsMap.isEmpty())
			return buffer;
		Toolbox helper = new Toolbox();
		for (String globalVar: globalVarsMap.keySet())
			buffer += globalVar + "VARIABLE" + ": RESGR " + 
					Integer.toString(helper.getDataTypeSize(getGlobalVariable(globalVar).getDataType())) +"\n ";
		return buffer.substring(0, buffer.length() - 3);
	}
	
	
	/**
	 * Compiles this Program to a DRAMA program in the file found at the given URL.
	 * @param 	url
	 * 			The URL to which the DRAMA program will be written. 
	 * @see	implementation.
	 * @throws	IllegalStateException
	 * 			This Program object cannot be compiled to a valid DRAMA program.
	 * @throws 	FileNotFoundException
	 * 			The given URL does not point to a valid output path.
	 */
	public void compile(URL url) throws IllegalStateException, FileNotFoundException {
		setURL(url);
		outputTracker = new StringBuilder();
		initializeLabels();
		try {
			getFunction("main").compile();
			for (String function: functionsMap.keySet())
				if (!function.equals("main"))
					functionsMap.get(function).compile();
		} catch (IllegalArgumentException e) {
			throw new IllegalStateException(e);
		} catch (NoSuchElementException e) {
			throw new IllegalStateException(e);
		} catch (NullPointerException e) {
			throw new IllegalStateException(e);
		} 
		addOutput(Instructions.STP);
		addOutput(getDRAMAGlobalVars());
		addOutput("HEAP: RESGR 200");
		addOutput(Instructions.EINDPR);
		writeOutput();
	}
		
}

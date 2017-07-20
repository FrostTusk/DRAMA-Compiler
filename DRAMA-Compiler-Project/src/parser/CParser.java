package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import language.Function;
import language.Struct;
import language.expressions.ParameterExpression;
import language.expressions.VariableExpression;
import language.statements.SequenceStatement;
import language.statements.Statement;
import model.Program;
import model.URL;

/**
 * Class representing a Parser object that parses a C file to
 * a internal representation of a DRAMA program as seen in the SOCS course
 * at KULeuven. The C file needs to use the same C syntax as described in
 * that course.
 * 
 * @author Mathijs Hubrechtsen
 *
 */
public class CParser {	
	
	public CParser() {
		reset();
	}
	
	
	/**
	 * The internal representation of the file.
	 */
	private String programString;
	/**
	 * The point in programString that is currently being read.
	 */
	private int pointer;
	/**
	 * Variable that tracks whether or not the pointer is located 
	 * in a function at this moment. 
	 */
	private boolean inFunction;
	/**
	 * Variable that tracks whether or not the pointer is located 
	 * in a struct at this moment. 
	 */
	private boolean inStruct;
	/**
	 * List holding all the current struct definitions.
	 */
	private List<Struct> structs;
	/**
	 * List holding all the current function definitions.
	 */
	private List<Function> functions;
	/**
	 * List holding all the current variable definitions.
	 */
	private List<VariableExpression> variables;
	
	
	/**
	 * Resets all the variables to their default values.
	 */
	private void reset() {
		programString = "";
		pointer = 0;
		inFunction = false;
		inStruct = false;
		structs = new ArrayList<Struct>();
		functions = new ArrayList<Function>();
		variables = new ArrayList<VariableExpression>();
	}
	
	
	
			/*	
			 * |----------------|
			 * | Main Parsers:	|
			 * |----------------|
			 */
	
	
	
	/**
	 * Parse the file located at the given URL to a DRAMA Program.
	 * The file needs to used the C syntax as seen in the SOCS course
	 * at KULeuven.
	 * 
	 * @param 	url
	 * 			The URL of the file to be used.
	 * 
	 * @return	a Program object representing a DRAMA program.
	 * 
	 * @throws 	IOException
	 */
	public Program parse(URL url) throws IOException {
		reset();
		return parseToProgram(parseToString(url));
	}
	
	
	/**
	 * Parse the file located at the given URL to a DRAMA Program.
	 * The file needs to used the C syntax as seen in the SOCS course
	 * at KULeuven.
	 * 
	 * @param 	url
	 * 			The URL of the file to be used.
	 * 
	 * @return	a String representing a DRAMA program.
	 * 
	 * @throws 	IOException
	 */
	public String parseToString(URL url) throws IOException {
		reset();
		BufferedReader br = new BufferedReader(new FileReader(url.getTarget()));;
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
//		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    return sb.toString();
		} finally {
			    br.close();
		}
	}
	
	
	/**
	 * Parse a given string to a DRAMA Program.
	 * The file needs to used the C syntax as seen in the SOCS course
	 * at KULeuven.
	 * 
	 * @param 	url
	 * 			The URL of the file to be used.
	 * 
	 * @return	a String representing a DRAMA program.
	 * 
	 * @throws 	IOException
	 */
	public Program parseToProgram(String programString) {
		reset();
		this.programString = programString;
		String buffer = "";
		try {
			for (int i = 0; i < programString.length(); i++) {
				pointer = i;
				char ch = programString.charAt(i);
				if ( (Character.isWhitespace(ch)) && (buffer.equals("")) )
					continue;
				buffer = handleBufferInGlobal(buffer + ch, i);
				
			}
		} catch (IllegalArgumentException e) {
			
		} catch (IllegalStateException e) {
			
		} catch (NoSuchElementException e) {
			
		}
		return new Program(functions, variables, structs);
	}
	
	
	
			/*	
			 * |--------------------|
			 * | Helper Parsers:	|
			 * |--------------------|
			 */
	
	
	
	/**
	 * Variable holding the current found statements.
	 */
	private List<Statement> statements;
	
	
	/**
	 * Parse the current programString starting from a given index to a function.
	 * Note: this method is only called in handleBuffer, this means we're sure
	 * that we have just read "def".
	 * 
	 * @param 	start
	 * 			The start of where to start parsing to a function.
	 * 			This is the character right after "def".
	 * 			In "defX" the X represents the character of which start is 
	 * 			the index. start will be used as index 0.
	 * 
	 * @post	Pointer points to the last char of the function definition: "}".
	 * 			| Character.toString(programString.charAt(pointer)).equals("}")
	 * 
	 * @return	A parsed function definition. 
	 * 
	 * @throws	IllegalStateException
	 * 			The Parser is in the wrong state to call this method.
	 * 			| (!inFunction) || inStruct
	 * @throws	NoSuchElementException
	 * 			There is no function to be parsed in the current programString.
	 */
	private Function parseToFunction(int start) throws IllegalStateException, NoSuchElementException {
		if (!inFunction || inStruct)
			throw new IllegalStateException();

		String name = mineName(start, "{");
		mineNonWhiteSpace(pointer);
		List<ParameterExpression> parameters = mineParameters(start);
		
		int temp = pointer, end = -1;
		for (pointer += 1; pointer < programString.length(); pointer++)
			if (Character.toString(programString.charAt(pointer)).equals("}"))
				end = pointer;
		if (end == -1)
			throw new NoSuchElementException();
		
		return new Function(name, parameters, parseToStatement(temp, end));
	}
	
	
	/**
	 * Parse the current programString starting from a given index to a struct.
	 * Note: this method is only called in handleBuffer, this means we're sure
	 * that we have just read "struct".
	 * 
	 * @param 	start
	 * 			The start of where to start parsing to a struct.
	 * 			This is the character right after "struct".
	 * 			In "structX" the X represents the character of which start is 
	 * 			the index. start will be used as index 0.
	 * 
	 * @post	Pointer points to the last char of the struct definition: "}".
	 * 			| Character.toString(programString.charAt(pointer)).equals("}")
	 * 
	 * @return	A parsed struct definition. 
	 */
	private Struct parseToStruct(int start) {
		return null;
	}
	
	
	/**
	 * Parse the current programString starting from a given index to another index
	 * to a statement.
	 * 
	 * @param 	start
	 * 			The start of where to start parsing to a statement.
	 * 			start will be used as index 0.
	 * @param	end 
	 * 			The end of where to parse to.
	 *
	 * @post	Pointer points to the last char of the statement: end.
	 * 			| pointer == end
	 * 
	 * @return	A parsed statement.
	 * 
	 * @throws 	IllegalStateException
	 * @throws	NoSuchElementException
	 */
	private Statement parseToStatement(int start, int end) throws IllegalStateException, NoSuchElementException {
		statements = new ArrayList<Statement>();
		int count = 0;
		for (int i = start; i < end; i++) {
			char ch = programString.charAt(pointer);
			if (Character.toString(ch).equals(";"))
				count += 1;
		}
		if (count == 0)
			throw new NoSuchElementException();
		
		
		String buffer = "";
		for (pointer = start; pointer < end; pointer++) {
			char ch = programString.charAt(pointer);
			if ( (Character.isWhitespace(ch)) && (buffer.equals("")) )
				continue;
			buffer = handleBufferInLocal(buffer + ch, pointer);
		}
		
		return new SequenceStatement(statements);
	}
	
	private Statement parseToWhileStatement(int start) {
		return null;
	}
	
	
	
			/*	
			 * |------------|
			 * | Handlers:	|
			 * |------------|
			 */
	
	
	
	private String handleBufferInGlobal(String buffer, int i) throws IllegalStateException, NoSuchElementException {
		if (inFunction || inStruct)
			throw new IllegalStateException();
		switch (buffer) {
			case "def": 
				mineNonWhiteSpace(i);
				functions.add(parseToFunction(pointer));
				break;
			case "struct": 
				mineNonWhiteSpace(i);
				structs.add(parseToStruct(pointer));
				break;
			default: 
				if (!Character.toString(programString.charAt(i)).equals("="))
					return buffer;
		}
		pointer += 1;
		return "";
	}
	

	private String handleBufferInLocal(String buffer, int i) throws IllegalStateException, NoSuchElementException {
		if (!inFunction && !inStruct)
			throw new IllegalStateException();
		switch (buffer) {
			case "while": 
				mineNonWhiteSpace(i);
				parseToWhileStatement(i);
				break;
			case "for": 
				break;
			case "if": 
				break;
			case "print":
				break;
			default: 
				return buffer;
		}
		pointer += 1;
		return "";
	}
	
	
	
			/*	
			 * |------------|
			 * | Miners:	|
			 * |------------|
			 */
	
	
	
	/**
	 * Mine the current programString to get the first non whitespace character 
	 * starting from a given index.
	 * 
	 * @param 	start
	 * 			The start of where to start mining for the non whitespace
	 * 			character. start will be used as index 0.
	 * 
	 * @post	Pointer points to the returned character.
	 * 			| Character.toString(programString.charAt(pointer)).equals(result)
	 * 
	 * @return	The first non white space character mined starting from start.
	 * 
	 * @throws	NoSuchElementException
	 * 			There is no first non whitespace character.
	 */
	private char mineNonWhiteSpace(int start) throws NoSuchElementException {
		for (pointer = start; pointer < programString.length(); pointer++)
			if (!Character.isWhitespace(programString.charAt(pointer)))
				return programString.charAt(pointer);
		throw new NoSuchElementException();
	}
	
	
	/**
	 * Mine the current programString to get the first name
	 * starting from a given index.
	 * 
	 * @param 	start
	 * 			The start of where to start mining for a name.
	 * 			start will be used as index 0.
	 * @param	end
	 * 			The character that will be found at the end of the name
	 * 			represented as a string. This needs to be of length 1.
	 * 
	 * @post	Pointer points to end of the returned String.
	 * 			| Character.toString(programString.charAt(pointer)).
	 * 			| equals(result.charAt(result.length -1))
	 * 
	 * @return	A String representing the first name.
	 * 
	 * @throws	IllegalArgumentException
	 * 			The given end string is not 1 character long.
	 * 			| end.length() != 2
	 * @throws	NoSuchElementException This means there
	 * 			is no end character: end.
	 * 			There is no first name.
	 * @throws	NullPointerException
	 * 			The given end string is null.
	 * 			| end == null
	 */
	private String mineName(int start, String end) 
			throws IllegalArgumentException, NoSuchElementException, NullPointerException {
		if (end.length() != 1)
			throw new IllegalArgumentException();
		String buffer = "";
		for (pointer = start; pointer < programString.length(); pointer++) {
			char ch = programString.charAt(pointer);
			if (Character.toString(ch).equals(end))
				return buffer;
			buffer += ch;
		}
		throw new NoSuchElementException();
	}
	
	
	/**
	 * Mine the current programString to get the first parameters
	 * starting from a given index.
	 * 
	 * @param 	start
	 * 			The start of where to start mining for parameters.
	 * 			The character at this position should be "("
	 * 			start will be used as index 0.
	 * 
	 * @post	Pointer points to end of the parameters: ")".
	 * 			| Character.toString(programString.charAt(pointer)).equals(")")
	 * 
	 * @return	A List of Parameters representing the parameters.
	 * 
	 * @throws	NoSuchElementException
	 * 			There are no valid parameters. This means there
	 * 			is no end character: ")".
	 */
	private List<ParameterExpression> mineParameters(int start) {
		return null;
	}
	
}

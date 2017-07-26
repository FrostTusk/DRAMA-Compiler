package util;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.DataType;
import language.statements.Statement;

/**
 * A Toolbox Class containing various methods to simplify the code. 
 * @author	Mathijs Hubrechtsen
 */
public class Toolbox {
	
	/**
	 * Variable containing the statement that uses this Toolbox.
	 */
	private Statement statement;
	
	
	/**
	 * Get the statement of this Toolbox.
	 */
	@Basic @Raw
	public Statement getStatement() {
		return statement;
	}
	
	
	/**
	 * Sets the Statement of this Toolbox to the given Statement.
	 * @see implementation
	 * @param 	statement
	 * 			The new statement of this Toolbox.
	 */
	@Raw
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	/**
	 * Adds the given result String to the compilation output tracker of the Program
	 * of the Statement of this toolbox. This is a "shortcut" method to reduce visual code clutter.
	 * @param 	result
	 * 			The String to be added.
	 * @post	The given result String has been added to the compilation
	 * 			output tracker.
	 * @throws	NullPointerException
	 * 			The statement of this Toolbox, or the function of the statement, or 
	 * 			the program of the function was null.
	 * 			| (getStatement() == null) || (getStatement().getFunction() == null) ||
	 * 			| (getStatement().getFunction().getProgram() == null)
	 */
	@Raw
	public void addOutput(String result) throws NullPointerException {
		getStatement().getFunction().getProgram().addOutput(result);
	}
	
	/**
	 * Adds the given result String to the compilation output tracker of the Program
	 * of the given Statement. This is a "shortcut" method to reduce visual code clutter.
	 * @param 	result
	 * 			The String to be added.
	 * @post	The given result String has been added to the compilation
	 * 			output tracker.
	 * @throws	NullPointerException
	 * 			The statement of this Toolbox, or the function of the statement, or 
	 * 			the program of the function was null.
	 * 			| (statement == null) || (statement.getFunction() == null) ||
	 * 			| (statement.getFunction().getProgram() == null)
	 */
	@Raw
	public void addOutput(Statement statement, String result) {
		statement.getFunction().getProgram().addOutput(result);
	}
	
	
	/**
	 * Get the interpretation (as defined in DRAMA) of the given Data Type.
	 * @param 	type
	 * 			The data type of which the interpretation needs to be returned.
	 * @return	Returns the interpretation of the given Data Type.
	 */
	@Raw
	public String getInterpretation(DataType type) {
		switch(type) {
			case INT:
				return ".w ";
			case POINTER:
				return ".i ";
			default:
				return " ";
		}
	}
	
	/**
	 * Get the size of the given Data Type.
	 * @param 	type
	 * 			The data type of which the size needs to be returned.
	 * @return	Returns the size of the given Data Type.
	 */ // TODO: Structs/Arrays
	@Raw
	public int getDataTypeSize(DataType type) {
		switch(type) {
			default:
				return 1;
		}
	}
	
	
	
	/**
	 * Prints out the differences between 2 given Strings to the
	 * current output stream.
	 * @param	string1
	 * 			The first String to be used.
	 * @param 	string2
	 * 			The second String to be used.
	 */
	@Raw
	public void printStringDifferences(String string1, String string2) {
		if (string1.equals(string2))
			System.out.println("string1 EQUALS string2");
		else
			System.out.println("string1 DOES NOT EQUAL string 2");
		
		for (int i = 0; i < string1.length(); i++)
			if (string1.charAt(i) != string2.charAt(i)) {
				if (Character.toString(string1.charAt(i)).equals("\r") || 
						Character.toString(string1.charAt(i)).equals("\n"))
					System.out.format("lineSeparator != %s" + System.lineSeparator(), Character.toString(string2.charAt(i)));
				else if (Character.toString(string2.charAt(i)).equals("\r") || 
						Character.toString(string2.charAt(i)).equals("\n"))
					System.out.format("%s != lineSeparator" + System.lineSeparator(), Character.toString(string1.charAt(i)));
				else
					System.out.format("%s != %s" + System.lineSeparator()
					, Character.toString(string1.charAt(i)), Character.toString(string2.charAt(i)));
			}
	}
	
	
	
	/**
	 * Removes all of the elements from this Toolbox. 
	 * This Toolbox will be empty after this call returns.
	 * @post	This Toolbox is empty.
	 * 			| getStatement() == null
	 */
	@Raw
	public void clear() {
		statement = null;
	}
	
}

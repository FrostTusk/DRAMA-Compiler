package model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.DataType;
import language.statements.Statement;
import language.expressions.Interpretation;

import static language.expressions.Interpretation.i;
import static language.expressions.Interpretation.none;
import static language.expressions.Interpretation.w;

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
	 * 			The Statement of this Toolbox, or the Function of the Statement, or 
	 * 			the Program of the Function was null.
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
	 * 			The Statement of this Toolbox, or the Function of the Statement, or 
	 * 			the Program of the Function was null.
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
	public Interpretation getInterpretation(DataType type) {
		switch(type) {
			case INT:
				return w;
			case POINTER:
				return i;
			default:
				return none;
		}
	}
	
	/**
	 * Get the size of the given Data Type.
	 * @param 	type
	 * 			The data type of which the size needs to be returned.
	 * @return	Returns the size of the given Data Type.
	 */ // TODO: Structs/Arrays.
	@Raw
	public int getDataTypeSize(DataType type) {
		switch(type) {
			default:
				return 1;
		}
	}
	
}

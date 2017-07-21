package language.statements;

import be.kuleuven.cs.som.annotate.Basic;
import language.Function;

/**
 * An interface representing a Compilable DRAMA Statement.
 * @author Mathijs Hubrechtsen
 */
public interface Statement {
	
	/**
	 * Get the Function of this Statement.
	 */
	@Basic
	public Function getFunction();
	/**
	 * Set the Function of this Statement to the given Function.
	 * @param 	function
	 * 			The new Function of this Statement.
	 */
	public void setFunction(Function function);
	/**
	 * Compiles this statement to it's corresponding DRAMA code.
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public void compile() throws IllegalArgumentException, NullPointerException;
	
}

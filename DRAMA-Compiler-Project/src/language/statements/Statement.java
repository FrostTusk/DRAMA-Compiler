package language.statements;

import java.util.NoSuchElementException;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.Function;

/**
 * An interface representing a Compilable DRAMA Statement.
 * @author Mathijs Hubrechtsen
 */
public interface Statement {
	
	/**
	 * Get the function of this Statement.
	 */
	@Basic @Raw
	public Function getFunction();
	/**
	 * Set the function of this Statement to the given function.
	 * @param 	function
	 * 			The new function of this Statement.
	 */
	@Raw
	public void setFunction(Function function);
	/**
	 * Compiles this statement to it's corresponding DRAMA code.
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public void compile() throws IllegalArgumentException, NoSuchElementException, NullPointerException;
	
}

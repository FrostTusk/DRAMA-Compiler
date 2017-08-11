package language.statements;

import java.util.NoSuchElementException;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.Function;
import model.Compilable;

/**
 * An interface representing a Compilable C Statement.
 * @author Mathijs Hubrechtsen
 */
public interface Statement extends Compilable {
	
	/**
	 * Get the function of this Statement.
	 */
	@Basic @Raw
	public Function getFunction();
	/**
	 * Set the function of this Statement to the given function.
	 * @param 	function
	 * 			The new function of this Statement.
	 * @post	The Function of this statement is equal to the 
	 * 			given statement.
	 * 			| new.getFunction() == function
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

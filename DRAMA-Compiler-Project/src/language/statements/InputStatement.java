package language.statements;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.Function;
import language.expressions.Storeable;

/**
 * A Class that represents a DRAMA Input Statement.
 * @author Mathijs Hubrechtsen
 */
public class InputStatement implements Statement {

	/**
	 * Create a new Assignment Statement object with a given storeable.
	 * @param 	storeable
	 * 			The storeable in which the input will be stored.
	 * @see	implementation
	 */
	public InputStatement(Storeable storeable) {
		this.storeable = storeable;
	}
	
	
	/**
	 * Variable registering the storeable of this Input Statement.
	 */
	private final Storeable storeable;
	
	
	/**
	 * Get the storeable of this Input Statement.
	 */
	@Basic @Raw
	public Storeable getStoreable() {
		return storeable;
	}
	
	
	
	/**
	 * Variable registering to which Function this Statement belongs.
	 */
	private Function function;
	
	
	@Basic @Override @Raw
	public Function getFunction() {
		return function;
	}


	@Override @Raw
	public void setFunction(Function function) {
		this.function = function;
	}
	
	
	
	/**
	 * @throws	NullPointerException
	 * 			The function of this Statement, or the program of the function was null.
	 * 			| (getFunction() == null) || (getFunction().getProgram() == null)
	 */
	@Override @Raw
	public void compile() throws NullPointerException {
		getFunction().getProgram().addOutput("BST R0");
		getFunction().getProgram().addOutput("LEZ");
		getFunction().getProgram().addOutput(getStoreable().store("R0"));
		getFunction().getProgram().addOutput("HST R0");
	}

}

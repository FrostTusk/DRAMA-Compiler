package language.statements;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.Function;
import language.expressions.FunctionCallExpression;

/**
 * A Class that represents a Compilable C Function Call Statement.
 * @author Mathijs Hubrechtsen
 */
public class FunctionCallStatement implements Statement {

	/**
	 * Create a new FunctionCall Statement object with a given function call expression.
	 * @param 	functionCall
	 * 			The function call expression used in this functionCall Statement.
	 * @see	implementation
	 */
	public FunctionCallStatement(FunctionCallExpression functionCall) {
		this.functionCall = functionCall;
	}
	
	
	/**
	 * Variable containing the function call expression of this FunctionCall Statement.
	 */
	private final FunctionCallExpression functionCall;
	
	
	/**
	 * Get the Function Call Expression of this FunctionCall Statement.
	 */
	@Basic @Raw
	public FunctionCallExpression getFunctionCall() {
		return functionCall;
	}
	
	
	
	/**
	 * Variable containing to which Function this Statement belongs.
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
	 * 			The function of this statement, the program of the function, or 
	 * 			the function call expression of this statement was null.
	 * 			| (getFunction() == null) || (getFunction().getProgram() == null)
	 * 			| || (getFunctionCall() == null)
	 */ // TODO: More exceptions?
	@Override @Raw
	public void compile() throws NullPointerException {
		getFunction().getProgram().addOutput(getFunctionCall().evaluate());
	}

}

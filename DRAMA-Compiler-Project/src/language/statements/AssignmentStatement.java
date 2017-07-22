package language.statements;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.Function;
import language.expressions.Expression;
import language.expressions.Storeable;

/**
 * A Class that represents a DRAMA Assignment Statement.
 * @author Mathijs Hubrechtsen
 */
public class AssignmentStatement implements Statement {

	/**
	 * Create a new Assignment Statement object with a given storeable and expression.
	 * @param 	expression
	 * 			The expression that will be assigned to the given Storeable.
	 * @param	storeable
	 * 			The storeable to which the expression is assigned.
	 * @see	implementation
	 */
	public AssignmentStatement(Storeable storeable, Expression expression) {
		this.storeable = storeable;
		this.expression = expression;
	}
	
	
	/**
	 * Variable registering the expression of this Assignment Statement.
	 */
	private final Expression expression;
	/**
	 * Variable registering the storeable of this Assignment Statement.
	 */
	private final Storeable storeable;
	
	
	/**
	 * Get the expression of this Assignment Statement.
	 */
	@Basic
	public Expression getExpression() {
		return expression;
	}
	
	/**
	 * Get the Storeable of this Assignment Statement.
	 */
	@Basic
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
	 * @throws	IllegalArgumentException
	 * 		   	The Data Type of the storeable and the expression or incompatible.
	 * 		  	| storeable.getDataType() != expression.getDataType()
	 * @throws	NullPointerException
	 * 			The Function of this Statement, or the Program of the Function was null.
	 * 			| (getFunction() == null) || (getFunction().getProgram() == null)
	 */
	@Override @Raw
	public void compile() throws IllegalArgumentException, NullPointerException {
		getFunction().getProgram().addOutput(storeable.store(getExpression()));	
	}

}

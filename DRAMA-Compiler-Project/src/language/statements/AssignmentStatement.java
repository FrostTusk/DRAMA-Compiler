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
	 * Create a new AssignmentStatement object with a given storeable and expression.
	 * @param	storeable
	 * 			The storeable to which the expression is assigned.
	 * @param 	expression
	 * 			The expression that will be assigned to the given Storeable.
	 * @see	implementation
	 */
	public AssignmentStatement(Storeable storeable, Expression expression) {
		this.storeable = storeable;
		this.expression = expression;
	}
	
	
	/**
	 * 
	 */
	private final Expression expression;
	private final Storeable storeable;
	
	
	/**
	 * Get the expression of this AssignmentStatement.
	 */
	@Basic
	public Expression getExpression() {
		return expression;
	}
	
	/**
	 * Get the Storeable of this AssignmentStatement.
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
	 * 			The Statement of this Toolbox, or the Function of the Statement, or 
	 * 			the Program of the Function was null.
	 * 			| (getStatement() == null) || (getStatement().getFunction() == null) ||
	 * 			| (getStatement().getFunction().getProgram() == null)
	 */
	@Override @Raw
	public void compile() throws IllegalArgumentException, NullPointerException {
		getFunction().getProgram().addOutput(storeable.store(getExpression()));	
	}

}

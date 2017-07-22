package language.statements;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.Function;
import language.expressions.Expression;

/**
 * A Class that represents a DRAMA Print Statement.
 * @author Mathijs Hubrechtsen
 */
public class PrintStatement implements Statement {

	/**
	 * Create a new Print Statement object with a given expression.
	 * @param 	expression
	 * 			The expression to be printed.
	 * @see	implementation
	 */
	public PrintStatement(Expression expression) {
		this.expression = expression;
	}
	
	
	/**
	 * Variable registering the expression of this Print Statement.
	 */
	private Expression expression;
	
	
	/**
	 * Get the expression of this Print Statement.
	 */
	@Basic
	public Expression getExpression() {
		return expression;
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
	 * 			The Function of this Statement, or the Program of the Function was null.
	 * 			| (getFunction() == null) || (getFunction().getProgram() == null)
	 */
	@Override
	public void compile() throws NullPointerException {
		getFunction().getProgram().addOutput("DRU " + getExpression().evaluate());
	}

}

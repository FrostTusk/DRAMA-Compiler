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
	 * Variable containing the expression of this Print Statement.
	 */
	private Expression expression;
	
	
	/**
	 * Get the expression of this Print Statement.
	 */
	@Basic @Raw
	public Expression getExpression() {
		return expression;
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
	 * 			The function of this Statement, or the program of the function was null.
	 * 			| (getFunction() == null) || (getFunction().getProgram() == null)
	 */
	@Override @Raw
	public void compile() throws NullPointerException {
		getFunction().getProgram().addOutput("DRU " + getExpression().evaluate());
	}

}

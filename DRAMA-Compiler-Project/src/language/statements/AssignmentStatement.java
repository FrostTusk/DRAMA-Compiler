package language.statements;

import language.Function;
import language.expressions.Expression;
import language.expressions.Storeable;

public class AssignmentStatement implements Statement {

	public AssignmentStatement(Storeable variable, Expression expression) {
		this.variable = variable;
		this.expression = expression;
	}
	
	
	private final Expression expression;
	private final Storeable variable;
	
	
	public Expression getExpression() {
		return expression;
	}
	
	public Storeable getVariable() {
		return variable;
	}


	
	private Function function;
	
	
	@Override
	public Function getFunction() {
		return function;
	}


	@Override
	public void setFunction(Function function) {
		this.function = function;
	}
	


	/**
	 * @throws IllegalArgumentException
	 * 		   The data-type of the variable and the expression or incompatible.
	 * 		   variable.getDataType() != expression.getDataType()
	 */
	@Override
	public void compile() throws IllegalArgumentException {
		getFunction().getProgram().addOutput(variable.store(getExpression()));	
	}

}

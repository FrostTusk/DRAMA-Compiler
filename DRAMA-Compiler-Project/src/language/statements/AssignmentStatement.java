package language.statements;

import language.Function;
import language.expressions.Expression;
import language.expressions.VariableExpression;

public class AssignmentStatement implements Statement {

	public AssignmentStatement(VariableExpression variable, Expression expression) {
		this.variable = variable;
		this.expression = expression;
	}
	
	
	private final VariableExpression variable;
	private final Expression expression;
	
	
	public VariableExpression getVariable() {
		return variable;
	}
	
	public Expression getExpression() {
		return expression;
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
	 * 
	 * @param expression
	 * @return
	 * @throws IllegalArgumentException
	 * 		   variable.getDataType() != expression.getDataType()
	 */
	@Override
	public void compile() throws IllegalArgumentException {
		getFunction().getProgram().addOutput(variable.store(getExpression()));	
	}

}

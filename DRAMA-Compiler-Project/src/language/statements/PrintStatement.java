package language.statements;

import language.Function;
import language.expressions.Expression;

public class PrintStatement implements Statement {

	public PrintStatement(Expression expression) {
		this.expression = expression;
	}
	
	
	private Expression expression;
	
	
	
	private Function function; 
	
	
	@Override
	public Function getFunction() {
		return function;
	}

	
	@Override
	public void setFunction(Function function) {
		this.function = function;		
	}

	
	
	@Override
	public void compile() {
		getFunction().getProgram().addOutput("DRU " + expression.evaluate());
	}

}

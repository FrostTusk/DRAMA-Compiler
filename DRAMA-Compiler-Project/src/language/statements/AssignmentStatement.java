package language.statements;

import language.Expression;
import language.Function;
import language.Variable;
import model.Program;

public class AssignmentStatement implements Statement {

	public AssignmentStatement(Variable variable, Expression expression) {
		this.variable = variable;
		this.expression = expression;
	}
	
	
	private Variable variable;
	private Expression expression;
	
	
	
	private Function function;
	
	
	@Override
	public Function getFunction() {
		return function;
	}

	@Override
	public Program getProgram() {
		return getFunction().getProgram();
	}


	@Override
	public void setFunction(Function function) {
		this.function = function;
	}


	@Override
	public void compile() {
		
		
	}

}

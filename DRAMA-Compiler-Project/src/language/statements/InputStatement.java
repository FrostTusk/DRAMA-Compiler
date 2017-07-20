package language.statements;

import language.Function;
import language.expressions.VariableExpression;

public class InputStatement implements Statement {

	public InputStatement(VariableExpression variable) {
		this.variable = variable;
	}
	
	
	private final VariableExpression variable;
	
	
	public VariableExpression getVariable() {
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
	
	

	@Override
	public void compile() {
		getFunction().getProgram().addOutput("BST R0");
		getFunction().getProgram().addOutput("LEZ");
		getFunction().getProgram().addOutput(getVariable().store("R0"));
		getFunction().getProgram().addOutput("HST R0");
	}

}

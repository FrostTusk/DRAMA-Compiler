package language.statements;

import language.Function;

public class InputStatement implements Statement {

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
		getFunction().getProgram().addOutput("HST R0");
	}

}

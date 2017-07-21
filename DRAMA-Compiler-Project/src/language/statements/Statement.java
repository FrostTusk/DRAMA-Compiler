package language.statements;

import language.Function;

public interface Statement {
	
	public Function getFunction();
	public void setFunction(Function function);
	public void compile() throws IllegalArgumentException;
	
}

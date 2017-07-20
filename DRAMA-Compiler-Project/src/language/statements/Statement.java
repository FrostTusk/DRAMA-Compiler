package language.statements;

import language.Function;
import model.Program;

public interface Statement {
	
	public Function getFunction();
	public Program getProgram();
	public void setFunction(Function function);
	public void compile();
	
}

package language.statements;

import java.util.List;

import language.Function;

public class SequenceStatement implements Statement {
	
	public SequenceStatement(List<Statement> statements) {
		this.statements = statements;
	}

	
	private final List<Statement> statements;
	
	
	public List<Statement> getStatements() {
		return statements;
	}
	
	
	private Function function;

	@Override
	public Function getFunction() {
		return this.function;
	}


	@Override
	public void setFunction(Function function) {
		this.function = function;
		for (Statement statement: statements)
			statement.setFunction(function);
	}


	@Override
	public void compile() {
		for (Statement statement: statements)
			statement.compile();
	}
	
}

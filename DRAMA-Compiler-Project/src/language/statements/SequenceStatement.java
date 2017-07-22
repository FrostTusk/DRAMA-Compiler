package language.statements;

import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.Function;

/**
 * A Class that represents a DRAMA Sequence Statement.
 * @author Mathijs Hubrechtsen
 */
public class SequenceStatement implements Statement {
	
	/**
	 * Create a new Sequence Statement object with given statements.
	 * @param 	statements
	 * 			The statements of this Sequence Statement.
	 * @see	implementation
	 */
	public SequenceStatement(List<Statement> statements) {
		this.statements = statements;
	}

	
	/**
	 * Variable registering the statements of this Sequence Statement.
	 */
	private final List<Statement> statements;
	
	
	/**
	 * Get the statements of this Sequence Statement.
	 */
	@Basic
	public List<Statement> getStatements() {
		return statements;
	}
	
	
	
	/**
	 * Variable registering to which Function this Statement belongs.
	 */
	private Function function;
	
	
	@Basic @Override @Raw
	public Function getFunction() {
		return function;
	}


	@Override @Raw
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

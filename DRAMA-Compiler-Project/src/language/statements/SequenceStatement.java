package language.statements;

import java.util.List;
import java.util.NoSuchElementException;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.Function;

/**
 * A Class that represents a Compilable C Sequence Statement.
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
	 * Variable containing the statements of this Sequence Statement.
	 */
	private final List<Statement> statements;
	
	
	/**
	 * Get the statements of this Sequence Statement.
	 */
	@Basic @Raw
	public List<Statement> getStatements() {
		return statements;
	}
	
	
	
	/**
	 * Variable containing to which function this Statement belongs.
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


	@Override @Raw
	public void compile() throws IllegalArgumentException, NoSuchElementException, NullPointerException {
		for (Statement statement: statements)
			statement.compile();
	}
	
}

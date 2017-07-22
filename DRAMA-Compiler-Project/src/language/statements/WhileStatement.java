package language.statements;

import java.util.List;
import java.util.NoSuchElementException;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.Compound;
import language.Function;
import model.LabelType;

/**
 * A Class that represents a DRAMA While Statement.
 * @author Mathijs Hubrechtsen
 */
public class WhileStatement implements Statement {

	/**
	 * Create a new While Statement object with a given condition and body.
	 * @param 	condition
	 * 			The condition of this While Statement.
	 * @param	body
	 * 			The body part of this If Statement.
	 * @see	implementation
	 */
	public WhileStatement(Compound condition, SequenceStatement body) {
		this.condition = condition;
		this.body = body;
	}
	
	
	/**
	 * Variable containing the condition of this While Statement.
	 */
	private final Compound condition;
	/**
	 * List containing the labels used by this While Statement
	 */
	private List<String> labels;
	/**
	 * Variable containing the body of this While Statement.
	 */
	private final SequenceStatement body;
	
	
	/**
	 * Get the condition of this While Statement.
	 */
	@Basic @Raw
	public Compound getCondition() {
		return condition;
	}
	
	/**
	 * Get the labels of this While Statement.
	 */
	@Basic @Raw
	public List<String> getLabels() {
		return this.labels;
	}

	/**
	 * Get the body of this While Statement.
	 */
	@Basic @Raw
	public SequenceStatement getBody() {
		return body;
	}
	
	
	/**
	 * Set the labels of this While Statement to the given labels.
	 * @param 	labels
	 * 			The new labels of this While Statement.
	 */
	@Raw
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}


	
	/**
	 * Variable containing to which Function this Statement belongs.
	 */
	private Function function;
	
	
	@Basic @Override @Raw
	public Function getFunction() {
		return function;
	}

	
	@Override
	public void setFunction(Function function) {
		this.function = function;
	}

	
	
	/**
	 * @throws	NoSuchElementException
	 * 			The necessary labels for this While Statement could not be created.
	 * @throws	NullPointerException
	 * 			The function of this Statement, or the program of the function, or the body of this 
	 * 			statement was null.
	 * 			| (getFunction() == null) || (getFunction().getProgram() == null) || (getBody().compile())
	 */ // TODO: Labels of the VSP in compound have to be set!
	@Override @Raw
	public void compile() throws NoSuchElementException, NullPointerException {
		String label = getFunction().requestLabel(LabelType.WHILE);
		getFunction().getProgram().addOutput(getCondition().compile());
		getBody().compile();
		getFunction().getProgram().addOutput("SPR " + label);
		getFunction().getProgram().addOutput(getLabels().get(getLabels().size() - 1) + ": NWL");
	}
	
}

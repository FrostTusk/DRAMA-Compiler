package language.statements;

import java.util.List;
import java.util.NoSuchElementException;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.Compound;
import language.Function;
import model.LabelType;

/**
 * A Class that represents a DRAMA If Statement.
 * @author Mathijs Hubrechtsen
 */
public class IfStatement implements Statement {

	/**
	 * Create a new If Statement object with a given condition and if part.
	 * @param 	condition
	 * 			The condition of this If Statement.
	 * @param	ifPart
	 * 			The if part of this If Statement.
	 * @see	implementation
	 */
	public IfStatement(Compound condition, SequenceStatement ifPart) {
		this.condition = condition;
		this.ifPart = ifPart;
		this.elsePart = null;
	}
	
	/**
	 * Create a new If Statement object with a given condition, if part, and else part.
	 * @param 	condition
	 * 			The condition of this If Statement.
	 * @param	ifPart
	 * 			The if part of this If Statement.
	 * @param	elsePart
	 * 			The else part of this If Statement.
	 * @see	implementation
	 */
	public IfStatement(Compound condition, SequenceStatement ifPart, SequenceStatement elsePart) {
		this.condition = condition;
		this.ifPart = ifPart;
		this.elsePart = elsePart;
	}
	
	
	/**
	 * Variable containing the condition of this If Statement.
	 */
	private final Compound condition;
	/**
	 * List containing the labels used by this If Statement
	 */
	private List<String> labels;
	/**
	 * Variable containing the if part of this If Statement.
	 */
	private final SequenceStatement ifPart;
	/**
	 * Variable containing the else part of this If Statement.
	 */
	private final SequenceStatement elsePart;
	
	
	/**
	 * Get the condition of this If Statement.
	 */
	@Basic @Raw
	public Compound getCondition() {
		return condition;
	}
	
	/**
	 * Get the labels of this If Statement.
	 */
	@Basic @Raw
	public List<String> getLabels() {
		return this.labels;
	}

	/**
	 * Get the if part of this If Statement.
	 */
	@Basic @Raw
	public SequenceStatement getIfPart() {
		return ifPart;
	}
	
	/**
	 * Get the else part of this Assignment Statement.
	 */
	@Basic @Raw
	public SequenceStatement getElsePart() {
		return elsePart;
	}
	
	
	/**
	 * Set the labels of this If Statement to the given labels.
	 * @param 	labels
	 * 			The new labels of this If Statement.
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
	 * 			The necessary labels for this If Statement could not be created.
	 * @throws	NullPointerException
	 * 			The function of this Statement, or the program of the function, or if part of this 
	 * 			statement was null.
	 * 			| (getFunction() == null) || (getFunction().getProgram() == null) || (getIfPart().compile())
	 */ // TODO: Labels of the VSP in compound have to be set!
	@Override @Raw
	public void compile() throws  NoSuchElementException, NullPointerException {
		getFunction().getProgram().addOutput(getCondition().compile());
		getIfPart().compile();
		if (getElsePart() != null) {
			String label = getFunction().requestLabel(LabelType.ELSE);
			getFunction().getProgram().addOutput("SPR " + label);	
			getFunction().getProgram().addOutput(getLabels().get(getLabels().size() - 1) + ": NWL");
			getElsePart().compile();
			getFunction().getProgram().addOutput(label + ": NWL");
		} else
			getFunction().getProgram().addOutput(getLabels().get(getLabels().size() - 1) + ": NWL");
	}

}

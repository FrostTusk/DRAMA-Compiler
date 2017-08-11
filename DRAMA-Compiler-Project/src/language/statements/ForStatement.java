package language.statements;

import java.util.List;
import java.util.NoSuchElementException;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.Compound;
import language.Function;
import language.expressions.Expression;
import language.expressions.Storeable;
import model.LabelType;
import util.Toolbox;

/**
 * A Class that represents a Compilable C For Statement.
 * @author Mathijs Hubrechtsen
 */
public class ForStatement implements Statement {
	
	/**
	 * Create a new For Statement object with a given condition, start, end, 
	 * step, body, and storeable.
	 * @param 	condition
	 * 			The condition of this For Statement.
	 * @param 	start
	 * 			The start of this For Statement.
	 * @param 	end
	 * 			The end of this For Statement.
	 * @param 	step
	 * 			The step of this For Statement.
	 * @param 	body
	 * 			The body of this For Statement.
	 * @param 	storeable
	 * 			The storeable used to hold the counter of
	 * 			this For Statement.
	 * @see	implementation
	 */
	public ForStatement(Compound condition,  Expression start, Expression end, Expression step, 
			SequenceStatement body, Storeable storeable) {
		this.condition = condition;
		this.start = start;
		this.end = end;
		this.step = step;
		this.storeable = storeable;
		this.body = body;
	}
	
	
	/**
	 * Variable containing the condition of this For Statement.
	 */
	private final Compound condition;
	/**
	 * List containing the labels of this For Statement.
	 */
	private List<String> labels;
	/**
	 * Variable containing the start of this For Statement.
	 */
	private final Expression start;
	/**
	 * Variable containing the end of this For Statement.
	 */
	private final Expression end;
	/**
	 * Variable containing the step of this For Statement.
	 */
	private final Expression step;	
	/**
	 * Variable containing the storeable of this For Statement.
	 */
	private final Storeable storeable;
	/**
	 * Variable containing the body of this For Statement.
	 */
	private final SequenceStatement body;
	
	
	/**
	 * Get the condition of this For Statement.
	 */
	@Basic @Raw
	public Compound getCondition() {
		return condition;
	}
	
	/**
	 * Get the labels of this For Statement.
	 */
	@Basic @Raw
	public List<String> getLabels() {
		return labels;
	}

	/**
	 * Get the start of this For Statement.
	 */
	@Basic @Raw
	public Expression getStart() {
		return start;
	}
	
	/**
	 * Get the end of this For Statement.
	 */
	@Basic @Raw
	public Expression getEnd() {
		return end;
	}
	
	/**
	 * Get the step of this For Statement.
	 */
	@Basic @Raw
	public Expression getStep() {
		return step;
	}
	
	/**
	 * Get the body of this For Statement.
	 */
	@Basic @Raw
	public SequenceStatement getBody() {
		return body;
	}

	/**
	 * Get the storeable of this For Statement.
	 */
	@Basic @Raw
	public Storeable getStoreable() {
		return storeable;
	}

	
	/**
	 * Set the labels of this For Statement to the given labels.
	 * @param 	labels
	 * 			The new labels of this For Statement.
	 * @see	implementation
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


	@Override @Raw
	public void setFunction(Function function) {
		this.function = function;
	}

	
	
	/**
	 * @throws	NoSuchElementException
	 * 			The necessary labels for this For Statement could not be created.
	 * @throws	NullPointerException
	 * 			The function of this Statement, or the program of the function, or the body of this 
	 * 			statement was null.
	 * 			| (getFunction() == null) || (getFunction().getProgram() == null) || (getBody().compile())
	 */ // TODO: Labels of the VSP in compound have to be set!
	@Override @Raw
	public void compile() throws NoSuchElementException, NullPointerException {
		String label = getFunction().requestLabel(LabelType.FOR);
		Toolbox toolbox = new Toolbox();
		toolbox.setStatement(this);
		toolbox.addOutput(getStoreable().store(getStart()));
		toolbox.addOutput(label + "BST R0");
		toolbox.addOutput("HIA R0, " + getStoreable().evaluate());
		toolbox.addOutput("VGL R0, " + getEnd().evaluate()); // FIXME: Interpretation.
		toolbox.addOutput("HST R0");
		toolbox.addOutput(condition.compile());
		getBody().compile();
		toolbox.addOutput("SPR " + label);
		toolbox.addOutput(getLabels().get(getLabels().size() - 1) + ": NWL");
	}
	
}

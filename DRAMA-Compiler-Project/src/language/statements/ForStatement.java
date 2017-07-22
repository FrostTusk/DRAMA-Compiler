package language.statements;

import java.util.List;

import language.Compound;
import language.Function;
import language.expressions.Expression;
import language.expressions.Storeable;
import model.LabelType;
import util.Toolbox;

public class ForStatement implements Statement {

	public ForStatement(Storeable storeable, Expression start, Expression end, Expression step, Compound condition, 
			SequenceStatement body) {
		this.storeable = storeable;
		this.body = body;
		this.start = start;
		this.end = end;
		this.step = step;
		this.condition = condition;
	}
	
	
	private final Storeable storeable;
	private final Expression start;
	private final Expression end;
	private final Expression step;	
	private final Compound condition;
	private List<String> labels;
	private final SequenceStatement body;

	
	public Storeable getStoreable() {
		return storeable;
	}
	
	public Expression getStart() {
		return start;
	}
	
	public Expression getEnd() {
		return end;
	}
	
	public Expression getStep() {
		return step;
	}
	
	public List<String> getLabels() {
		return this.labels;
	}

	public SequenceStatement getBody() {
		return body;
	}

	
	public void setLabels(List<String> labels) {
		this.labels = labels;
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
	public void compile() { // TODO Fix this.
		String label1 = getFunction().requestLabel(LabelType.FOR);
		String label2 = getFunction().requestLabel(LabelType.FOR);
		Toolbox helper = new Toolbox();
		helper.setStatement(this);
		helper.addOutput(getStoreable().store(getStart()));
		helper.addOutput(label1 + "BST R0");
		helper.addOutput("HIA R0, " + getStoreable().evaluate());
		helper.addOutput("VGL R0, " + getEnd().evaluate());
		helper.addOutput("HST R0");
		helper.addOutput("VSP " + condition.compile() + label2);
		getBody().compile();
		helper.addOutput("SPR " + label1);
		helper.addOutput(label2 + ": NWL");
	}
	
}

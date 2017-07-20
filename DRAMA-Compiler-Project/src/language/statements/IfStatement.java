package language.statements;

import java.util.List;

import language.Compound;
import language.Function;
import model.LabelType;

public class IfStatement implements Statement {

	public IfStatement(Compound condition, SequenceStatement ifPart, SequenceStatement elsePart) {
		this.condition = condition;
		this.ifPart = ifPart;
		this.elsePart = elsePart;
	}
	
	
	private final Compound condition;
	private final SequenceStatement ifPart;
	private final SequenceStatement elsePart;
	
	
	public Compound getCondition() {
		return condition;
	}
	
	public SequenceStatement getIfPart() {
		return ifPart;
	}
	
	public SequenceStatement getElsePart() {
		return elsePart;
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

	
	
	private List<String> labels;
	
	
	public List<String> getLabels() {
		return this.labels;
	}
	
	
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
	
	
	@Override
	public void compile() {
		getFunction().getProgram().addOutput(getCondition().compile());
		getIfPart().compile();
		if (getElsePart() != null) {
			String label = getFunction().requestLabel(LabelType.ELSE);
			getFunction().getProgram().addOutput("SPR " + label);	
			getFunction().getProgram().addOutput(getLabels().get(getLabels().size() - 1) + ": MNTS"); // TODO Is this empty command?
			getElsePart().compile();
			getFunction().getProgram().addOutput(label + ": MNTS");
		} else
			getFunction().getProgram().addOutput(getLabels().get(getLabels().size() - 1) + ": MNTS"); // TODO Is this empty command?
	}

}

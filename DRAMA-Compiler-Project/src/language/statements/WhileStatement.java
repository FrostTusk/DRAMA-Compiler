package language.statements;

import java.util.List;

import language.Compound;
import language.Function;
import model.LabelType;

public class WhileStatement implements Statement {

	public WhileStatement(Compound condition, SequenceStatement body) {
		this.condition = condition;
		this.body = body;
	}
	
	
	private final Compound condition;
	private final SequenceStatement body;
	
	
	public Compound getCondition() {
		return condition;
	}
	
	public SequenceStatement getBody() {
		return body;
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
		String label = getFunction().requestLabel(LabelType.WHILE);
		getFunction().getProgram().addOutput(getCondition().compile());
		getBody().compile();
		getFunction().getProgram().addOutput("SPR " + label);
		getFunction().getProgram().addOutput(getLabels().get(getLabels().size() - 1) + ": MNTS"); // TODO Is this empty command?
	}
	
}

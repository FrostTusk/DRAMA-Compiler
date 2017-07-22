package language.statements;

import java.util.List;

import language.Function;
import language.expressions.Storeable;
import model.LabelType;
import util.Toolbox;

public class ForStatement implements Statement {

	public ForStatement(Storeable storeable, int start, int end, int step, SequenceStatement body) {
		this.storeable = storeable;
		this.body = body;
		this.start = start;
		this.end = end;
		this.step = step;
	}
	
	
	private final Storeable storeable;
	private final int start;
	private final int end;
	private final int step;	
	private List<String> labels;
	private final SequenceStatement body;

	
	public Storeable getStoreable() {
		return storeable;
	}
	
	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public int getStep() {
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
	public void compile() { // TODO Reread HEAP!
		String label1 = getFunction().requestLabel(LabelType.FOR);
		String label2 = getFunction().requestLabel(LabelType.FOR);
		Toolbox helper = new Toolbox();
		helper.setStatement(this);
		helper.addOutput("HIA R0, " + storeable.evaluate());
		helper.addOutput("BIG " + Integer.toString(start) + ", " + "HEAP"); // TODO Reread HEAP
		helper.addOutput("BST R0");
		helper.addOutput("HIA R0, HEAP");
		helper.addOutput("OPT.w R0, 1");
		helper.addOutput("BIG R0, HEAP");
		helper.addOutput("HST R0");
		helper.addOutput(label1 + ": NWL");
		helper.addOutput("VGL R7"); // TODO Reread HEAP
		helper.addOutput("VSP GRG, " + label2);
		getBody().compile();
		helper.addOutput("HIA R7");
		helper.addOutput("OPT");
		helper.addOutput("BIG R7");
		getFunction().getProgram().addOutput("SPR " + label1);
		helper.addOutput(label2 + ": NWL");
	}
	
}

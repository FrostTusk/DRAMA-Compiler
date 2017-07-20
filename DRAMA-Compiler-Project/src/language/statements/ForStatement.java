package language.statements;

import java.util.List;

import language.Function;
import model.LabelType;

public class ForStatement implements Statement {

	public ForStatement(int start, int end, int step, SequenceStatement body) {
		this.body = body;
		this.start = start;
		this.end = end;
		this.step = step;
	}
	
	
	private final int start;
	private final int end;
	private final int step;	
	private List<String> labels;
	private final SequenceStatement body;


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
		write("BIG " + Integer.toString(start) + ", " + "HEAP"); // TODO Reread HEAP
		write("BST R0");
		write("HIA R0, HEAP");
		write("OPT.w R0, 1");
		write("BIG R0, HEAP");
		write("HST R0");
		write(label1 + ": MNTS");
		write("VGL R7"); // TODO Reread HEAP
		write("VSP GRG, " + label2);
		getBody().compile();
		write("HIA R7");
		write("OPT");
		write("BIG R7");
		getFunction().getProgram().addOutput("SPR " + label1);
		write(label2 + ": MNTS");
	}
	
	
	public void write(String command) {
		getFunction().getProgram().addOutput(command);
	}
	
}

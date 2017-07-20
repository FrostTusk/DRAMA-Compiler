package language.statements;

import language.Compound;
import language.Function;
import model.LabelType;

public class IfStatement implements Statement {

	public IfStatement(Compound check, SequenceStatement ifPart, SequenceStatement elsePart) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Function getFunction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFunction(Function function) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void compile() {
		String label = getFunction().requestLabel(LabelType.IF) + ": ";
		getFunction().getProgram().addOutput(label + ""); //TODO Add what?
	}

}

package language.statements;

import language.Variable;
import model.Program;

public class PrintStatement implements Statement {

	public PrintStatement(Variable variable) {
		this.variable = variable;
	}
	
	
	private Variable variable;
	
	
	@Override
	public Program getOwner() {
		return this.owner;
	}
	
	private Program owner;
	
	@Override
	public void setOwner(Program owner) {
		this.owner = owner;		
	}


	@Override
	public void compile() {
		getOwner().addOutput("DRU " + variable.evaluate());
	}

}

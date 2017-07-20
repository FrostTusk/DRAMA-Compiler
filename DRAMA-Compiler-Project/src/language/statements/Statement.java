package language.statements;

import model.Program;

public interface Statement {
	
	public Program getOwner();
	public void setOwner(Program owner);
	public void compile();
}

package language;

import java.util.List;

import language.statements.Statement;
import model.Label;
import model.Program;

public class Function {
	
	public Function(String name, List<Parameter> parameters, Statement statement) {
		setName(name);
	}
	
	
	private String name;
	
	
	public String getName() {
		return this.name;
	}
	
	
	private void setName(String name) {
		this.name = name;
	}
	
	
	
	private Program program;
	private Label label;
	
	
	public Program getProgram() {
		return this.program;
	}
	
	public Label getLabel() {
		return this.label;
	}
	
	
	public void setProgram(Program program) {
		this.program = program;
	}
	
	public void setLabel(Label label) {
		this.label = label;
	}
	
	
	
	public Label requestLabel() {
		return null;
	}
	
	public void compile() {
		setLabel(requestLabel());
	}
	
}

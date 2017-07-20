package language;

import java.util.List;

import language.expressions.ParameterExpression;
import language.statements.Statement;
import model.LabelType;
import model.Program;

public class Function {
	
	public Function(String name, List<ParameterExpression> parameters, Statement statement) {
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
	private String label;
	
	
	public Program getProgram() {
		return this.program;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	
	public void setProgram(Program program) {
		this.program = program;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	/**
	 * Returns an unused label of the given labeltype
	 * @return
	 */
	public String requestLabel(LabelType type) {
		return null;
	}
	
	public void compile() {
	}
	
}

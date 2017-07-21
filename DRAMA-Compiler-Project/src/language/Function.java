package language;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import language.expressions.ParameterExpression;
import language.expressions.VariableExpression;
import language.statements.Statement;
import model.LabelType;
import model.Program;

public class Function {
	
	public Function(Statement statement, String name, List<ParameterExpression> parameters) {
		this.name = name;
		this.statement = statement;
		this.parametersMap = new HashMap<String, ParameterExpression>();
		setParameters(parameters);
	}
	
	
	private final Statement statement;
	private final String name;
	
	
	public Statement getStatement() {
		return this.statement;
	}
	
	public String getName() {
		return this.name;
	}	
	
	
	
	private Map<String, ParameterExpression> parametersMap;
	private Map<String, VariableExpression> localVarsMap;
	
	
	public ParameterExpression getParameter(String name) {
		if (!parametersMap.containsKey(name))
			throw new NoSuchElementException();
		return parametersMap.get(name);
	}
	
	
	public boolean canHaveAsParameter(ParameterExpression parameter) {
		for (String ownedName: parametersMap.keySet())
			if (ownedName.equals(parameter.getName()))
				return false;
		return true;
	}
	
	public boolean canHaveAsLocalVariable(VariableExpression variable) {
		for (String ownedName: localVarsMap.keySet())
			if (ownedName.equals(variable.getName()))
				return false;
		return true;
	}
	
	
	
	private void setParameters(List<ParameterExpression> parameters) {
		for (ParameterExpression parameter: parameters) {
			if (!canHaveAsParameter(parameter))
				throw new IllegalArgumentException();
			this.parametersMap.put(parameter.getName(), parameter);
		}
	}
	
	
	public void addLocalVariable(VariableExpression variable) {
		if (!canHaveAsLocalVariable(variable))
			throw new IllegalArgumentException();
		this.localVarsMap.put(variable.getName(), variable);
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
	 * Returns an unused label of the given LabelType
	 * @return
	 */
	public String requestLabel(LabelType type) {
		return getProgram().getLabel(this, type);
	}
	
	public void compile() {
		getStatement().compile();
	}
	
}

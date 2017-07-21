package language;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.expressions.ParameterExpression;
import language.expressions.VariableExpression;
import language.statements.Statement;
import model.LabelType;
import model.Program;

public class Function {
	
	public Function(Statement statement, String name, List<ParameterExpression> parameters) {
		this.regAmt = 0;
		this.name = name;
		this.statement = statement;
		this.parametersMap = new HashMap<String, ParameterExpression>();
		setParameters(parameters);
	}
	
	
	/**
	 * Variable containing the statement enclosed in this Function.
	 */
	private final Statement statement;
	/**
	 * Variable containing the name of this Function.
	 */
	private final String name;
	
	
	@Basic @Raw
	public Statement getStatement() {
		return this.statement;
	}
	
	@Basic @Raw
	public String getName() {
		return this.name;
	}	
	
	
	/**
	 * Variable containing amount of registers used by this Function.
	 */
	private int regAmt;
	/**
	 * Map containing the ParametersExpressions (parameters) of this Function mapped to their name.
	 */
	private Map<String, ParameterExpression> parametersMap;
	/**
	 * Map containing the VariableExpressions (local variables) of this Function mapped to their name.
	 */
	private Map<String, VariableExpression> localVarsMap;
	
	
	@Basic @Raw
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
			if ((ownedName.equals(variable.getName()) || ((variable.inRegister()) && (regAmt >= 3))))
				return false;
		return true;
	}
	

	/**
	 * Set the parameters of this Function to the given parameters.
	 * This method also checks if each parameter is a valid parameter.
	 * @param parameters
	 */
	private void setParameters(List<ParameterExpression> parameters) {
		for (ParameterExpression parameter: parameters) {
			if (!canHaveAsParameter(parameter))
				throw new IllegalArgumentException();
			this.parametersMap.put(parameter.getName(), parameter);
		}
	}

	/**
	 * 
	 * @param variable
	 */
	public void addLocalVariable(VariableExpression variable) {
		if (!canHaveAsLocalVariable(variable))
			throw new IllegalArgumentException();
		this.localVarsMap.put(variable.getName(), variable);
		if (variable.inRegister())
			regAmt += 1;
	}
	
	
	
	private Program program;
	
	
	public Program getProgram() {
		return this.program;
	}
	
	
	public void setProgram(Program program) {
		this.program = program;
	}
	

	
	
	/**
	 * Returns an unused label of the given LabelType
	 * @return
	 */
	public String requestLabel(LabelType type) {
		return getProgram().requestLabel(this, type);
	}
	
	
	public void compile() {
		getProgram().addOutput(requestLabel(LabelType.FUNCTION) + ": NWL");
		for (int i = 0; i < regAmt; i++)
			getProgram().addOutput("BST R" + Integer.toString(6 - i));
		getStatement().compile();
		for (int i = 0; i < regAmt; i++)
			getProgram().addOutput("HST R" + Integer.toString(6 - i));
		getProgram().addOutput("KTG");
	}
	
}

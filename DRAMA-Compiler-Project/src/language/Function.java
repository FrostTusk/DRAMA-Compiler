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
import model.Compilable;
import model.LabelType;
import model.Program;

/**
 * A class that represent a DRAMA function.
 * @invar	A Function must be able to have each of it's parameters as a parameter.
 * @invar	A Program must be able to have each of it's variables as a local variable. 
 * @author	Mathijs Hubrechtsen
 */
public class Function implements Compilable {
	
	/**
	 * Create a new Function object with a given statement, name, and parameters.
	 * @param	dataType
	 * 			The data type of this Function.
	 * @param 	parameters
	 * 			The parameters of this Function.
	 * @param 	statement
	 * 			The statement enclosed by this Function.
	 * @param 	name
	 * 			The name of this Function.
	 * @see	implementation
	 * @throws	IllegalArgumentException
	 * 			The Function cannot have each of the given parameters as parameter.
	 * 			| !for each parameters in parameters: this.canHaveAsParameter(parameter)
	 */
	public Function(DataType dataType, List<ParameterExpression> parameters, Statement statement, String name) 
				throws IllegalArgumentException {
		this.dataType = dataType;
		this.regAmt = 0;
		this.name = name;
		this.statement = statement;
		this.parametersMap = new HashMap<Integer, ParameterExpression>();
		setParameters(parameters);
	}
	
	
	/**
	 * Variable containing the data type of this Function.
	 */
	private final DataType dataType;
	/**
	 * Variable containing amount of registers used by this Function.
	 */
	private int regAmt;
	/**
	 * Map containing the ParametersExpressions (parameters) of this Function mapped to their number.
	 */
	private Map<Integer, ParameterExpression> parametersMap;
	/**
	 * Map containing the VariableExpressions (local variables) of this Function mapped to their name.
	 */
	private Map<String, VariableExpression> localVarsMap;	// TODO: Name <> Number?
	/**
	 * Variable containing the statement enclosed in this Function.
	 */
	private final Statement statement;
	/**
	 * Variable containing the name of this Function.
	 */
	private final String name;
	
	
	/**
	 * Get the data type of this Function. 
	 */
	@Basic @Raw
	public DataType getDataType() {
		return dataType;
	}
	
	/**
	 * Returns the parameter located at the given number.
	 * @param 	number
	 * 			The number of the parameter to be returned. 
	 * @return	Returns the parameter located at the given name.
	 * @throws 	NoSuchElementException
	 * 			There is no parameter with the given name.
	 */
	@Raw
	public ParameterExpression getParameter(int number) throws NoSuchElementException {
		if (!parametersMap.containsKey(number))
			throw new NoSuchElementException();
		return parametersMap.get(number);
	}
	
	/**
	 * Returns the amount of parameters used by this Function.
	 * @return	Returns the amount of parameters used by this Function.
	 */
	@Raw
	public int getParameterAmt() {
		return parametersMap.size();
	}
	
	/**
	 * Get the statement enclosed in this Function. 
	 */
	@Basic @Raw
	public Statement getStatement() {
		return this.statement;
	}
	
	/**
	 * Get the name of this Function.
	 */
	@Basic @Raw
	public String getName() {
		return this.name;
	}	
	
	
	/**
	 * Checks whether this Function can have the give parameter as parameter.
	 * @param	parameter
	 * 			The parameter to be checked.
	 * @return	Returns true if it can have the parameter as parameter, 
	 * 			false if not.
	 */
	@Raw
	public boolean canHaveAsParameter(ParameterExpression parameter) {
		for (ParameterExpression ownedParameter: parametersMap.values())
			if (ownedParameter.getName().equals(parameter.getName()))
				return false;
		return true;
	}
	
	/**
	 * Checks whether this Function can have the give variable as local variable.
	 * @param	variable
	 * 			The variable to be checked.	
	 * @return	Returns true if it can have the variable as local variable, 
	 * 			false if not.
	 */
	@Raw
	public boolean canHaveAsLocalVariable(VariableExpression variable) {
		for (String ownedName: localVarsMap.keySet())
			if ((ownedName.equals(variable.getName()) || ((variable.inRegister()) && (regAmt >= 3))))
				return false;
		return true;
	}
	

	/**
	 * Add a given variable to the local variables of this Function. If the given
	 * variable needs to be kept in a register, the internal register counter will
	 * also be updated.
	 * @param 	variable
	 * 			The variable to be added.
	 * @post	The variable has been added to the local variables of this Function.
	 * @throws	IllegalArgumentException
	 * 			The Function cannot have the given variable as local variable
	 * 			| !canHaveAsLocalVariable(variable)
	 */
	@Raw
	public void addLocalVariable(VariableExpression variable) throws IllegalArgumentException {
		if (!canHaveAsLocalVariable(variable))
			throw new IllegalArgumentException();
		this.localVarsMap.put(variable.getName(), variable);
		if (variable.inRegister())
			regAmt += 1;
	}

	/**
	 * Set the parameters of this Function to the given parameters if able.
	 * @param 	parameters
	 * 			The new parameters of this Function.
	 * @post	The parameters of this Function have been set to the given parameters.
	 * @throws	IllegalArgumentException
	 * 			The Function cannot have each of the given parameters as parameter.
	 * 			| !for each parameters in parameters: this.canHaveAsParameter(parameter)
	 */
	@Raw
	private void setParameters(List<ParameterExpression> parameters) throws IllegalArgumentException {
		for (ParameterExpression parameter: parameters) {
			if (!canHaveAsParameter(parameter))
				throw new IllegalArgumentException();
			this.parametersMap.put(parameter.getNumber(), parameter);
		}
	}

	
	
	/**
	 * Variable registering to which Program this Function belongs.
	 */
	private Program program;
	
	
	/**
	 * Get the Program to which this Function belongs.
	 */
	@Basic
	public Program getProgram() {
		return this.program;
	}
	
	
	/**
	 * Sets the program of this Function to the given program.
	 * @see	implementation
	 * @param 	program
	 * 			The new program of this Function.
	 */
	public void setProgram(Program program) {
		this.program = program;
	}
	
	
	/**
	 * Returns an unused (by the Program of this Function) label 
	 * of the given label type if able.
	 * @param 	type
	 * 			The type of the label.
	 * @return	Returns a label of the given label type.
	 * @throws	NoSuchElementException
	 * 			No label of the given type could be returned.
	 * @throws	NullPointerException
	 * 			This Function does not belong to any Program
	 * 			| this.getProgram() == null
	 */
	public String requestLabel(LabelType type) throws NoSuchElementException, NullPointerException {
		return getProgram().requestLabel(this, type);
	}
	
	
	@Override
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

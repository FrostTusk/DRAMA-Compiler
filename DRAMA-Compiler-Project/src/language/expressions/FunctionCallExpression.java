package language.expressions;

import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.DataType;
import language.Function;

/**
 * A Class that represents a DRAMA Function Call Expression.
 * A Function Call Expression is created when the output of a
 * function call is stored in a Storeable.
 * @author Mathijs Hubrechtsen
 */
public class FunctionCallExpression implements Expression {

	/**
	 * Create a new Function Call Expression object with a given call and parameters.
	 * @param	call
	 * 			The function called by this Function Call Expression.
	 * @param	parameters
	 * 			The parameters given to this Function Call Expression.	
	 */
	public FunctionCallExpression(Function call, List<Storeable> parameters) {
		if (parameters.size() != call.getParameterAmt())
			throw new IllegalArgumentException();
		this.call = call;
		this.parameters = parameters;
	}
	
	
	/**
	 * Variable containing the function called by this Function Call Expression.
	 */
	private final Function call;
	/**
	 * List containing the parameters given to this Function Call Expression.
	 */
	private final List<Storeable> parameters;
	
	
	
	/**
	 * Get the data type returned by this Function Call Expression.
	 */
	@Basic @Raw @Override
	public DataType getDataType() {
		return getCall().getDataType();
	}

	/**
	 * Get the function called by this Function Call Expression.
	 */
	@Basic @Raw
	public Function getCall() {
		return call;
	}
	
	/**
	 * Get the parameters given to this Function Call Expression.
	 */
	@Basic @Raw
	public List<Storeable> getParameters() {
		return parameters;
	}
	
	
	@Override
	public String evaluate() {
		StringBuilder stringBuilder = new StringBuilder();
		int count = 1;
		stringBuilder.append("BST R0");
		for (int i = 1; i < getParameters().size() + 1; i++) {
			if (getCall().getParameter(i).inRegister()) {
				stringBuilder.append("BST R" + Integer.toString(i));
				stringBuilder.append("HIA R" + Integer.toString(count++) + getParameters().get(i).evaluate() + System.lineSeparator());
			}
		} 
		for (int i = 0; i < getParameters().size() + 1; i++) {
			if (!getCall().getParameter(i).inRegister()) {
				stringBuilder.append("HIA: R0, " + getParameters().get(i).evaluate() + System.lineSeparator());
				stringBuilder.append("BST R0" + System.lineSeparator()); // TODO: Structs/Arrays
			}
		}
		stringBuilder.append("SBR " + getCall().getName() + "-FUNCTION");
		stringBuilder.append("OPT.w R9, " + Integer.toString(getParameters().size() - count)); // TODO: Structs.Arrays
		stringBuilder.append("HST R0");
		return stringBuilder.toString();
	}

}

package language.expressions;

import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.DataType;
import language.Function;

public class FunctionCallExpression implements Expression {

	/**
	 * Create a new Function Call Expression object with a given call and parameters
	 * @param	call
	 * 			The function called by this Function Call Expression.
	 * @param	parameters
	 * 			The parameters given to this Function Call Expression.	
	 * @see	implementation
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
		String stringBuilder = "";
		int count = 1;
		for (int i = 1; i < getParameters().size() + 1; i++) {
			if (getCall().getParameter(i).inRegister())
				stringBuilder += "HIA R" + Integer.toString(count++) + ", " + getParameters().get(i).evaluate() + "\n ";
			else {
				stringBuilder += "BST" + "\n "; // FIXME: What to do with structs/arrays?
			} // FIXME: command/interpretation
		} 
		return stringBuilder;
	}

}

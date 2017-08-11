package language.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.DataType;
import util.Toolbox;

/**
 * A Class that represents a C Expression.
 * @author Mathijs Hubrechtsen
 */
public class ParameterExpression implements Storeable {
	
	/**
	 * Create a new Parameter Expression object with a given register, data type,
	 * number, and name.
	 * @param 	register
	 * 			Is this Parameter Expression in a register? Yes: true, No: false. 
	 * @param 	type
	 * 			The data type of this Parameter Expression.
	 * @param 	number
	 * 			The number of this Parameter Expression.
	 * @param 	name
	 * 			The name of this Parameter Expression.
	 */
	public ParameterExpression(boolean register, DataType type, int number, String name) {
		this.register = register;
		this.type = type;
		this.number = number;
		this.name = name;
	}
	
	
	/**
	 * Variable containing whether this Parameter Expression is in a register or not.
	 */
	private final boolean register;
	/**
	 * Variable containing the data type of this Parameter Expression.
	 */
	private final DataType type;
	/**
	 * Variable containing the number of this Parameter Expression.
	 */
	private final int number;
	/**
	 * Variable containing the name of this Parameter Expression.
	 */
	private final String name;
	
	
	/**
	 * Get whether this Parameter Expression is in a register or not.
	 */
	@Basic @Raw
	public boolean inRegister() {
		return register;
	}

	@Basic @Override @Raw
	public DataType getDataType() {
		return type;
	}
	
	/**
	 * Get the number of this Parameter Expression.
	 */
	@Basic @Raw
	public int getNumber() {
		return number;
	}

	/**
	 * Get the name of this Parameter Expression.
	 */
	@Basic @Raw
	public String getName() {
		return name;
	}
	
	
	
	/**
	 * Variable containing the location of this Parameter Expression.
	 */
	private String location;
	
	
	@Basic @Override @Raw
	public String getLocation() {
		return location;
	}

	
	@Override @Raw
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
	/** 
	 * @throws	IllegalArgumentException
	 * 		   	The Data Type of this Parameter Expression and the expression are incompatible.
	 * 		  	| getDataType() != expression.getDataType()
	 */ 
	@Override @Raw
	public String store(Expression expression) throws IllegalArgumentException {
		if (this.getDataType() != expression.getDataType())
			throw new IllegalArgumentException();

		if (register)
			return "HIA" + new Toolbox().getInterpretation(getDataType()) + getLocation()
					+ ", " + expression.evaluate();
		return "BIG" + new Toolbox().getInterpretation(getDataType()) + getLocation()
				+ ", " + expression.evaluate();
	}
	
	@Override @Raw
	public String store(String location) {
		if (register)
			return "HIA " + getLocation() + ", " + location;
		return "BIG " + location + ", " + getLocation();
	}
	

	@Override @Raw
	public String evaluate() {
		return getLocation();
	}
	
}

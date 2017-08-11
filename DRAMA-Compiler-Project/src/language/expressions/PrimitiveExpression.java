package language.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.DataType;

/**
 * A Class that represents a C Primitive Expression.
 * A Primitive Expression is a very basic value, such as
 * integer literals.
 * @author Mathijs Hubrechtsen
 */
public class PrimitiveExpression implements Expression {
	
	/**
	 * Create a new Primitive Expression object with a given data type and value.
	 * @param	dataType
	 * 			The data type of this Primitive Expression.
	 * @param	value
	 * 			The value of this Primitive Expression.
	 * @see	implementation
	 */
	public PrimitiveExpression(DataType dataType, String value) {
		this.dataType = dataType;
		this.value = value;
	}
	
	
	/**
	 * Variable containing the data type of this Primitive Expression.
	 */
	private DataType dataType;
	/**
	 * Variable containing the value of this Primitive Expression.
	 */
	private String value;
	
	
	@Basic @Override @Raw
	public DataType getDataType() {
		return dataType;
	}
	
	/**
	 * Get the value of this Primitive Expression.
	 */
	@Basic @Raw
	public String getValue() {
		return value;
	}

	
	@Override @Raw
	public String evaluate() {
		return getValue();
	}
	
}

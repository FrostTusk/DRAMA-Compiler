package language.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import language.DataType;

public interface Expression {

	/**
	 * Get the data type of this Function. 
	 */
	@Basic @Raw
	public DataType getDataType();
	/**
	 * Evaluate this expression to it's String format, the format in which
	 * it will appear in a DRAMA program. 
	 * 
	 * @return Returns this expression in it's String format.
	 */
	public String evaluate();
	
}

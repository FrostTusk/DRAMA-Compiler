package language.expressions;

import other.DataType;

public interface Expression {

	public DataType getDataType();
	public String evaluate();
	
}

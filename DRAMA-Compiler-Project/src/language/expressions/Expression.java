package language.expressions;

import language.DataType;

public interface Expression {

	public DataType getDataType();
	public String evaluate();
	
}

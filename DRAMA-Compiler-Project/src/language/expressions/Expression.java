package language.expressions;

import model.DataType;

public interface Expression {

	public DataType getDataType();
	public String evaluate();
	
}

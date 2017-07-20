package language.expressions;

import model.DataType;

public class PrimitiveExpression implements Expression {
	
	public PrimitiveExpression(DataType dataType, String value) {
		this.dataType = dataType;
		this.value = value;
	}
	
	
	private DataType dataType;
	private String value;
	
	
	@Override
	public DataType getDataType() {
		return dataType;
	}
	
	
	@Override
	public String evaluate() {
		return value;
	}
	
}

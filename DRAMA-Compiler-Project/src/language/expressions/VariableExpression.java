package language.expressions;

import model.DataType;

public class VariableExpression implements Expression {
	
	public VariableExpression(DataType dataType, String name) {
		
	}
	
	
	public boolean register;

	
	public String getLocation() {
		return null;
	}
	

	public void setLocation() {
		
	}

	/**
	 * 
	 * @param expression
	 * @return
	 * @throws IllegalArgumentException
	 * 		   this.getDataType() != expression.getDataType()
	 */
	public String store(Expression expression) throws IllegalArgumentException {
		if (this.getDataType() != expression.getDataType())
			throw new IllegalArgumentException();
		if (register)
			return "HIA " + getLocation() + ", " + expression.evaluate();
		return "BIG" + getLocation() + ", " + expression.evaluate();
	}
	
	
	
	private DataType dataType;
	
	
	@Override
	public DataType getDataType() {
		return dataType;
	}



	public String evaluate() {
		return null;
	}
	
}

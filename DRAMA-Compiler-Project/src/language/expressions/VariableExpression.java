package language.expressions;

import language.DataType;
import model.Toolbox;

public class VariableExpression implements Storeable {
	
	public VariableExpression(boolean register, DataType dataType, String name) {
		this.register = register;
		this.dataType = dataType;
		this.name = name;
	}
	
	
	private String location;
	private final String name;
	private final boolean register;


	public String getLocation() {
		return location;
	}

	
	public String getName() {
		return this.name;
	}
	
	public boolean inRegister() {
		return register;
	}
	

	public void setLocation(String location/*, boolean register*/) {
		this.location = location;
//		this.register = register;
	}
	
	
	/**
	 * @throws IllegalArgumentException
	 * 		   this.getDataType() != expression.getDataType()
	 */
	public String store(Expression expression) throws IllegalArgumentException {
		if (this.getDataType() != expression.getDataType())
			throw new IllegalArgumentException();
		Toolbox helper = new Toolbox();
		String pointletter = helper.getInterpretation(getDataType());
		
		if (register)
			return "HIA" + pointletter + " " + getLocation() + ", " + expression.evaluate();
		return "BIG" + pointletter + " " + expression.evaluate() + ", " + getLocation();
	}
	
	
	public String store(String location) {
		if (register)
			return "HIA " + getLocation() + ", " + location;
		return "BIG" + location + ", " + getLocation();
	}
	
	
	
	private final DataType dataType;


	@Override
	public DataType getDataType() {
		return dataType;
	}
	
	
	public String evaluate() {
		return getLocation();
	}
	
}

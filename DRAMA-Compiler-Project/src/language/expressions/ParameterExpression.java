package language.expressions;

import language.DataType;

public class ParameterExpression implements Expression, Storeable {
	
	public ParameterExpression(boolean register, DataType type, String name) {
		this.register = register;
		this.type = type;
		this.name = name;
	}
	
	
	private final boolean register;
	private final DataType type;
	private final String name;

	
	public boolean inRegister() {
		return register;
	}

	@Override
	public DataType getDataType() {
		return type;
	}
	
	public String getName() {
		return name;
	}



	private String location;
	
	
	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public void setLocation(String location) {
		this.location = location;
	}
	
	

	@Override
	public String evaluate() {
		return getLocation();
	}

	@Override
	public String store(Expression expression) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

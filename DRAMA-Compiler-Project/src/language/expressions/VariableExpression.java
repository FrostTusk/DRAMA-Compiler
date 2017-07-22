package language.expressions;

import static model.Instructions.BIG;
import static model.Instructions.HIA;

import language.DataType;
import model.Instructions;
import util.Toolbox;

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

		if (register){
			return Instructions.construct(HIA, new Toolbox().getInterpretation(getDataType())
					, getLocation(),expression.evaluate());
		}
		return Instructions.construct(BIG, new Toolbox().getInterpretation(getDataType())
				, expression.evaluate(), getLocation());
	}
	
	
	public String store(String location) {
		if (register)
			return Instructions.construct(HIA, getLocation(), location);
		return Instructions.construct(BIG, location, getLocation());
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

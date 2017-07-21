package helper;

import language.DataType;
import language.statements.Statement;

public class Helper {
	
	private Statement statement;
	
	
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	public void addOutput(String result) {
		statement.getFunction().getProgram().addOutput(result);
	}
	
	public void addOutput(Statement statement, String result) {
		statement.getFunction().getProgram().addOutput(result);
	}
	
	
	
	public String getPointLetter(DataType type) {
		switch(type) {
		case INT:
			return ".w";
		default:
			return "";
		}
	}
	
}

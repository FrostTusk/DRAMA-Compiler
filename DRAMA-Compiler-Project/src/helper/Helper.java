package helper;

import language.DataType;

public class Helper {
	
	public String getPointLetter(DataType type) {
		switch(type) {
		case INT:
			return ".w";
		default:
			return "";
		}
	}
	
}

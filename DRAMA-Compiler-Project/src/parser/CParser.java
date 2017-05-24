package parser;

import java.io.*;
import language.*;
import model.*;

public class CParser {	
	
	public Program parse(URL url) {
		return null;
	}
	
	public String parseToString(URL url) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(url.getTarget()));;
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    return sb.toString();
		} finally {
			    br.close();
		}
	}
	
	
	public Program parseToProgram(String program) {
		return null;
	}
	
	
	@SuppressWarnings("unused")
	private Statement parseToStatement(String line) {
		return null;
	}
	
}

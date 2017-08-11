package util;

import be.kuleuven.cs.som.annotate.Raw;

/**
 * A Message Class containing various error messages that can be displayed. 
 * @author	Mathijs Hubrechtsen
 */
public class ErrorHandler {
	
	/**
	 * Handles displaying the various error messages that can be displayed.
	 * @param 	error
	 * 			The error to be displayed.
	 */
	@Raw
	public void handleError(ErrorType error) {
		switch(error) {
			case COMPILE_GENERAL:
				System.out.println("ERROR: COMPILATION: general compilation error!");
				return;
			case IO_GENERAL:
				System.out.println("ERROR: I/O: general I/O error!");
				return;
			case IO_INPUT:
				System.out.println("ERROR: I/O: input file error!");
				return;
			case IO_OUTPUT:
				System.out.println("ERROR: I/O: output file error!");
				return;
			case PARSE_GENERAL:
				System.out.println("ERROR: PARSING: general parsing error!");
				return;
			default:
				System.out.println("ERROR: unkown error!");
				return;
		}
	}
	
}

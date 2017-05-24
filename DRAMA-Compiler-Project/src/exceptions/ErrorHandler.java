package exceptions;

public class ErrorHandler {
	
	public void handleIOError(ErrorType IOerror) {
		switch(IOerror) {
			case INPUT:
				System.out.println("ERROR: input file error!");
				break;
			case OUTPUT:
				System.out.println("ERROR: output file error!");
				break;
			default:
				throw new IllegalArgumentException();
		}
	}
	
}

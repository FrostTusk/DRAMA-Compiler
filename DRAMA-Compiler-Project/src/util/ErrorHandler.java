package util;

public class ErrorHandler {
	
	public void handleIOError(ErrorType IOerror) {
		switch(IOerror) {
			case INPUTIO:
				System.out.println("ERROR: I/O: input file error!");
				break;
			case OUTPUTIO:
				System.out.println("ERROR: I/O: output file error!");
				break;
			case UNKOWNIO:
				System.out.println("ERROR: I/O: unkown I/O error!");
			default:
				throw new IllegalArgumentException();
		}
	}
	
}

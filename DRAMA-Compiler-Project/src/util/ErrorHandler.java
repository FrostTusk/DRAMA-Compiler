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
				break;
			case UNKOWN:
				System.out.println("ERROR: unkown error!");
				break;
			default:
				throw new IllegalArgumentException();
		}
	}
	
	
	public void handleParsingError(ErrorType parseError) {
		switch (parseError) {
			case GENERALPARSE:
				System.out.println("ERROR: PARSING: general parsing error!");
				break;
			case UNKOWN:
				System.out.println("ERROR: unkown error!");
				break;
			default:
				throw new IllegalArgumentException();
		}
	}
	
	
	public void handleCompilationError(ErrorType compileError) {
		switch (compileError) {
			case GENERALCOMPILE:
				System.out.println("ERROR: COMPILATION: general compilation error!");
				break;
			case UNKOWN:
				System.out.println("ERROR: unkown error!");
				break;
			default:
				throw new IllegalArgumentException();
		}
	}
	
}

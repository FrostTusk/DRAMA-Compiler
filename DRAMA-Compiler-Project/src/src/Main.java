package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import model.Program;
import parser.CParser;
import util.ErrorHandler;
import util.ErrorType;
import util.URL;

public class Main {
	
	private static CParser parser = new CParser();
	private static ErrorHandler errorHandler = new ErrorHandler();
	private static Scanner scanner = new Scanner(System.in);
	

	public static void main(String[] args) {
//		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		System.out.println("DRAMA-Compiler, v.27/07/2017");
		try {
			handleArgs(args[0], args[1]);
		} catch (IndexOutOfBoundsException exc) {
			handleNoArgs();
		}
		scanner.close();
	}
	
	
	private static void handleHelp() {
		FileReader helpReader;
		try {
			helpReader = new FileReader("src/util/help.txt");
		} catch (FileNotFoundException e) {
			System.out.println("help could not be openend because help.txt is missing...");
			return;
		}
		
		System.out.println("Opening help.txt...");
		BufferedReader br = new BufferedReader(helpReader);
	    StringBuilder sb = new StringBuilder();
	    try {
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
		        line = br.readLine();
		    }
		    System.out.println(sb.toString());
			br.close();
	    } catch (IOException e) {
	    	errorHandler.handleIOError(ErrorType.UNKOWNIO);
	    }
	}
	
	
	public static void handleArgs(String input, String output) {
		System.out.println("Handling Command Line Args");
		try {
			FileReader testReader = new FileReader(input);
			testReader.close();
			System.out.format("Reading: %s ...%n", input);
		    PrintWriter writer = new PrintWriter(output, "UTF-8");
		    writer.println("| File generated by DRAMA-Compiler");
		    writer.close();
		    System.out.format("Writing: %s ...%n", output);
		} catch (IOException e) {
			errorHandler.handleIOError(ErrorType.INPUTIO);
		}
		
		Program program;
		try {
			program = parser.parse(new URL(input));
			try {
				program.compile(new URL(output));
			} catch(RuntimeException e){
				System.out.println("Not Implemented.");
			}
		} catch (IOException e) {
			errorHandler.handleIOError(ErrorType.UNKOWNIO);
		} catch (RuntimeException e) {
			System.out.println("Not Implemented."); // TODO: N/A
		}		
	}
	
	
	public static void handleNoArgs() {
		System.out.println("Handling No Command Line Args");
		System.out.println("Type help() for help, quit() to quit");
		URL input = null, output = null;
		boolean inputSet = false, outputSet = false;
		
		while (!inputSet) {
			System.out.format("Enter target input file: ");
			String buffer = scanner.nextLine();
			if (buffer.equals("quit()")) {
				System.out.format("Quitting...");
				return;
			} else if (buffer.equals("help()")) {
				handleHelp();
				continue;
			}
			
			input = new URL(buffer);
			inputSet = true;
			try {
				FileReader testReader = new FileReader(input.getTarget());
				testReader.close();
				System.out.format("Reading: %s .../n", input.getTarget());
			} catch (IOException e) {
				inputSet = false;
				errorHandler.handleIOError(ErrorType.INPUTIO);
			}
		}
		
		while (!outputSet) {
			System.out.format("Enter target output file: ");
			String buffer = scanner.nextLine();
			if (buffer.equals("quit()")) {
				System.out.format("Quitting...");
				return;
			} else if (buffer.equals("help()")) {
				handleHelp();
				continue;
			}
			
			output = new URL(buffer);
			outputSet = true;
			try {
			    PrintWriter writer = new PrintWriter(output.getTarget(), "UTF-8");
			    writer.println("| File generated by DRAMA-Compiler");
			    writer.close();
			    System.out.format("Writing: %s ...%n", output.getTarget());
			} catch (IOException e) {
				outputSet = false;
				errorHandler.handleIOError(ErrorType.OUTPUTIO);
			}
		}
		
		Program program;
		try {
			program = parser.parse(input);
			try {
				program.compile(output);
			} catch(RuntimeException e){
				System.out.println("Not Implemented.");
			}
		} catch (IOException e) {
			errorHandler.handleIOError(ErrorType.UNKOWNIO);
		} catch (RuntimeException e) {
			System.out.println("Not Implemented.");	// TODO: N/A
		}		
	}
	
}

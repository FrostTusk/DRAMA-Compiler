package language;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import language.expressions.ParameterExpression;
import language.expressions.PrimitiveExpression;
import language.expressions.VariableExpression;
import language.statements.PrintStatement;
import language.statements.Statement;
import model.Program;
import util.Toolbox;

public class TestProgram {

	private Program program;
	private List<Struct> structs = new ArrayList<Struct>();
	private List<VariableExpression> variables = new ArrayList<VariableExpression>();
	private List<Function> functions = new ArrayList<Function>();
	private List<ParameterExpression> parameters = new ArrayList<ParameterExpression>();
	private String prologue = "main-FUNCTION: NWL" + System.lineSeparator();
	private String epilogue = 	"STP" + System.lineSeparator() 
								+  System.lineSeparator() 
								+ "HEAP: RESGR 200" + System.lineSeparator()
								+ "EINDPR" + System.lineSeparator();
	private Toolbox toolbox = new Toolbox();
	private boolean debug = false;
	private boolean print = false;

	
	@Before
	public void setUp() throws Exception {
		// TODO: N/A
	}

	@After
	public void tearDown() throws Exception {
		// TODO: N/A
	}

	
	@Test
	public void testPrint() {
		reset();
		Statement statement = new PrintStatement(new VariableExpression(true, DataType.INT, 1, "TEST"));
		functions.add(new Function(DataType.VOID, parameters, statement, "main"));
		program = new Program(functions, structs, variables);
		program.compile();
		String expected = "DRU.w null" + System.lineSeparator();
		assertSameString(debug, print, program.getOutput(), prologue + expected + epilogue);
		
		functions.clear();
		statement = new PrintStatement(new PrimitiveExpression(DataType.INT, "300"));
		functions.add(new Function(DataType.VOID, parameters, statement, "main"));
		program = new Program(functions, structs, variables);
		program.compile();
		expected = "DRU.w 300" + System.lineSeparator();
		assertSameString(debug, print, program.getOutput(), prologue + expected + epilogue);
	}
	
	

	/**
	 * Asserts whether 2 given Strings are equal. There are also 2 flags
	 * that will either debug the assertion or print out the first String.
	 * @param	debug
	 * 			If this flag is true, then the assertion will be debugged.
	 * 			The differences between the String will be printed to the 
	 * 			current output stream.
	 * @param 	print
	 * 			If this flag is true, then the first String will be printed.
	 * 			The first String will be printed to the current output stream.
	 * @param 	string1
	 * 			The first String to be used.
	 * @param 	string2
	 * 			The second String to be used.
	 */
	public void assertSameString(boolean debug, boolean print, String string1, String string2) {
		if (print)
			System.out.println(string1);
		if (debug) {
			toolbox.clear();
			toolbox.printStringDifferences(string1, string2);
		}
		assertTrue(string1.equals(string1));
	}
	

	/**
	 * Reset all the variables of this Test Suite
	 * @post	The variables of this Test Suite are all empty.
	 */
	public void reset() {
		structs.clear();
		variables.clear();
		functions.clear();
		parameters.clear();
		toolbox.clear();
	}
	
}

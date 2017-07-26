package language;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import language.expressions.ParameterExpression;
import language.expressions.VariableExpression;
import language.statements.PrintStatement;
import language.statements.Statement;
import model.Program;
import util.Toolbox;

public class TestProgram {

	private Program program;
	private String prologue = "main-FUNCTION: NWL" + System.lineSeparator();
	private String epilogue = 	"STP" + System.lineSeparator() 
								+ "" +  System.lineSeparator() 
								+ "HEAP: RESGR 200" + System.lineSeparator()
								+ "EINDPR" + System.lineSeparator();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testBasic() {
		List<Function> functions = new ArrayList<Function>();
		List<ParameterExpression> parameters = new ArrayList<ParameterExpression>();
		Statement statement = new PrintStatement(new VariableExpression(true, DataType.INT, 1, "TEST"));
		functions.add(new Function(DataType.VOID, parameters, statement, "main"));
		program = new Program(functions, new ArrayList<Struct>(), new ArrayList<VariableExpression>());
		program.compile();
//		System.out.println(program.getOutput());
		new Toolbox().printStringDifferences(program.getOutput(), (prologue + "DRU null" + System.lineSeparator() + epilogue));
		assertTrue(program.getOutput().equals(prologue + ("DRU null" + System.lineSeparator()) + epilogue));
	}

}

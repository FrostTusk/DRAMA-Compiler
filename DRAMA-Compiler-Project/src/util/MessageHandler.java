package util;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A Message Class containing various messages that can be displayed. 
 * @author	Mathijs Hubrechtsen
 */
public class MessageHandler {
	
	/**
	 * The variable containing the version of this DRAMA-Compiler.
	 */
	private String version = "DRAMA-Compiler (v0.1)";
	/**
	 * The variable containing the short license of this DRAMA-Compiler.
	 */
	private String shortLicense = "This project is licensed under the MIT License: Copyright (c) 2017 Mathijs Hubrechtsen";
	/**
	 * The variable containing the extended (MIT) license of this DRAMA-Compiler.
	 */
	private String extendedLicense = "MIT License" + System.lineSeparator() + System.lineSeparator() +
			"Copyright (c) 2017 Mathijs Hubrechtsen" + System.lineSeparator() + System.lineSeparator() +
			"Permission is hereby granted, free of charge, to any person obtaining a copy" + System.lineSeparator() +
			"of this software and associated documentation files (the \"Software\"), to deal" + System.lineSeparator() + 
			"in the Software without restriction, including without limitation the rights" + System.lineSeparator() +
			"to use, copy, modify, merge, publish, distribute, sublicense, and/or sell" + System.lineSeparator() +
			"copies of the Software, and to permit persons to whom the Software is" + System.lineSeparator() +
			"furnished to do so, subject to the following conditions:" + System.lineSeparator() + System.lineSeparator() +
			"The above copyright notice and this permission notice shall be included in all" + System.lineSeparator() +
			"copies or substantial portions of the Software." + System.lineSeparator() + System.lineSeparator() +
			"THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR" + System.lineSeparator() +
			"IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY," + System.lineSeparator() +
			"FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE" + System.lineSeparator() +
			"AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER" + System.lineSeparator() +
			"LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM," + System.lineSeparator() +
			"OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE" + System.lineSeparator() +
			"SOFTWARE.";
	/**
	 * The variable containing the help message of this DRAMA-Compiler.
	 */
	private String helpMessage = "This compiler compiles C code to DRAMA code." + System.lineSeparator() + 
			 "\t\tusage: java -jar DRAMA-Compiler [inputfile] [outputfile]" + System.lineSeparator() +
			"If no inputfile and outputfile are given, the program will simply prompt the user to enter these." + System.lineSeparator() +
			"If a given file is not located in the directory where this program is being run, the full path of the file must be given." + System.lineSeparator() + 
			"The exitcodes of this compiler, 0 and 1 respectively represent whether the compiler exited normally or not.";

	
	/**
	 * Gets the version of this DRAMA-Compiler.
	 */
	@Basic @Raw
	public String getVersion() {
		return version;
	}
	
	/**
	 * Gets the short license of this DRAMA-Compiler.
	 */
	@Basic @Raw
	public String getShortLicense() {
		return shortLicense;
	}
	
	/**
	 * Gets the extended license of this DRAMA-Compiler.
	 */
	@Basic @Raw
	public String getExtendedLicense() {
		return extendedLicense;
	}
	
	/**
	 * Gets the help message of this DRAMA-Compiler.
	 */
	@Basic @Raw
	public String getHelpMessage() {
		StringBuilder sb = new StringBuilder(version);
		sb.append(System.lineSeparator());
		sb.append(shortLicense);
		sb.append(System.lineSeparator());
		sb.append(helpMessage);
		return sb.toString();
	}
	
}

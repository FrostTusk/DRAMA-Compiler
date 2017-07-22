package model;

import java.util.NoSuchElementException;

public interface Compilable {
    
	/**
	 * Compiles this program part by adding the compilation output
	 * to the Program output tracker. A Compilable requires an
	 * association with a Program to work.
	 */
	void compile() throws IllegalArgumentException, NoSuchElementException, NullPointerException;

}

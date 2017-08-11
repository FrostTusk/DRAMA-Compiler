package util;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * An Exception that contains a result.
 * @author Jianing Li, Mathijs Hubrechtsen
 */
public class ResultException extends Throwable {

	/**
	 * Create a new Result Exception with a given result.
	 * @param	result
	 * 			The result of this exception.
	 * @see	implementation
	 */
    public ResultException(int result) {
        this.result = result;
    }
    
    
    /**
	 * Variable registering the version of this exception.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Variable registering the result of this Result Exception
	 */
	private int result;

	
	/**
	 * Get the result of this Result Exception.
	 */
	@Basic
    public int getResult() {
        return result;
    }
	
	
	/**
	 * Set the result of this Result Exception to the given result.
	 * @param 	result
	 * 			The new result of this Result Exception.
	 */
    public void setResult(int result) {
        this.result = result;
    }

}

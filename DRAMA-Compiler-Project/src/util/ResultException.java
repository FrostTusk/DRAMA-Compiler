package util;


public class ResultException extends Throwable {

	/**
	 *
	 * @param value
	 */
    public ResultException(Object value) {
        this.value = value;
    }
    
    
    /**
	 * Variable registering the version of this exception.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Variable registering the value of this Result Exception
	 */
	private Object value;



    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}

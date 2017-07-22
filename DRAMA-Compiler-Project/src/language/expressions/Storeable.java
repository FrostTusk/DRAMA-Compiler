package language.expressions;

/**
 * Interface representing a storeable object. A storeable object
 * can store an expression at a location. For example, variables and
 * parameters are storeables.
 * @author Mathijs Hubrechtsen
 *
 */
public interface Storeable extends Expression {
	
	public String getLocation();
	public void setLocation(String location);
	public String store(Expression expression);
	public String store(String location);
	
}

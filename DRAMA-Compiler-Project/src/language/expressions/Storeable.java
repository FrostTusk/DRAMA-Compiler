package language.expressions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * Interface representing a storeable object. A storeable object
 * can store an expression at a location. For example, variables and
 * parameters are storeables.
 * @author Mathijs Hubrechtsen
 *
 */
public interface Storeable extends Expression {
	
	/**
	 * Get the location of this Storeable.
	 */
	@Basic @Raw
	public String getLocation();
	/**
	 * Set the location of this Storeable to the given location.
	 * @param	location
	 * 			The new location of this Storeable.
	 * @post	new.getLocation() == location
	 */
	@Raw
	public void setLocation(String location);
	/**
	 * Returns a String store command in which the given expression
	 * is stored in this Storeable.
	 * @param	expression
	 * 			The expression to be stored.
	 * @return	Returns a String store command in which the given expression
	 * 			is stored in this Storeable.
	 */
	@Raw
	public String store(Expression expression);
	/**
	 * Returns a String store command in which the given location
	 * is stored in this Storeable.
	 * @param	location
	 * 			The location to be stored.
	 * @return	Returns a String store command in which the given location
	 * 			is stored in this Storeable.
	 */	// TODO: Interpretation.
	@Raw
	public String store(String location);
	
}

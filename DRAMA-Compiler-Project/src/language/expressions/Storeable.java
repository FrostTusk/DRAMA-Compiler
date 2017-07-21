package language.expressions;

public interface Storeable extends Expression {
	
	public String getLocation();
	public void setLocation(String location);
	public String store(Expression expression);
	
}

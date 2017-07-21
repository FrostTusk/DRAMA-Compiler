package language.expressions;

public interface Storeable {
	
	public String getLocation();
	public void setLocation(String location);
	public String store(Expression expression);
}

package language;

import model.Compilable;

public class Struct implements Compilable {
	private String name;
	public String getName() {
		return this.name;
	}

	@Override
	public void compile() {

	}
}

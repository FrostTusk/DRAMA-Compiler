package model;

/**
 * Enumeration of the possible types of Interpretation.
 * @author Jianing Li, Mathijs Hubrechtsen
 */
public enum Interpretation {
    a, d, i, w, none;

    @Override
    public String toString() {
        return (this == none) ? "": "." + this.name();
    }
    
}

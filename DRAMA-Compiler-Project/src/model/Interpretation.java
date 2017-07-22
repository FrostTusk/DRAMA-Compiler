package model;

/**
 * Enumeration of the possible types of Interpretation.
 * @author Jianing Li, Mathijs Hubrechtsen
 */
public enum Interpretation {
    a, d, i, w, none;

    @Override
    public String toString() { // TODO: Super or this?
        return (this == none) ? "": "." + this.name();
    }
    
}

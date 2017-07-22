package model;


public enum Interpretation {
    i, a, d, w, none;

    @Override
    public String toString() {
        if (this == none){
            return "";
        }
        return "." + super.toString();
    }
    
}

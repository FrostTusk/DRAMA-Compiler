package util;


public class ResultCatcher extends Throwable {

    private Object value;

    public ResultCatcher(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


}

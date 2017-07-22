package util;

import java.util.Collection;
import java.util.stream.Stream;

/**
 *         Interface Container is a container which primitive operation could be executed,
 *         the implementation method can decide which datastructure they use.
 */
@SuppressWarnings("unchecked")
public interface Container<T> {
    
	public void add(T value);

    public default void add(T... values) {
        for (T value : values) {
            add(value);
        }
    }

    public void remove(T value);

	public default void remove(T... values) {
        for (T value : values) {
            remove(value);
        }
    }

    public Collection<T> getAll();

    public boolean contains(T value);

    public default Stream<T> stream() {
        Stream.Builder<T> builder = Stream.builder();
        for (T element : this.getAll())
            builder.accept(element);
        return builder.build();
    }

    public int size();
    
}

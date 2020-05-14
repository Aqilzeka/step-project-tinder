package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public interface DAO<T> extends Iterable<T> {

    List<Integer> getAllId();
    List<T> getDatabase();
    Stream<T> stream();
    Integer size();
    T get(int id);

    void read();
    void clear();
    void add(T t);

}

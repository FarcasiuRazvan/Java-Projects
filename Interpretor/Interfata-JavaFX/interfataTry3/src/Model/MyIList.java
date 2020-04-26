package Model;

import java.util.Vector;

public interface MyIList<T> {
    void add(T var);
    T fromIndex(int index);
    int getSize();
    String toString();
    Vector<T> getList();
    void clear();
}

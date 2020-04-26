package Model;

public interface MyIList<T> {
    void add(T var);
    T fromIndex(int index);
    int getSize();
    String toString();
}

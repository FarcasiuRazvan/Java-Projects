package Model;

import java.util.Stack;

public interface MyIStack<T> {
    T pop();
    void push(T v);
    boolean isEmpty();
    String toString();
    T top();
    Stack<T> getStk();
    void clear();
}

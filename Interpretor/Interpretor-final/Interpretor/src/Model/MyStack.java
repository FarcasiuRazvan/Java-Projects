package Model;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stac;

    public MyStack()
    {
        this.stac=new Stack<>();
    }
    public T pop()
    {
        return this.stac.pop();
    }
    public void push(T v)
    {
        this.stac.push(v);
    }

    public Stack<T> getStk()
    {
        return this.stac;
    }

    public boolean isEmpty()
    {
        return this.stac.isEmpty();
    }

    public T top()
    {
        return this.stac.peek();
    }

    public String toString()
    {
        StringBuilder str = new StringBuilder();
        for (T r:stac)
        {
            str.append(r).append("#");
        }
        return str.toString();
    }
}

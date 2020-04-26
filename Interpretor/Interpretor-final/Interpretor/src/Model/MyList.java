package Model;

import java.util.Vector;

public class MyList<T> implements MyIList<T> {
    private Vector<T> list;

    public MyList()
    {
        this.list= new Vector<>();
    }
    public void add(T var)
    {
        this.list.add(var);
    }
    public T fromIndex(int index)
    {
        return this.list.get(index);
    }
    public int getSize()
    {
        return this.list.size();
    }
    public String toString()
    {
        return this.list.toString();
    }


}

package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyHeap implements MyIHeap<Integer,Integer> {
    private HashMap<Integer,Integer> heap;
    private int unique=1;

    public MyHeap()
    {
        this.heap= new HashMap<Integer, Integer>();
    }
    public void add(Integer id,Integer val)
    {
        this.heap.put(id,val);
    }
    public void update(Integer id,Integer val)
    {
        this.heap.put(id,val);
    }
    public boolean isDefined(Integer id)
    {
        return this.heap.containsKey(id);
    }
    public Integer lookForVal(Integer id)
    {
        return this.heap.get(id);
    }
    public Integer lookForId(Integer val)
    {

        for(Map.Entry<Integer,Integer> entry: this.heap.entrySet())
        {
            if(Objects.equals(val,entry.getValue())) return entry.getKey();
        }
        return null;
    }
    public void delete(Integer id)
    {
        this.heap.remove(id);
    }
    public String toString()
    {
        return this.heap.toString();
    }
    public int getUniqueId()
    {
        return this.unique++;
    }
    public HashMap<Integer,Integer> getHeap(){return this.heap;}
    public void setHeap(Map<Integer,Integer> newDict)
    {
        this.heap=new HashMap<>();
        for(Integer key: newDict.keySet()) this.heap.put(key,newDict.get(key));
    }
}

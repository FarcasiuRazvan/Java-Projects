package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyBarrierTable implements MyIBarrierTable<Integer,Pair<Integer, ArrayList<Integer>>> {
    private HashMap<Integer,Pair<Integer, ArrayList<Integer>>> barrier;
    private int unique=1;

    public MyBarrierTable()
    {
        this.barrier= new HashMap<Integer,Pair<Integer, ArrayList<Integer>>>();
    }
    public void add(Integer id,Pair<Integer,ArrayList<Integer>> val)
    {
        this.barrier.put(id,val);
    }
    public void update(Integer id,Pair<Integer,ArrayList<Integer>> val)
    {
        this.barrier.put(id,val);
    }
    public boolean isDefined(Integer id)
    {
        return this.barrier.containsKey(id);
    }
    public Pair<Integer,ArrayList<Integer>> lookForVal(Integer id)
    {
        return this.barrier.get(id);
    }
    public Integer lookForId(Pair<Integer,ArrayList<Integer>> val)
    {

        for(Map.Entry<Integer,Pair<Integer,ArrayList<Integer>>> entry: this.barrier.entrySet())
        {
            if(Objects.equals(val,entry.getValue())) return entry.getKey();
        }
        return null;
    }
    public void delete(Integer id)
    {
        this.barrier.remove(id);
    }
    public String toString()
    {
        return this.barrier.toString();
    }
    public int getUniqueId()
    {
        return this.unique++;
    }
    public HashMap<Integer,Pair<Integer,ArrayList<Integer>>> getBarrierTable(){return this.barrier;}
    public void setBarrierTable(Map<Integer,Pair<Integer,ArrayList<Integer>>> newDict)
    {
        this.barrier=new HashMap<>();
        for(Integer key: newDict.keySet()) this.barrier.put(key,newDict.get(key));
    }

    public void clear(){this.barrier.clear();}
}

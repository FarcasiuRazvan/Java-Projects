package Model;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyDictionary<Key,Value> implements MyIDictionary<Key,Value> {
    private HashMap<Key,Value> dictionary;

    public MyDictionary()
    {
        this.dictionary= new HashMap<Key,Value>();
    }
    public HashMap<Key,Value> getContent(){return this.dictionary;}
    public void add(Key id,Value val)
    {
        this.dictionary.put(id,val);
    }
    public void update(Key id,Value val)
    {
        this.dictionary.put(id,val);
    }
    public void updateValue(Key id,Value val)
    {

        for(Map.Entry<Key,Value> entry: this.dictionary.entrySet())
        {
            if(Objects.equals(id,entry.getKey())) entry.setValue(val);
        }
    }
    public boolean isDefined(Key id)
    {
        return this.dictionary.containsKey(id);
    }
    public Value lookup(Key id)
    {
        return this.dictionary.get(id);
    }
    public String toString()
    {
        return this.dictionary.toString();
    }
    public void setContent(HashMap<Key,Value> dict)
    {
        for(Map.Entry<Key,Value> entry: dict.entrySet())
        {
            this.add(entry.getKey(),entry.getValue());
        }
    }
    @Override
    public MyIDictionary<Key,Value> clone()
    {
        MyIDictionary<Key,Value> Clone=new MyDictionary<>();
        for(Key key : this.dictionary.keySet())
            Clone.add(key,this.dictionary.get(key));

        return Clone;
    }
    @Override
    public Map<Key,Value> toMap()
    {
        return this.dictionary;
    }

    public void clear(){this.dictionary.clear();}
}

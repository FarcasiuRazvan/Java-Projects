package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MyFileTable implements MyIFileTable<Integer, Pair<String, BufferedReader>> {
    private HashMap<Integer, Pair<String, BufferedReader>> fileTable;
    private int unique=0;

    public MyFileTable()
    {
        this.fileTable= new HashMap<Integer, Pair<String, BufferedReader>>();
    }
    public void add(Integer id,Pair<String,BufferedReader> val)
    {
        this.fileTable.put(id,val);
    }
    public void update(Integer id,Pair<String,BufferedReader> val)
    {
        this.fileTable.put(id,val);
    }
    public boolean isDefined(Integer id)
    {
        return this.fileTable.containsKey(id);
    }
    public Pair<String,BufferedReader> lookup(Integer id)
    {
        return this.fileTable.get(id);
    }
    public Integer lookFor(Pair<String,BufferedReader> val)
    {

        for(Map.Entry<Integer,Pair<String,BufferedReader>> entry: this.fileTable.entrySet())
        {
            if(Objects.equals(val,entry.getValue())) return entry.getKey();
        }
        return null;
    }
    public void delete(Integer id)
    {
        this.fileTable.remove(id);
    }
    public Integer lookForFileName(String fileName)
    {

        for(Map.Entry<Integer,Pair<String,BufferedReader>> entry: this.fileTable.entrySet())
        {
            if(Objects.equals(fileName,entry.getValue().getKey())) return entry.getKey();
        }
        return null;
    }
    public String toString()
    {
        return this.fileTable.toString();
    }
    public int uniqueId()
    {
        return this.unique++;
    }

    public void closeAllFiles() throws IOException {

        this.fileTable.forEach((key, value) -> {
            try {
                value.getValue().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

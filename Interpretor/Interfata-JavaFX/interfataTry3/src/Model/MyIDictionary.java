package Model;

import java.util.HashMap;
import java.util.Map;

public interface MyIDictionary<Key,Value>  {

    void add(Key id,Value val);
    void update(Key id,Value val);
    boolean isDefined(Key id);
    Value lookup(Key id);
    String toString();
    void updateValue(Key id,Value val);
    HashMap<Key,Value> getContent();
    void setContent(HashMap<Key,Value> dict);
    MyIDictionary<Key,Value> clone();
    Map<Key,Value> toMap();
    void clear();
}

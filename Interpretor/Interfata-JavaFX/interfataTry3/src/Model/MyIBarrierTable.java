package Model;

import java.util.HashMap;
import java.util.Map;

public interface MyIBarrierTable<Key,Value> {
    void add(Key id,Value val);
    void update(Key id,Value val);
    boolean isDefined(Key id);
    Value lookForVal(Key id);
    Key lookForId(Value val);
    void delete(Key id);
    String toString();
    int getUniqueId();
    HashMap<Key,Value> getBarrierTable();
    void setBarrierTable(Map<Key,Value> newDict);
    void clear();
}

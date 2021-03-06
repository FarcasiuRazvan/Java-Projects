package Model;

import java.io.BufferedReader;
import java.io.IOException;

public interface MyIFileTable<Key,Value>  {

    void add(Key id,Value val);
    void update(Key id,Value val);
    boolean isDefined(Key id);
    Value lookup(Key id);
    String toString();
    int uniqueId();
    Key lookFor(Value val);
    Integer lookForFileName(String fileName);
    void delete(Integer id);
    void closeAllFiles() throws IOException;
}

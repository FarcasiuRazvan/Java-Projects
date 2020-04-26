package Model;

public abstract class Exp {
    abstract int eval(MyIDictionary<String,Integer> tbl,MyIHeap<Integer,Integer> heap) throws MyException;
    public abstract String toString();
}

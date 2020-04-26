package Model;

public class ConstExp extends Exp {
    private int number;

    public ConstExp(int val){this.number=val;}

    public int eval(MyIDictionary<String,Integer> tbl,MyIHeap<Integer,Integer> heap)
    {
        return this.number;
    }

    public String toString()
    {
        return Integer.toString(this.number)+"";
    }
}

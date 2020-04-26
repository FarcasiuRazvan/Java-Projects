package Model;

public class VarExp extends Exp {
    private String id;

    public VarExp(String ids){this.id=ids;}

    public int eval(MyIDictionary<String, Integer> nTable,MyIHeap<Integer,Integer> heap) throws MyException
    {
        if (nTable.isDefined(this.id))
        {
            return nTable.lookup(this.id);
        }
        else throw new MyException("ERROR: NOT FOUND!!!");
    }

    public String toString()
    {
        return "Eval("+this.id+")=LookUp(SymTable,"+this.id+")";
    }
}

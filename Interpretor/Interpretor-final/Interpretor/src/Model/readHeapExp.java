package Model;

public class readHeapExp extends Exp {
    private String VariableName;

    public readHeapExp(String var){this.VariableName=var;}

    public int eval(MyIDictionary<String,Integer> tbl,MyIHeap<Integer,Integer> heap) throws MyException
    {
        if(!tbl.isDefined(this.VariableName)) throw new MyException("The variable doesn't exist !!!");
        int id=tbl.lookup(this.VariableName);
        if(!heap.isDefined(id)) throw new MyException("The address doesn't exist in the heap memory !!!");
        return heap.lookForVal(id);
    }
    public String toString(){return "rH("+this.VariableName+")";}
}

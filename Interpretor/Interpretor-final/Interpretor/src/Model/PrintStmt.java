package Model;

public class PrintStmt implements IStmt {
    private Exp exp;
    public PrintStmt(Exp ex)
    {
        this.exp=ex;
    }
    public String toString()
    {
        return "print("+this.exp.toString()+")";
    }
    public PrgState execute(PrgState state) throws MyException
    {
        int tmp = this.exp.eval(state.getSymTable(),state.getHeap());
        MyIList<Integer> o = state.getOutput();
        o.add(tmp);

        return null;
    }
}

package Model;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt second;
    public CompStmt(IStmt one,IStmt two)
    {
        this.first=one;
        this.second=two;
    }
    public PrgState execute(PrgState state)
    {
        MyIStack<IStmt> stk=state.getStk();
        stk.push(this.second);
        stk.push(this.first);

        return null;
    }
    public String toString()
    {
        return ""+this.first.toString()+""+this.second.toString()+"";
    }
}

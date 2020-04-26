package Model;

public class IfStmt implements IStmt {
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(Exp e, IStmt Then, IStmt Else)
    {
        this.exp=e;
        this.thenS=Then;
        this.elseS=Else;
    }

    public PrgState execute(PrgState state)
    {
        try
        {
            MyIStack<IStmt> execStack = state.getStk();
            int tmp = exp.eval(state.getSymTable(),state.getHeap());
            if (tmp != 0)
                execStack.push(this.thenS);
            else
                execStack.push(this.elseS);
        }
        catch (MyException mess) {
        System.out.println(mess.getMessage());
        }
        return null;
    }

    public String toString()
    {
        return "IF("+this.exp.toString()+")THEN("+this.thenS.toString()+")ELSE("+this.elseS.toString()+")";
    }
}

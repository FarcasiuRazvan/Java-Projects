package Model;

import java.io.IOException;

public class whileStmt implements IStmt {

    private Exp exp;
    private IStmt stmt;

    public whileStmt(Exp ex, IStmt state)
    {
        this.exp=ex;
        this.stmt=state;
    }
    public String toString()
    {
        return "(While("+this.exp.toString()+")"+this.stmt.toString()+")";
    }
    public PrgState execute(PrgState state) throws MyException, IOException
    {
        MyIDictionary<String,Integer> symTbl= state.getSymTable();
        try
        {
            int val=this.exp.eval(symTbl,state.getHeap());
            if(val!=0)
            {
                state.getStk().push(this);
                state.getStk().push(this.stmt);
            }
        } catch (MyException mess) {
            System.out.println(mess.getMessage());
        }
        return null;
    }
}

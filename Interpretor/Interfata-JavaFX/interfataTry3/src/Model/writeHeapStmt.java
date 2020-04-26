package Model;

import java.io.IOException;

public class writeHeapStmt implements IStmt {
    private String var_name;
    private Exp exp;

    public writeHeapStmt(String var,Exp exp1){this.exp=exp1;this.var_name=var;}

    public String toString(){return "wH("+this.var_name+","+this.exp.toString()+")";}
    public PrgState execute(PrgState state) throws MyException, IOException
    {
        if(!state.getSymTable().isDefined(this.var_name)) throw new MyException("The variable doesn't exist !!!");
        int id=state.getSymTable().lookup(this.var_name);
        if(!state.getHeap().isDefined(id)) throw new MyException("The address doesn't exist in the heap memory !!!");
        state.getHeap().update(id,this.exp.eval(state.getSymTable(),state.getHeap()));
        return null;
    }
}

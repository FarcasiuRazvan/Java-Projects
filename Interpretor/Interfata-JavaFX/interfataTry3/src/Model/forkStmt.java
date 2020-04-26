package Model;

import java.io.IOException;

public class forkStmt implements IStmt {
    private IStmt stmt;

    public forkStmt(IStmt stm){this.stmt=stm;}
    public String toString(){return "";}
    public PrgState execute(PrgState state) throws MyException, IOException
    {
        MyIDictionary<String, Integer> symtbl=new MyDictionary<String, Integer>();
        symtbl.setContent(state.getSymTable().getContent());

        return new PrgState(new MyStack<>(),symtbl,state.getOutput(),state.getFileTable(),state.getHeap(),this.stmt,state.getId()*10);
        //de verificat, id-ul trebuie sa fie unic !!!!!
    }
}

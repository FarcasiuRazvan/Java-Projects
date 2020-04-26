package Model;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt {
    private Exp exp_file_id;
    public closeRFile(Exp id)
    {
        this.exp_file_id=id;
    }
    public String toString()
    {
        return "closeRFile("+this.exp_file_id.toString()+")";
    }
    public PrgState execute(PrgState state) throws MyException, IOException {
        int value;
        value=this.exp_file_id.eval(state.getSymTable(),state.getHeap());
        BufferedReader buff;
        buff=state.getFileTable().lookup(value).getValue();
        if(buff==null) throw new MyException("No buff found !!!");

        buff.close();
        state.getFileTable().delete(value);
        return null;
    }
}

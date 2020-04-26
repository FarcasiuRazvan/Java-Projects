package Model;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt {
    private Exp exp_file_id;
    private String var_name;
    public readFile(Exp id,String name)
    {
        this.exp_file_id=id;
        this.var_name=name;
    }
    public String toString()
    {
        return "readFile("+this.exp_file_id.toString()+","+this.var_name+")";
    }
    public PrgState execute(PrgState state) throws MyException, IOException {
        int value,theValue;
        value=this.exp_file_id.eval(state.getSymTable(),state.getHeap());
        BufferedReader buff;
        buff=state.getFileTable().lookup(value).getValue();
        if(buff==null) throw new MyException("No buff found !!!");

        String str=buff.readLine();
        if(str!=null) theValue=Integer.parseInt(str);
        else theValue=0;

        if(!state.getSymTable().isDefined(var_name)) state.getSymTable().add(var_name,theValue);
        else state.getSymTable().update(var_name,theValue);
        return null;
    }
}

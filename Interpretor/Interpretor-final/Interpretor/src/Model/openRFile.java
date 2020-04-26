package Model;

import java.io.*;

public class openRFile implements IStmt {
    private String var_file_id;
    private String filename;
    public openRFile(String id,String fname)
    {
        this.var_file_id=id;
        this.filename=fname;
    }
    public String toString()
    {
        return "openRFile("+this.var_file_id.toString()+","+this.filename+")";
    }
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIStack<IStmt> stk=state.getStk();

        if(state.getFileTable().lookForFileName(this.filename)!=null) throw new MyException("Wrong Filename");

        BufferedReader buff=new BufferedReader(new FileReader(this.filename));
        int id=state.getFileTable().uniqueId();
        Pair<String,BufferedReader> value=new Pair<String,BufferedReader>(this.filename,buff);
        state.getFileTable().add(id,value);

        state.getSymTable().add(this.var_file_id,id);
        return null;
    }

}

package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String,Integer> symTable;
    private MyIList<String> out;
    private MyIFileTable<Integer, Pair<String,BufferedReader>> fileTable;
    private MyIHeap<Integer,Integer> heap;
    private MyIBarrierTable<Integer,Pair<Integer, ArrayList<Integer>>> barrierTable;
    private IStmt originalProgram;
    private int id;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Integer> symtbl, MyIList<String> ot,MyIFileTable<Integer,Pair<String,BufferedReader>> fTable,MyIHeap<Integer,Integer> heap1 ,MyIBarrierTable<Integer,Pair<Integer, ArrayList<Integer>>> barrierTab,IStmt prg, int id1)
    {
        this.exeStack=stk;
        this.symTable=symtbl;
        this.out=ot;
        this.fileTable=fTable;
        this.heap=heap1;
        this.barrierTable=barrierTab;
        this.id=id1;
        this.originalProgram=prg;
        this.exeStack.push(prg);

    }

    public MyIBarrierTable<Integer,Pair<Integer, ArrayList<Integer>>> getBarrierTable(){return this.barrierTable;}
    public MyIStack<IStmt> getStk()
    {
        return this.exeStack;
    }
    public MyIFileTable<Integer,Pair<String,BufferedReader>> getFileTable()
    {
        return this.fileTable;
    }

    public MyIDictionary<String,Integer> getSymTable()
    {
        return this.symTable;
    }

    public MyIList<String> getOutput(){return this.out;}
    public MyIHeap<Integer,Integer> getHeap(){return this.heap;}
    public int getId(){return this.id;}
    public String toString()
    {
        return Integer.toString(this.id)+"@"+this.exeStack.toString()+"@"+ this.symTable.toString()+"@"+this.out.toString()+"@"+this.fileTable.toString()+"@"+this.heap.toString();
    }

    public Boolean isNotCompleted()
    {
        return !this.exeStack.isEmpty();
    }

    public PrgState executeOneStatement() throws MyException
    {
        MyIStack<IStmt> execStack = this.getStk();

        if (!execStack.isEmpty())
        try {
            IStmt crtStmt=execStack.pop();
            return crtStmt.execute(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void reset()
    {
        this.exeStack.clear();
        this.symTable.clear();
        this.out.clear();
        this.fileTable.clear();
        this.heap.clear();
        this.exeStack.push(this.originalProgram);
    }
}

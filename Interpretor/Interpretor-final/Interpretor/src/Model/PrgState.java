package Model;

import java.io.BufferedReader;
import java.io.IOException;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String,Integer> symTable;
    private MyIList<Integer> out;
    private MyIFileTable<Integer, Pair<String,BufferedReader>> fileTable;
    private MyIHeap<Integer,Integer> heap;
    private IStmt originalProgram;
    private int id;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Integer> symtbl, MyIList<Integer> ot,MyIFileTable<Integer,Pair<String,BufferedReader>> fTable,MyIHeap<Integer,Integer> heap1 ,IStmt prg, int id1)
    {
        this.exeStack=stk;
        this.symTable=symtbl;
        this.out=ot;
        this.fileTable=fTable;
        this.heap=heap1;
        this.id=id1;
        this.exeStack.push(prg);
    }

    MyIStack<IStmt> getStk()
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

    MyIList<Integer> getOutput(){return this.out;}
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

        /*System.out.print("ID: ");
        System.out.println(Integer.toString(this.id));
        System.out.print("Execution stack: ");
        System.out.println(execStack.toString());
        System.out.print("Symbol Table: ");
        System.out.println(this.getSymTable().toString());
        System.out.print("Output: ");
        System.out.println(this.getOutput().toString());
        System.out.print("FileTable: ");
        System.out.println(this.getFileTable().toString());
        System.out.print("Heap: ");
        System.out.println(this.getHeap().toString());
        System.out.println("\n ==> \n");
        */
        return null;
    }
}

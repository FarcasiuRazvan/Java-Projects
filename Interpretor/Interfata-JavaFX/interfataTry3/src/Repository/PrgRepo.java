package Repository;

import Model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PrgRepo implements IPrgRepo{
    private ArrayList<PrgState> programStates;
    private String logFilePath;

    public PrgRepo(String logFile)
    {
        this.programStates=new ArrayList<>();
        this.logFilePath=logFile;
    }
    /*
    public PrgState getCrtPrg()
    {
        return this.programStates.get(0);
    }*/

    public void add(PrgState pr)
    {
        this.programStates.add(pr);
    }

    public void logPrgStateExec(PrgState p) throws IOException {

        BufferedWriter buff=new BufferedWriter(new FileWriter(this.logFilePath, true));
        PrintWriter logFile = new PrintWriter(buff);
        String[] fileString=p.toString().split("@");
        String[] id=fileString[0].split("#");
        String[] execStack=fileString[1].split("#");
        String[] symbolTable=fileString[2].split("#");
        String[] Output=fileString[3].split("#");
        String[] fileTable=fileString[4].split("#");
        String[] heap=fileString[5].split("#");

        logFile.print("ID");
        buff.newLine();
        logFile.print(id[0]);
        buff.newLine();

        logFile.print("EXECUTION STACK");
        buff.newLine();
        for(int i=0;i<execStack.length;i++) {
            logFile.print(execStack[i]);
            buff.newLine();
        }

        logFile.print("SYMBOL TABLE");
        buff.newLine();
        for(int i=0;i<symbolTable.length;i++) {
            logFile.print(symbolTable[i]);
            buff.newLine();
        }

        logFile.print("OUTPUT");
        buff.newLine();
        for(int i=0;i<Output.length;i++) {
            logFile.print(Output[i]);
            buff.newLine();
        }

        logFile.print("FILETABLE");
        buff.newLine();
        for(int i=0;i<fileTable.length;i++) {
            logFile.print(fileTable[i]);
            buff.newLine();
        }

        logFile.print("HEAP");
        buff.newLine();
        for(int i=0;i<heap.length;i++) {
            logFile.print(heap[i]);
            buff.newLine();
        }



        buff.newLine();
        buff.newLine();
        buff.newLine();
        logFile.close();

    }

    public ArrayList<PrgState> getPrgList(){return this.programStates;}
    public void setPrgList(ArrayList<PrgState> states){this.programStates=states;}
    public void reset(){for(PrgState prg : programStates) prg.reset();}
    public int getSize(){return this.programStates.size();}
    public String getLogFilePath(){return this.logFilePath;}


}


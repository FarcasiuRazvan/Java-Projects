package Repository;

import Model.PrgState;

import java.io.IOException;
import java.util.ArrayList;

public interface IPrgRepo {
    //PrgState getCrtPrg();
    void add(PrgState pr);
    void logPrgStateExec(PrgState prg) throws IOException;
    ArrayList<PrgState> getPrgList();
    void setPrgList(ArrayList<PrgState> states);
}

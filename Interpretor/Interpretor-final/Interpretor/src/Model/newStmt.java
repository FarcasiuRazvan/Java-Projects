package Model;

import java.io.IOException;

public class newStmt implements IStmt {
    private String variable;
    private Exp exp;

    public newStmt(String var, Exp exp1) {
        this.exp = exp1;
        this.variable = var;
    }

    public String toString() {
        return "new(" + this.variable + "," + this.exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException, IOException {
        if (state.getSymTable().isDefined(this.variable)) {
            int id = state.getHeap().getUniqueId();
            state.getHeap().add(id, this.exp.eval(state.getSymTable(), state.getHeap()));
            state.getSymTable().update(this.variable, id);
        } else {
            int id = state.getHeap().getUniqueId();
            state.getHeap().add(id, this.exp.eval(state.getSymTable(), state.getHeap()));
            state.getSymTable().add(this.variable, id);
        }
        return null;
    }
}

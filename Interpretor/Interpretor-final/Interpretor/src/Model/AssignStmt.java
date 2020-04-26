package Model;

public class AssignStmt implements IStmt {
    private String id;
    private Exp exp;

    public AssignStmt(String ids, Exp ex)
    {
        this.id=ids;
        this.exp=ex;
    }
    public PrgState execute(PrgState state)
    {
        MyIStack<IStmt> stk=state.getStk();
        MyIDictionary<String,Integer> symTbl= state.getSymTable();
        try
        {
            int val=this.exp.eval(symTbl,state.getHeap());
            if (symTbl.isDefined(this.id)) symTbl.update(this.id,val);
            else symTbl.add(this.id,val);
        } catch (MyException mess) {
            System.out.println(mess.getMessage());
        }
        return null;
    }
    public String toString() {
        return this.id + "=" + this.exp.toString();
    }
}

package Model;

public class switchStmt implements IStmt {

    private Exp expInit;
    private Exp exp1;
    private Exp exp2;
    private IStmt case1;
    private IStmt case2;
    private IStmt defaultCase;

    public switchStmt(Exp exInit,Exp ex1,Exp ex2, IStmt cas1, IStmt cas2,IStmt defaulCase)
    {
        this.expInit=exInit;
        this.exp1=ex1;
        this.exp2=ex2;
        this.case1=cas1;
        this.case2=cas2;
        this.defaultCase=defaulCase;
    }

    public PrgState execute(PrgState state)
    {
        try{
            MyIStack<IStmt> execStack = state.getStk();
            IStmt statement=new IfStmt(
                    new BooleanExp(this.expInit,this.exp1,"=="),this.case1,
                    new IfStmt(new BooleanExp(this.expInit,this.exp2,"=="),this.case2,this.defaultCase));
            execStack.push(statement);
        }
        catch (MyException mess) {
        System.out.println(mess.getMessage());
        }
        return null;
    }

    public String toString()
    {
        return "switch("+this.expInit.toString()+") (case "+this.exp1.toString()+": stmt1) (case "+this.exp2.toString()+": stmt2) (default: stmt3)";
    }
}

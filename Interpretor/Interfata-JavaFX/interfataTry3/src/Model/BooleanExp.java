package Model;

public class BooleanExp extends Exp
{
    private Exp e1;
    private Exp e2;
    private String op; //1 stands for +, 2 for -, etc...

    public BooleanExp(Exp one,Exp two, String operation)
    {
        this.e1=one;
        this.e2=two;
        this.op=operation;
    }
    //override
    public int eval(MyIDictionary<String,Integer> tbl,MyIHeap<Integer,Integer> heap)
    {
        try {

            switch (this.op) {
                case "<":
                    if (this.e1.eval(tbl, heap) < this.e2.eval(tbl, heap)) return 1;
                    else return 0;
                case "<=":
                    if (this.e1.eval(tbl, heap) <= this.e2.eval(tbl, heap)) return 1;
                    else return 0;
                case "==":
                    System.out.print(this.e1.eval(tbl,heap));
                    System.out.print(this.e2.eval(tbl,heap));
                    if (this.e1.eval(tbl, heap) == this.e2.eval(tbl, heap)) return 1;
                    else return 0;
                case "!=":
                    if (this.e1.eval(tbl, heap) != this.e2.eval(tbl, heap)) return 1;
                    else return 0;
                case ">":
                    if (this.e1.eval(tbl, heap) > this.e2.eval(tbl, heap)) return 1;
                    else return 0;
                case ">=":
                    if (this.e1.eval(tbl, heap) >= this.e2.eval(tbl, heap)) return 1;
                    else return 0;
            }
        } catch (MyException mess) {
            System.out.println(mess.getMessage());
        }
        throw new MyException("Error: unknown operator!!");
    }

    public String toString()
    {
        if(this.op.equals("<")) return e1+"<"+e2+" ";
        if(this.op.equals("<=")) return e1+"<="+e2+" ";
        if(this.op.equals("==")) return e1+"=="+e2+" ";
        if(this.op.equals("!=")) return e1+"!="+e2+" ";
        if(this.op.equals(">")) return e1+">"+e2+" ";
        if(this.op.equals(">=")) return e1+">="+e2+" ";
        return "";
    }

}

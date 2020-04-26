package View;

import Controller.*;
import Model.*;
import Repository.*;

import java.io.IOException;
import java.util.*;

public class Interpretor {

    public static void main(String[] args) {
        //..........
        // v=2;Print(v)
        //..........
        //Statement
        IStmt statement1 = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new VarExp("v")));


        //..........
        MyIStack stk1 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> dict1 = new MyDictionary<>();
        MyIList list1 = new MyList<Integer>();
        MyIFileTable fileTable1 = new MyFileTable();
        MyIHeap<Integer,Integer> heap1=new MyHeap();
        PrgState prgState1 = new PrgState(stk1, dict1, list1, fileTable1,heap1, statement1,1);
        IPrgRepo repos1 = new PrgRepo("c:\\Users\\Wolf\\Desktop\\report.txt");
        repos1.add(prgState1);
        Controller ctrl1 = new Controller(repos1);

        //..........
        //a=2+3*5;b=a+1;Print(b)
        //..........
        //Statement
        AssignStmt a2 = new AssignStmt("a", new ArithExp(new ConstExp(2), new ArithExp(new ConstExp(3), new ConstExp(5), 3), 1));
        AssignStmt b2 = new AssignStmt("b", new ArithExp(new VarExp("a"), new ConstExp(1), 1));
        PrintStmt v2 = new PrintStmt(new VarExp("b"));
        IStmt statement2 = new CompStmt(new CompStmt(a2, b2), v2);


        //..........
        MyIStack stk2 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> dict2 = new MyDictionary<>();
        MyIList list2 = new MyList<Integer>();
        MyIFileTable fileTable2 = new MyFileTable();
        MyIHeap<Integer,Integer> heap2=new MyHeap();
        PrgState prgState2 = new PrgState(stk2, dict2, list2, fileTable2,heap2, statement2,2);
        IPrgRepo repos2 = new PrgRepo("c:\\Users\\Wolf\\Desktop\\report.txt");
        repos2.add(prgState2);
        Controller ctrl2 = new Controller(repos2);


        //..........
        // a=2-2;(If a Then v=2 Else v=3);Print(v)
        //..........
        // Statement
        AssignStmt a3 = new AssignStmt("a", new ArithExp(new ConstExp(2), new ConstExp(2), 2));
        IfStmt b3 = new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new AssignStmt("v", new ConstExp(3)));
        PrintStmt v3 = new PrintStmt(new VarExp("v"));
        IStmt statement3 = new CompStmt(new CompStmt(a3, b3), v3);


        //..........
        MyIStack stk3 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> dict3 = new MyDictionary<>();
        MyIList list3 = new MyList<Integer>();
        MyIFileTable fileTable3 = new MyFileTable();
        MyIHeap<Integer,Integer> heap3=new MyHeap();
        PrgState prgState3 = new PrgState(stk3, dict3, list3, fileTable3,heap3, statement3,3);
        IPrgRepo repos3 = new PrgRepo("c:\\Users\\Wolf\\Desktop\\report.txt");
        repos3.add(prgState3);
        Controller ctrl3 = new Controller(repos3);
        //..........

        //..........
        // openRFile(var_f,"test.in");
        // readFile(var_f,var_c);print(var_c);
        // (if var_c then readFile(var_f,var_c);print(var_c) else print(0));
        // closeRFile(var_f);
        //..........
        // Statement
        Exp var_f=new VarExp("var_f");
        Exp var_c=new VarExp("var_c");
        openRFile open4=new openRFile("var_f","c:\\Users\\Wolf\\Desktop\\test.in.txt");
        readFile read4=new readFile(var_f,"var_c");
        IStmt readPrint=new CompStmt(read4,new PrintStmt(var_c));
        IStmt if4=new IfStmt(var_c,new CompStmt(read4,new PrintStmt(var_c)),new PrintStmt(new ConstExp(0)));
        closeRFile close4=new closeRFile(var_f);
        IStmt statement4=new CompStmt(new CompStmt(open4,readPrint),new CompStmt(if4,close4));

        //..........
        MyIStack stk4 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> dict4 = new MyDictionary<>();
        MyIList list4 = new MyList<Integer>();
        MyIFileTable fileTable4 = new MyFileTable();
        MyIHeap<Integer,Integer> heap4=new MyHeap();
        PrgState prgState4 = new PrgState(stk4, dict4, list4, fileTable4,heap4, statement4,4);
        IPrgRepo repos4 = new PrgRepo("c:\\Users\\Wolf\\Desktop\\report.txt");
        repos4.add(prgState4);
        Controller ctrl4 = new Controller(repos4);
        //..........

        //..........
        // openRFile(var_f,"test.in");
        // readFile(var_f+2,var_c);print(var_c);
        // (if var_c then readFile(var_f,var_c);print(var_c)
        // else print(0));
        // closeRFile(var_f)
        //..........
        // Statement
        Exp var_f1=new VarExp("var_f1");
        Exp var_c1=new VarExp("var_c1");
        openRFile open5=new openRFile("var_f1","c:\\Users\\Wolf\\Desktop\\test.in.txt");
        readFile read5=new readFile(new ArithExp(var_f1,new ConstExp(2),1),"var_c1");
        IStmt readPrint5=new CompStmt(new readFile(var_f1,"var_c1"),new PrintStmt(var_c1));
        IStmt if5=new IfStmt(var_c1,new CompStmt(read5,new PrintStmt(var_c1)),new PrintStmt(new ConstExp(0)));
        closeRFile close5=new closeRFile(var_f1);
        IStmt statement5=new CompStmt(new CompStmt(open5,readPrint5),new CompStmt(if4,close5));

        //..........
        MyIStack stk5 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> dict5 = new MyDictionary<>();
        MyIList list5 = new MyList<Integer>();
        MyIFileTable fileTable5 = new MyFileTable();
        MyIHeap<Integer,Integer> heap5=new MyHeap();
        PrgState prgState5 = new PrgState(stk5, dict5, list5, fileTable5,heap5, statement5,5);
        IPrgRepo repos5 = new PrgRepo("c:\\Users\\Wolf\\Desktop\\report.txt");
        repos5.add(prgState5);
        Controller ctrl5 = new Controller(repos5);
        //..........

        //..........
        // v=10;
        // new(v,20);
        // new(a,22);
        // print(v)
        //..........
        // Statement
        IStmt v6=new AssignStmt("v",new ConstExp(10));
        IStmt newV6=new newStmt("v",new ConstExp(20));
        IStmt newA6=new newStmt("a",new ConstExp(22));
        IStmt print6=new PrintStmt(new VarExp("v"));
        IStmt statement6=new CompStmt(new CompStmt(v6,newV6),new CompStmt(newA6,print6));

        //..........
        MyIStack stk6 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> dict6 = new MyDictionary<>();
        MyIList list6 = new MyList<Integer>();
        MyIFileTable fileTable6 = new MyFileTable();
        MyIHeap<Integer,Integer> heap6=new MyHeap();
        PrgState prgState6 = new PrgState(stk6, dict6, list6, fileTable6,heap6, statement6,6);
        IPrgRepo repos6 = new PrgRepo("c:\\Users\\Wolf\\Desktop\\report.txt");
        repos6.add(prgState6);
        Controller ctrl6 = new Controller(repos6);
        //..........

        //..........
        // v=10;
        // new(v,20);
        // new(a,22);
        // print(100+rH(v));
        // print(100+rH(a))
        //..........
        // Statement
        IStmt v7=new AssignStmt("v",new ConstExp(10));
        IStmt newV7=new newStmt("v",new ConstExp(20));
        IStmt newA7=new newStmt("a",new ConstExp(22));
        IStmt print7_1=new PrintStmt(new ArithExp(new ConstExp(100),new readHeapExp("v"),1));
        IStmt print7_2=new PrintStmt(new ArithExp(new ConstExp(100),new readHeapExp("a"),1));
        IStmt statement7=new CompStmt(new CompStmt(v7,newV7),new CompStmt(newA7,new CompStmt(print7_1,print7_2)));

        //..........
        MyIStack stk7 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> dict7 = new MyDictionary<>();
        MyIList list7 = new MyList<Integer>();
        MyIFileTable fileTable7 = new MyFileTable();
        MyIHeap<Integer,Integer> heap7=new MyHeap();
        PrgState prgState7 = new PrgState(stk7, dict7, list7, fileTable7,heap7, statement7,7);
        IPrgRepo repos7 = new PrgRepo("c:\\Users\\Wolf\\Desktop\\report.txt");
        repos7.add(prgState7);
        Controller ctrl7 = new Controller(repos7);
        //..........

        //..........
        // v=10;
        // new(v,20);
        // new(a,22);
        // wH(a,30);
        // print(a);
        // print(rH(a))
        //..........
        // Statement
        IStmt v8=new AssignStmt("v",new ConstExp(10));
        IStmt newV8=new newStmt("v",new ConstExp(20));
        IStmt newA8=new newStmt("a",new ConstExp(22));
        IStmt wH8=new writeHeapStmt("a",new ConstExp(30));
        IStmt print8_1=new PrintStmt(new VarExp("a"));
        IStmt print8_2=new PrintStmt(new readHeapExp("a"));
        IStmt statement8=new CompStmt(new CompStmt(v8,newV8),new CompStmt(new CompStmt(newA8,wH8),new CompStmt(print8_1,print8_2)));

        //..........
        MyIStack stk8 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> dict8 = new MyDictionary<>();
        MyIList list8 = new MyList<Integer>();
        MyIFileTable fileTable8 = new MyFileTable();
        MyIHeap<Integer,Integer> heap8=new MyHeap();
        PrgState prgState8 = new PrgState(stk8, dict8, list8, fileTable8,heap8, statement8,8);
        IPrgRepo repos8 = new PrgRepo("c:\\Users\\Wolf\\Desktop\\report.txt");
        repos8.add(prgState8);
        Controller ctrl8 = new Controller(repos8);
        //..........

        //..........
        // v=10;
        // new(v,20);
        // new(a,22);
        // wH(a,30);
        // print(a);
        // print(rH(a));
        // a=0
        //..........
        // Statement
        IStmt v9=new AssignStmt("v",new ConstExp(10));
        IStmt newV9=new newStmt("v",new ConstExp(20));
        IStmt newA9=new newStmt("a",new ConstExp(22));
        IStmt wH9=new writeHeapStmt("a",new ConstExp(30));
        IStmt print9_1=new PrintStmt(new VarExp("a"));
        IStmt print9_2=new PrintStmt(new readHeapExp("a"));
        IStmt a9=new AssignStmt("a",new ConstExp(0));
        IStmt statement9=new CompStmt(new CompStmt(new CompStmt(v9,newV9),newA9),new CompStmt(new CompStmt(wH9,print9_1),new CompStmt(print9_2,a9)));

        //..........
        MyIStack stk9 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> dict9 = new MyDictionary<>();
        MyIList list9 = new MyList<Integer>();
        MyIFileTable fileTable9 = new MyFileTable();
        MyIHeap<Integer,Integer> heap9=new MyHeap();
        PrgState prgState9 = new PrgState(stk9, dict9, list9, fileTable9,heap9, statement9,9);
        IPrgRepo repos9 = new PrgRepo("c:\\Users\\Wolf\\Desktop\\report.txt");
        repos9.add(prgState9);
        Controller ctrl9 = new Controller(repos9);
        //..........


        //..........
        // 10 + (2<6) evaluates to 11
        //..........
        // Statement
        Exp plus10=new ArithExp(new ConstExp(10),new BooleanExp(new ConstExp(2),new ConstExp(6),"<"),1);
        IStmt statement10=new PrintStmt(plus10);

        //..........
        MyIStack stk10 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> dict10 = new MyDictionary<>();
        MyIList list10 = new MyList<Integer>();
        MyIFileTable fileTable10 = new MyFileTable();
        MyIHeap<Integer,Integer> heap10=new MyHeap();
        PrgState prgState10 = new PrgState(stk10, dict10, list10, fileTable10,heap10, statement10,10);
        IPrgRepo repos10 = new PrgRepo("c:\\Users\\Wolf\\Desktop\\report.txt");
        repos10.add(prgState10);
        Controller ctrl10 = new Controller(repos10);
        //..........


        //..........
        // (10+2)<6 evaluates to 0
        //..........
        // Statement
        IStmt statement11=new PrintStmt(new BooleanExp(new ArithExp(new ConstExp(10),new ConstExp(2),1),new ConstExp(6),"<"));
        //..........
        MyIStack stk11 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> dict11 = new MyDictionary<>();
        MyIList list11 = new MyList<Integer>();
        MyIFileTable fileTable11 = new MyFileTable();
        MyIHeap<Integer,Integer> heap11=new MyHeap();
        PrgState prgState11 = new PrgState(stk11, dict11, list11, fileTable11,heap11, statement11,11);
        IPrgRepo repos11 = new PrgRepo("c:\\Users\\Wolf\\Desktop\\report.txt");
        repos11.add(prgState11);
        Controller ctrl11 = new Controller(repos11);
        //..........



        //..........
        // v=6; (while (v-4) print(v);v=v-1);print(v)
        //..........
        // Statement
        Exp v12=new VarExp("v");
        IStmt assign12=new AssignStmt("v",new ConstExp(6));
        IStmt while12=new whileStmt(new BooleanExp(v12,new ConstExp(4),">"),new CompStmt(new PrintStmt(v12),new AssignStmt("v",new ArithExp(v12,new ConstExp(1),2))));
        IStmt statement12=new CompStmt(assign12,new CompStmt(while12,new PrintStmt(v12)));
        //..........
        MyIStack stk12 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> dict12 = new MyDictionary<>();
        MyIList list12 = new MyList<Integer>();
        MyIFileTable fileTable12 = new MyFileTable();
        MyIHeap<Integer,Integer> heap12=new MyHeap();
        PrgState prgState12 = new PrgState(stk12, dict12, list12, fileTable12,heap12, statement12,12);
        IPrgRepo repos12 = new PrgRepo("c:\\Users\\Wolf\\Desktop\\report.txt");
        repos12.add(prgState12);
        Controller ctrl12 = new Controller(repos12);
        //..........


        //..........
        // openRFile(var_f,"test.in");
        // readFile(var_f,var_c);print(var_c);
        // (if var_c then readFile(var_f,var_c);print(var_c) else print(0));
        //..........
        // Statement
        Exp var_f13=new VarExp("var_f13");
        Exp var_c13=new VarExp("var_c13");
        openRFile open13=new openRFile("var_f13","c:\\Users\\Wolf\\Desktop\\test.in.txt");
        readFile read13=new readFile(var_f13,"var_c13");
        IStmt readPrint13=new CompStmt(read13,new PrintStmt(var_c13));
        IStmt if13=new IfStmt(var_c13,new CompStmt(read13,new PrintStmt(var_c13)),new PrintStmt(new ConstExp(0)));
        IStmt statement13=new CompStmt(new CompStmt(open13,readPrint13),if13);

        //..........
        MyIStack stk13 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> dict13 = new MyDictionary<>();
        MyIList list13 = new MyList<Integer>();
        MyIFileTable fileTable13 = new MyFileTable();
        MyIHeap<Integer,Integer> heap13=new MyHeap();
        PrgState prgState13 = new PrgState(stk13, dict13, list13, fileTable13,heap13, statement13,13);
        IPrgRepo repos13 = new PrgRepo("c:\\Users\\Wolf\\Desktop\\report.txt");
        repos13.add(prgState13);
        Controller ctrl13 = new Controller(repos13);
        //..........

        //..........
        // v=10;new(a,22);
        // fork(wH(a,30);v=32;print(v);print(rH(a)));
        // print(v);print(rH(a))
        //..........
        // Statement
        Exp vE14=new VarExp("v");
        IStmt v14=new AssignStmt("v",new ConstExp(10));
        IStmt new14=new newStmt("a",new ConstExp(22));
        IStmt wH14=new writeHeapStmt("a",new ConstExp(30));
        IStmt fork14=new forkStmt(
                new CompStmt(wH14,new CompStmt(
                        new AssignStmt("v",new ConstExp(32)), new CompStmt(
                                new PrintStmt(vE14),new PrintStmt(new readHeapExp("a"))
                )))
        );

        IStmt statement14=new CompStmt(
                v14, new CompStmt(
                        new14, new CompStmt(
                                fork14,new CompStmt(
                                        new PrintStmt(vE14), new PrintStmt(new readHeapExp("a"))
        ))));

        //..........
        MyIStack stk14 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> dict14 = new MyDictionary<>();
        MyIList list14 = new MyList<Integer>();
        MyIFileTable fileTable14 = new MyFileTable();
        MyIHeap<Integer,Integer> heap14=new MyHeap();
        PrgState prgState14 = new PrgState(stk14, dict14, list14, fileTable14,heap14, statement14,14);
        IPrgRepo repos14 = new PrgRepo("c:\\Users\\Wolf\\Desktop\\report.txt");
        repos14.add(prgState14);
        Controller ctrl14 = new Controller(repos14);
        //..........

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",statement1.toString(),ctrl1));
        menu.addCommand(new RunExample("2",statement2.toString(),ctrl2));
        menu.addCommand(new RunExample("3",statement3.toString(),ctrl3));
        menu.addCommand(new RunExample("4",statement4.toString(),ctrl4));
        menu.addCommand(new RunExample("5",statement5.toString(),ctrl5));
        menu.addCommand(new RunExample("6",statement6.toString(),ctrl6));
        menu.addCommand(new RunExample("7",statement7.toString(),ctrl7));
        menu.addCommand(new RunExample("8",statement8.toString(),ctrl8));
        menu.addCommand(new RunExample("9",statement9.toString(),ctrl9));
        menu.addCommand(new RunExample("10",statement10.toString(),ctrl10));
        menu.addCommand(new RunExample("11",statement11.toString(),ctrl11));
        menu.addCommand(new RunExample("12",statement12.toString(),ctrl12));
        menu.addCommand(new RunExample("13",statement13.toString(),ctrl13));
        menu.addCommand(new RunExample("14",statement14.toString(),ctrl14));
        menu.show();
    }
}

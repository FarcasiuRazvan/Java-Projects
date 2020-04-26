package sample;

import Controller.Controller;
import Model.*;
import Repository.*;

import Utils.PrgStateUtil;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static PrgRepo createRepo(IStmt stmt)
    {
        MyIStack stk1 = new MyStack<IStmt>();
        MyIDictionary<String, Integer> dict1 = new MyDictionary<>();
        MyIList list1 = new MyList<Integer>();
        MyIFileTable fileTable1 = new MyFileTable();
        MyIHeap<Integer,Integer> heap1=new MyHeap();
        MyIBarrierTable<Integer,Pair<Integer, ArrayList<Integer>>> barrierTable=new MyBarrierTable();
        PrgState prgState1 = new PrgState(stk1, dict1, list1, fileTable1,heap1,barrierTable, stmt,1);
        PrgRepo repos1 = new PrgRepo("c:\\Users\\Wolf\\Desktop\\report.txt");
        repos1.add(prgState1);
        return repos1;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{



        //..........
        // v=2;Print(v)
        //..........
        //Statement
        IStmt statement1 = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new VarExp("v")));

        //..........
        //a=2+3*5;b=a+1;Print(b)
        //..........
        //Statement
        AssignStmt a2 = new AssignStmt("a", new ArithExp(new ConstExp(2), new ArithExp(new ConstExp(3), new ConstExp(5), 3), 1));
        AssignStmt b2 = new AssignStmt("b", new ArithExp(new VarExp("a"), new ConstExp(1), 1));
        PrintStmt v2 = new PrintStmt(new VarExp("b"));
        IStmt statement2 = new CompStmt(new CompStmt(a2, b2), v2);

        //..........
        // a=2-2;(If a Then v=2 Else v=3);Print(v)
        //..........
        // Statement
        AssignStmt a3 = new AssignStmt("a", new ArithExp(new ConstExp(2), new ConstExp(2), 2));
        IfStmt b3 = new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new AssignStmt("v", new ConstExp(3)));
        PrintStmt v3 = new PrintStmt(new VarExp("v"));
        IStmt statement3 = new CompStmt(new CompStmt(a3, b3), v3);

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
        // 10 + (2<6) evaluates to 11
        //..........
        // Statement
        Exp plus10=new ArithExp(new ConstExp(10),new BooleanExp(new ConstExp(2),new ConstExp(6),"<"),1);
        IStmt statement10=new PrintStmt(plus10);

        //..........
        // (10+2)<6 evaluates to 0
        //..........
        // Statement
        IStmt statement11=new PrintStmt(new BooleanExp(new ArithExp(new ConstExp(10),new ConstExp(2),1),new ConstExp(6),"<"));


        //..........
        // v=6; (while (v-4) print(v);v=v-1);print(v)
        //..........
        // Statement
        Exp v12=new VarExp("v");
        IStmt assign12=new AssignStmt("v",new ConstExp(6));
        IStmt while12=new whileStmt(new BooleanExp(v12,new ConstExp(4),">"),new CompStmt(new PrintStmt(v12),new AssignStmt("v",new ArithExp(v12,new ConstExp(1),2))));
        IStmt statement12=new CompStmt(assign12,new CompStmt(while12,new PrintStmt(v12)));


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

        //...........
        //a=1;b=2;c=5;
        //switch(a*10)
        //(case (b*c) print(a);print(b))
        //(case (10) print(100);print(200))
        //(default print(300));
        //print(300)
        //The final Out should be {1,2,300}

        Exp a15=new VarExp("a");
        Exp b15=new VarExp("b");
        Exp c15=new VarExp("c");
        IStmt assignStmt=new CompStmt(
                new AssignStmt("a",new ConstExp(1)),
                new CompStmt(
                        new AssignStmt("b",new ConstExp(2)),
                        new AssignStmt("c",new ConstExp(5)))
        );
        Exp expInit15=new ArithExp(a15,new ConstExp(10),3);
        Exp exp1_15=new ArithExp(b15,c15,3);
        Exp exp2_15=new ConstExp(10);

        IStmt firstStmt=new CompStmt(new PrintStmt(a15),new PrintStmt(b15));
        IStmt secondStmt=new CompStmt(new PrintStmt(new ConstExp(100)),new PrintStmt(new ConstExp(200)));
        IStmt defaultStmt=new PrintStmt(new ConstExp(300));

        IStmt switch15=new switchStmt(expInit15,exp1_15,exp2_15,firstStmt,secondStmt,defaultStmt);

        IStmt statement15=new CompStmt(assignStmt,new CompStmt(switch15,new PrintStmt(new ConstExp(300))));

        try {
            List<IStmt> menu = new ArrayList<>();
            menu.add(statement1);
            menu.add(statement2);
            menu.add(statement3);
            menu.add(statement4);
            menu.add(statement5);
            menu.add(statement6);
            menu.add(statement7);
            menu.add(statement8);
            menu.add(statement9);
            menu.add(statement10);
            menu.add(statement11);
            menu.add(statement12);
            menu.add(statement13);
            menu.add(statement14);
            menu.add(statement15);

            VBox root = new VBox(5);
            root.getChildren().add(new Label("Please choose a program: "));

            /// create the listview
            ObservableList<IStmt> observableStmtList = FXCollections.observableArrayList(menu);
            ListView<IStmt> programList = new ListView<>(observableStmtList);
            programList.setCellFactory(new Callback<ListView<IStmt>, ListCell<IStmt>>() {
                @Override
                public ListCell<IStmt> call(ListView<IStmt> param) {
                    ListCell<IStmt> listCell = new ListCell<IStmt>() {
                        @Override
                        protected void updateItem(IStmt e, boolean empty) {
                            super.updateItem(e, empty);
                            if (e == null || empty)
                                setText("");
                            else
                                setText(e.toString());
                        }
                    };
                    return listCell;
                }
            });
            root.getChildren().add(programList);

            Scene scene = new Scene(root, 700, 400);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Examples");
            primaryStage.show();

            programList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IStmt>() {
                @Override
                public void changed(ObservableValue<? extends IStmt> observable, IStmt oldValue,
                                    IStmt newValue) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("Menu.fxml"));
                        GridPane root = (GridPane) loader.load();
                        PrgStateUtil prgStateUtil = new PrgStateUtil(
                                createRepo(newValue));
                        Controller ctrl = loader.getController();

                        ctrl.setService(prgStateUtil);
                        prgStateUtil.addObserver(ctrl);

                        Stage dialogStage = new Stage();
                        dialogStage.setTitle("Run example dialog");
                        dialogStage.initModality(Modality.APPLICATION_MODAL);
                        dialogStage.setScene(new Scene(root));
                        dialogStage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


        }catch(Exception e){e.printStackTrace();}


    }


    public static void main(String[] args) {
        launch(args);
    }
}

package Controller;

import Model.Command;

public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        try
        {
            ctr.allStep();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}


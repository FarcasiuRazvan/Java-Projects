package Controller;

import View.Command;

import java.io.IOException;

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
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}


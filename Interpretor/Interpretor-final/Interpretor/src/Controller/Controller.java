package Controller;

import Model.*;
import Repository.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IPrgRepo repo;
    private ExecutorService executor;

    public Controller(IPrgRepo newrepo) {
        this.repo = newrepo;
    }

    private HashMap<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap)
    {
        Map<Integer,Integer> map=heap.entrySet().stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        HashMap<Integer,Integer> hashMap= new HashMap<>();
        for(Integer key : map.keySet())
            hashMap.put(key,map.get(key));
        return hashMap;
    }

    /*
    public void allStep() throws MyException, IOException {
        PrgState prgState = repo.getCrtPrg();
        try {
            while (!prgState.getStk().isEmpty()) {
                executeOneStatement(prgState);
                prgState.getHeap().setHeap(this.conservativeGarbageCollector(prgState.getSymTable().getContent().values(), prgState.getHeap().getHeap()));
                repo.logPrgStateExec();
            }

            System.out.println("-----ProgramStateStart------");
            System.out.println(prgState.toString());
            System.out.println("-----ProgramStateEnd------");
        }
        catch(MyException e){System.out.println("ERROR : all Steps !!!");}
        finally
        {
            prgState.getFileTable().closeAllFiles();
            repo.logPrgStateExec();
        }
    }*/

    void allStep() throws InterruptedException, IOException {
        executor= Executors.newFixedThreadPool(2);

        ArrayList<PrgState> prgList=removeCompletedPrg(this.repo.getPrgList());

        while(prgList.size()>0)
        {
            this.OneStatement(prgList);
            prgList=removeCompletedPrg(prgList);
        }

        executor.shutdownNow();

        ArrayList<PrgState> tmpList=this.repo.getPrgList();
        tmpList.get(0).getFileTable().closeAllFiles();

        repo.setPrgList(prgList);
    }

    private void OneStatement(ArrayList<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg->
        { try {
            prg.getHeap().setHeap(this.conservativeGarbageCollector(prg.getSymTable().getContent().values(), prg.getHeap().getHeap()));
            repo.logPrgStateExec(prg);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        List<Callable<PrgState>> callList=prgList.stream()
                .map((PrgState p)->(Callable<PrgState>)(p::executeOneStatement))
                .collect(Collectors.toList());

        //System.out.println(Integer.toString(callList.size())+"\n");
        List<PrgState> newPrgList=executor.invokeAll(callList).stream()
                .map(future->{
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList());

        prgList.addAll(newPrgList);

        prgList.forEach(prg->
        { try {
            repo.logPrgStateExec(prg);

        } catch (IOException e) {
            e.printStackTrace();
        }
        });

        repo.setPrgList(prgList);
    }

    private ArrayList<PrgState> removeCompletedPrg(ArrayList<PrgState> inPrgList)
    {

        List<PrgState> lst=inPrgList.stream().filter(PrgState::isNotCompleted).collect(Collectors.toList());
        ArrayList<PrgState> result=new ArrayList<>();
        int key;
        for(key=0;key<lst.size();key++) result.add(lst.get(key));
        return result;
    }
}










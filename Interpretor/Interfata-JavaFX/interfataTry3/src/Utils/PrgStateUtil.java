package Utils;

import Model.Pair;
import Model.PrgState;
import Repository.IPrgRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrgStateUtil implements Observable<PrgState> {
    private List<Observer<PrgState>> observers=new ArrayList<>();
    private IPrgRepo repo;

    public PrgStateUtil(IPrgRepo repo) {
        this.repo = repo;
    }

    public IPrgRepo getRepo() {
        return this.repo;
    }

    public List<PrgState> getAll() {
        return new ArrayList<>(this.repo.getPrgList());
    }

    public List<String> getOutList() {
        List<String> mList = new ArrayList<>();
        for(int i = 0; i < this.repo.getPrgList().get(0).getOutput().getSize(); ++ i)
            mList.add(String.valueOf(this.repo.getPrgList().get(0).getOutput().fromIndex(i)));
        return mList;
    }

    public List<Pair<Integer, String>> getFileList() {
        List<Pair<Integer, String>> mList = new ArrayList<>();
        for(Integer x : this.repo.getPrgList().get(0).getFileTable().keys())
            mList.add(new Pair<>(x, this.repo.getPrgList().get(0).getFileTable().lookup(x).getKey()));
        return mList;
    }

    public List<Map.Entry<Integer, Integer>> getHeapList() {
        System.out.println(repo.getPrgList().get(0).getHeap().getHeap().entrySet());
        return new ArrayList<>(repo.getPrgList().get(0).getHeap().getHeap().entrySet());
    }

    public List<Map.Entry<Integer, Integer>> getBarrierList() {
        System.out.println(repo.getPrgList().get(0).get.getHeap().entrySet());
        return new ArrayList<>(repo.getPrgList().get(0).getHeap().getHeap().entrySet());
    }

    @Override
    public void addObserver(Observer<PrgState> o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer<PrgState> o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer<PrgState> o : observers)
            o.update(this);
    }
}

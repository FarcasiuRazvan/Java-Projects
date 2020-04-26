package Model;

public class Pair<T, T1> {
    private T key;
    private T1 value;

    public Pair(T k,T1 val){this.key=k;this.value=val;}
    public T getKey(){return key;}
    public T1 getValue(){return value;}
    public String toString(){return "Pair("+key.toString()+","+value.toString()+")";}

}

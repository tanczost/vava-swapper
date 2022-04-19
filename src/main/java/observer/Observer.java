package observer;


public abstract class Observer {
    protected Subject subject;

    public enum LEVEL{
        severe,
        warning,
        info,
        fine
    }
    public abstract void update(String msg, LEVEL lvl);
}

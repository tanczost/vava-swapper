package observer;

import service.common.LogManager;

public abstract class Subject {

    private Observer observers = null;


    public void attach(Observer observer){
        observers  = observer;
    }

    public void notifyObserver(String msg, LogManager.LEVEL lvl){
        observers.update(msg,lvl);
    }
}

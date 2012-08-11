
package presentationtier;

import java.util.ArrayList;

/**
 * abstraktní třída pro observable objekt
 * @author osman
 */
public abstract class AbstractObservable {

    private ArrayList<MyObserver> listeners = new ArrayList<MyObserver>();

    public AbstractObservable() {
    }

    /**
     * přídá observer mezi pozorovatele
     * @param o
     */
    public void addObserver(MyObserver o){
        listeners.add(o);
    }

    /**
     * odebere observer
     * @param o
     */
    public void removeObserver(MyObserver o){
        listeners.remove(o);
    }

    /**
     * probudí všechny zaregistrované observery
     */
    public void notifyObservers(){
        notifyObservers(null);
    }

    /**
     * probudí všechny zaregistrované observery s argumentem
     * @param arg
     */
    public void notifyObservers(Object arg){
        for (MyObserver observer : listeners) {
            if(observer!=null) observer.update(this, arg);
        }
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier;


/**
 * intrerface pro observer
 * @author osman
 */
public interface MyObserver {
    /**
     * vyvoláno při změně Observeble objektu
     * @param o Observable objekt který vyvolal událost
     * @param arg nejaký argument
     */
    public void update(AbstractObservable o , Object arg);
}

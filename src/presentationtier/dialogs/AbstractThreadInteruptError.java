/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier.dialogs;


/**
 * implementace ThreadInteruptError
 * @author osman
 */
public class AbstractThreadInteruptError implements ThreadInteruptError{

    public synchronized void error(){
        notify();
    }

    public synchronized  void check() {
            try {
                wait();
            } catch (InterruptedException ex) {
                
            }
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationtier.actions;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import presentationtier.AbstractObservable;
import presentationtier.MyObserver;

/**
 * abstraktní třída pro akce
 * @author osman
 */
public abstract class Action extends AbstractAction implements MyObserver {

    public Action(String name) {
        super(name);
    }

    public Action(String name, Icon icon) {
        super(name, icon);
    }

    /**
     * pří změně se vyhodnotí zda je akce aktivní nebo ne
     * @param o
     * @param arg
     */
    public void update(AbstractObservable o, Object arg) {
        setEnabled(isEnabledInState());
    }

    /**
     * zda je akce aktivní v aktuálním stavu
     * @return true je aktivní false není
     */
    abstract boolean isEnabledInState();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier.actions;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import shogi.Globals;
import presentationtier.MyFrame;

/**
 * akce pro vypnutí programu
 * @author osman
 */
public class ExitAction extends Action {

    public static ExitAction instance = new ExitAction();

    public ExitAction() {
        super("Exit");
        Globals.instance.addObserver(this);
    }

    /**
     * vypnutí programu, pokud je hra vytvořená zeptá se zda chcete doopravdy ukončit program
     * @param e
     */
    public void actionPerformed(ActionEvent e) {

        int i=0;

        if(Globals.instance.getFacade().isCreated()) i=JOptionPane.showConfirmDialog(MyFrame.instance, "Chcete ukončit rozehranou hru?", "Exit", JOptionPane.CANCEL_OPTION);
           if(i==0){
                Globals.instance.getFacade().endGame();
                System.exit(0);
           }
    }

    @Override
    boolean isEnabledInState() {
        return true;
    }

    
}

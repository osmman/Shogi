
package presentationtier.actions;

import businesstier.ShogiFacade;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import shogi.Globals;
import presentationtier.MyFrame;

/**
 * akce pro ukončení hry
 * @author osman
 */
public class DisconnectAction extends Action {

    public static DisconnectAction instance = new DisconnectAction();

    private DisconnectAction() {
        super("Konec hry");
        Globals.instance.addObserver(this);
    }

    /**
     * ukončí hru
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
       int i=JOptionPane.showConfirmDialog(MyFrame.instance, "Chcete ukončit hru?", "Konec hry", JOptionPane.CANCEL_OPTION);
       if(i==0) ShogiFacade.getInstance().endGame();
    }

    /**
     *
     * @return true pokud je hra vytvořená, false není vytvořená
     */
    @Override
    boolean isEnabledInState() {
        return ShogiFacade.getInstance().isCreated();
    }

}

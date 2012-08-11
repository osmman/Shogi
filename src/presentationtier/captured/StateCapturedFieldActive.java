
package presentationtier.captured;

import java.awt.Graphics;
import java.util.Observable;
import shogi.Globals;

/**
 * context pro aktivní neoznačené položky
 * @author osman
 */
public class StateCapturedFieldActive implements StateCapturedFiled {

    private static StateCapturedFieldActive instance = new StateCapturedFieldActive();

    public static StateCapturedFiled getInstance(){
        return  instance;
    }

    /**
     * po aktivaci se context položky změní na StateCapturedFieldSelected a v menu se nastaví selected na aktuální typ tokenu
     * pro kterej je určena položka v menu
     * plus se odešle změna na menu
     * @param f
     */
    public void action(CapturedField f) {
        if (Globals.instance.getFacade().onTurn()) {
            CapturedMenu.getActivateMenu().selected = f.getTyp();
            CapturedMenu.getActivateMenu().update(new Observable(){}, f);
            f.setState(StateCapturedFieldSelected.getInstance());
            f.repaint();
        }

    }

    public void drawGrafic(CapturedField f, Graphics g) {
    }

    public void update(CapturedField f, Observable o, Object arg) {
    }

}

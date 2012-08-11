package presentationtier.board;

import core.exceptions.GameException;
import java.util.Observable;
import javax.swing.JOptionPane;
import presentationtier.captured.CapturedMenu;
import presentationtier.captured.StateCapturedFieldSelected;
import shogi.Globals;
import presentationtier.MyFrame;

/**
 * context pro Field dědí z StateFieldHighlight protože má stejné vykreslování
 * pole které má tento context se vyvolá při vybrání tokenu který chceme vrátit do hry, pole přejde do rezimu pro vkládání nových tokenů
 * @author osman
 */
public class StateFieldHighlightCaptured extends StateFieldHighlight {

    private static StateFieldHighlightCaptured instance = new StateFieldHighlightCaptured();

    public static StateField getInstance(){
        return  instance;
    }

    /**
     * akce vložií na hrací desku token zpět do hry, druh tokenu kerý je vybrán je v CapturedMenu.selected a cílové pole na které se položí je f
     * @param f pole na přidá figurka
     */
    @Override
    public void action(Field f) {
        try {
            Globals.instance.getFacade().addTokenToGame(CapturedMenu.getActivateMenu().selected, f.POSITION);
        } catch (GameException ex) {
            JOptionPane.showMessageDialog(MyFrame.instance,ex.getMessage(),"Game error",JOptionPane.WARNING_MESSAGE);
        }
        f.notifyChessboard(f);
    }

    /**
     * context pro Field f se změní pokud je arg instancí StateCapturedFieldSelected nebo Field a změní se na StateFieldNormal
     * @param f pole které ze aktualizuje
     * @param o
     * @param arg
     */
    @Override
    public void update(Field f,Observable o, Object arg) {

        if( arg instanceof StateCapturedFieldSelected || arg instanceof Field){
            f.setState(StateFieldNormal.getInstance());

            
        }

        f.repaint();
    }
}

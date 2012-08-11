package presentationtier.board;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import presentationtier.captured.CapturedField;

/**
 * context pro Field, slouží jako stav pole při jeho vybráním tj. aktivování
 * pole lze vybrat jen když na něm je token patřící hráči na tahu
 * @author osman
 */
public class StateFieldActive implements StateField{

    private static StateFieldActive instance = new StateFieldActive();

    public static StateField getInstance(){
        return  instance;
    }

    /**
     * po vyvolání akce informuje chessboard ze se zmenil Field f a tomuto poli změní context z active na normal (po znovu kliknuti na oznacene pole se toto pole odznaci)
     * @param f pole
     */
    public void action(Field f) {
        f.notifyChessboard(this);
        f.setState(StateFieldNormal.getInstance());

        f.repaint();

    }

    
    public void drawGrafic(Field f,Graphics g) {
        g.setColor (Color.gray);
        g.draw3DRect(0, 0, f.getWidth()-2, f.getHeight()-2,false);
        g.draw3DRect(1, 1, f.getWidth()-4, f.getHeight()-4,false);
        g.draw3DRect(2, 2, f.getWidth()-6, f.getHeight()-6,false);
    }

    /**
     * pokud je v arg instance třídy CapturedField změní se context pro Field f na StateFieldHighlightCaptured v jiném případě na StateFieldNormal
     * @param f pole
     * @param o observable objekt který vyvolal změnu
     * @param arg argument, posí lá se v něm objek u kterého vznikla změna
     */
    public void update(Field f,Observable o, Object arg) {
        
        if(arg instanceof  CapturedField){
            f.setState(StateFieldHighlightCaptured.getInstance());
        }
        else{
            f.setState(StateFieldNormal.getInstance());
        }
        f.repaint();
    }
}

package presentationtier.captured;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

/**
 * context když je položka menu vybraná tj. označená
 * @author osman
 */
public class StateCapturedFieldSelected implements StateCapturedFiled {

    private static StateCapturedFieldSelected instance = new StateCapturedFieldSelected();

    public static StateCapturedFiled getInstance(){
        return  instance;
    }

    /**
     * context se vrátí do normálního stavu StateCapturedFieldActive
     * a odešle se CapturedMenu změna
     * @param f
     */
    public void action(CapturedField f) {
        f.setState(StateCapturedFieldActive.getInstance());
        CapturedMenu.getActivateMenu().update(new Observable(){}, this);
        f.repaint();
    }

    public void drawGrafic(CapturedField f, Graphics g) {
        g.setColor (Color.gray);
        g.draw3DRect(0, 0, f.getWidth()-2, f.getHeight()-2,false);
        g.draw3DRect(1, 1, f.getWidth()-4, f.getHeight()-4,false);
    }

    /**
     * vrátí context do normálního stavu StateCapturedFieldActive
     * @param f
     * @param o
     * @param arg
     */
    public void update(CapturedField f, Observable o, Object arg) {
            f.setState(StateCapturedFieldActive.getInstance());
            f.repaint();
    }

}

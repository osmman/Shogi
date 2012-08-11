/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier.captured;

import java.awt.Graphics;
import java.util.Observable;

/**
 *
 * @author osman
 */
public interface StateCapturedFiled {
    /**
     * akce vyvolaná při kliknutí na položku v menu
     * @param f aktivované políčko
     */
    public void action(CapturedField f);
    /**
     * vykreslí podle kontextu položky v menu
     * @param f políčko které se má vykreslit
     * @param g
     */
    public void drawGrafic(CapturedField f,Graphics g);
    /**
     * update vyvolný obserble objektem
     * @param f políčko které se má updatovat
     * @param o observable který vyvolal událost
     * @param arg argument
     */
    public void update(CapturedField f,Observable o, Object arg);
}

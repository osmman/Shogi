package presentationtier.board;

import java.awt.Graphics;
import java.util.Observable;


/**
 * interface pro context ke třídě Field
 * @author osman
 */
public interface StateField{
    /**
     * akce vyvolaná při kliknutí na políčko
     * @param f aktivované políčko
     */
    public void action(Field f);
    /**
     * vykreslí podle kontextu políčko
     * @param f políčko které se má vykreslit
     * @param g
     */
    public void drawGrafic(Field f,Graphics g);
    /**
     * update vyvolný obserble objektem
     * @param f políčko které se má updatovat
     * @param o observable který vyvolal událost
     * @param arg argument
     */
    public void update(Field f,Observable o, Object arg);
}

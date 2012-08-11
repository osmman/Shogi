/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier.board;

import core.Coordinate;
import core.exceptions.GameException;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Observable;
import javax.swing.JOptionPane;
import presentationtier.captured.CapturedField;
import shogi.Globals;
import presentationtier.MyFrame;

/**
 * context pro Field
 * pole které má tento context znamená kam se může vybraná figurka na poli s contextem Active pohnout, po kliknutí na toto pole se vyvolá akce pro posun
 * @author osman
 */
class StateFieldHighlight implements StateField {

    private static StateFieldHighlight instance = new StateFieldHighlight();

    public static StateField getInstance(){
        return  instance;
    }

    /**
     * po kliknutí na pole se vyvolá akce pro posun tokenu a pokud lze token povýšit hláška zda chcete povýšit token
     * startovní pozice tokenu se bere z Chessboard.selected a cilovou z f.POSOTION
     * @param f pole
     */
    public void action(Field f) {
         try {
            Globals.instance.getFacade().moveToken(Chessboard.instance.selected.token.getPosition(), f.POSITION);
        } catch (GameException ex) {
            try {
                int i = JOptionPane.showConfirmDialog(MyFrame.instance, ex.getMessage(), "Game error", JOptionPane.OK_CANCEL_OPTION);
                if (i == 0) {
                    Globals.instance.getFacade().promoteToken(f.POSITION);
                }
                Globals.instance.getFacade().endTurn();
            } catch (GameException ex1) {
                JOptionPane.showMessageDialog(MyFrame.instance, ex.getMessage(), "Game error", JOptionPane.ERROR_MESSAGE);
            }
        }
        f.notifyChessboard(f);
    }

    public void drawGrafic(Field f,Graphics g) {
        g.setColor(new Color(208, 239, 116));
        g.fillRect(0, 0, f.getWidth(), f.getHeight());

    }

    /**
     * pokud je v arg instance třídy CapturedField změní se context pro Field f na StateFieldHighlightCaptured,
     * pokud v arg je instace collection projede se kolekce a pokud se v ní najde coordinát stejný jako je coordinát Field f.POSITION context se nezmění, v jiném případě se změní na StateFieldNormal
     * @param f pole
     * @param o observable objekt který vyvolal změnu
     * @param arg argument, posí lá se v něm objek u kterého vznikla změna
     */
    public void update(Field f,Observable o, Object arg) {

        if(arg instanceof  CapturedField){

            f.setState(StateFieldHighlightCaptured.getInstance());
            f.repaint();
            return;
        }

        if(arg instanceof Collection){

            Collection<Coordinate> col = (Collection<Coordinate>) arg;

            for (Coordinate coordinate : col) {
                if(f.POSITION.equals(coordinate)){
                    return;
                }
            }
        }

            f.setState(StateFieldNormal.getInstance());

            f.repaint();

    }
}

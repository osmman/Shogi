/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier.actions;

import java.awt.event.ActionEvent;
import java.net.URI;
import shogi.Globals;

/**
 * akce pro herní pravydla
 * @author osman
 */
public class GameRulesAction extends Action {
    public static GameRulesAction instance = new GameRulesAction();

    public GameRulesAction() {
        super("Pravidla hry");
        Globals.instance.addObserver(this);
    }

    /**
     * zapnese prohlížeč na internetové stránce s pravidly
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        try {
            java.awt.Desktop.getDesktop().browse(new URI("http://cs.wikipedia.org/wiki/Shogi"));
        } catch (Exception ex) {           
        }
    }

    @Override
    boolean isEnabledInState() {
        return true;
    }

}

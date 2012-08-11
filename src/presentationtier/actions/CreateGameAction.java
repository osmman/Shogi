/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier.actions;


import businesstier.ShogiFacade;

import java.awt.event.ActionEvent;


import shogi.Globals;
import presentationtier.MyFrame;
import presentationtier.dialogs.CreateNewGame;

/**
 * akce pro vytvoření hry
 * @author osman
 */
public class CreateGameAction extends Action {

    public static CreateGameAction instance = new CreateGameAction();

    private CreateGameAction() {
        super("Nová hra");
        Globals.instance.addObserver(this);
    }

    /**
     * vytvoří novou hru
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        new CreateNewGame(MyFrame.instance);
    }

    /**
     * akce je povolené když není hra vytvořená
     * @return true hra není vytvořená, false hra je vytvořená
     */
    @Override
    boolean isEnabledInState() {
        return !ShogiFacade.getInstance().isCreated();
    }

}

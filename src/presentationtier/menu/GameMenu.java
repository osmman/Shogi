/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier.menu;

import javax.swing.JMenu;
import presentationtier.actions.CreateGameAction;
import presentationtier.actions.DisconnectAction;
import presentationtier.actions.ExitAction;
import presentationtier.actions.JoinGameAction;

/**
 *
 * @author osman
 */
public class GameMenu extends JMenu {

    public GameMenu() {
        super("Hra");

        add(CreateGameAction.instance);
        add(JoinGameAction.instance);
        add(DisconnectAction.instance);
        add(ExitAction.instance);
    }

    


}

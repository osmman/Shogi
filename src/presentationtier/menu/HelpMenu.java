/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier.menu;

import javax.swing.JMenu;
import presentationtier.actions.GameRulesAction;

/**
 *
 * @author osman
 */
public class HelpMenu extends JMenu {

    public HelpMenu() {
        super("Nápověda");
        add(GameRulesAction.instance);
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier.menu;

import javax.swing.JMenuBar;

/**
 *
 * @author osman
 */
public class SchogiMenuBar extends JMenuBar {

    public SchogiMenuBar() {
        add(new GameMenu());
        add(new HelpMenu());
    }

}

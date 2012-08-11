/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.mesage;

import businesstier.ShogiFacade;
import core.GameBoard;
import core.Player;

/**
 * synchronizace hry před jejím zahájením
 * @author osman
 */
public class MsgSync implements Messages {
    private Player p;

    /**
     * synchronizuje se začínající hráč
     * @param p
     */
    public MsgSync(Player p) {
        this.p = p;
    }

    public Player getPlayer() {
        return p;
    }

    /**
     * pokud je Player p ve zprávě Player1 tak se přehodí aktuální hráč pro kolo
     * @param g hrací plocha
     */
    public void execute(GameBoard g) {
        if(getPlayer()==Player.PLAYER1) ShogiFacade.getInstance().getGame().switchPlayer();
    }
    
}

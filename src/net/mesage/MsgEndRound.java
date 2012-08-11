/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.mesage;

import businesstier.ShogiFacade;
import core.GameBoard;

/**
 * ukončení herního kola
 * @author osman
 */
public class MsgEndRound implements Messages  {

    public MsgEndRound() {
        
    }

    @Override
    public String toString() {
        return "End round";
    }

    /**
     * ukončí herní kolo tj přehodí hráče
     * @param g herní deska
     */
    public void execute(GameBoard g) {
        ShogiFacade.getInstance().getGame().switchPlayer();
    }
}

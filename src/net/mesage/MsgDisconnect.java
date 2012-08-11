/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.mesage;

import businesstier.ShogiFacade;
import core.GameBoard;

/**
 * zrušení spojení
 * @author osman
 */
public class MsgDisconnect implements Messages {

    public MsgDisconnect() {
    }

    /**
     * ukončí hru
     * @param g herní deska
     */
    public void execute(GameBoard g) {
        ShogiFacade.getInstance().endGame();
    }
    
}

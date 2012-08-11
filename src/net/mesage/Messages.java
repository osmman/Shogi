/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.mesage;

import core.GameBoard;
import java.io.Serializable;

/**
 * imterface pro zprávy k síťové komunikaci
 * @author osman
 */
public interface Messages extends Serializable {
    /**
     * zpráva se vykoná
     * @param g herní deska
     */
   void execute(GameBoard g);
}

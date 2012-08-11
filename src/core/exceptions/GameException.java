/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core.exceptions;

/**
 * pokud byla vyvolána událost proti pravydlům hry, nebo ukončení hry
 * @author osman
 */
public class GameException extends Exception {

    public GameException(String msg) {
        super(msg);
    }
}

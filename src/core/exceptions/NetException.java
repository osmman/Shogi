/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core.exceptions;

/**
 * při pádu nebo nesprávném chování sítové komunikace
 * @author osman
 */
public class NetException extends Exception {
    public NetException() {
    }

    public NetException(String msg) {
        super(msg);
    }
}

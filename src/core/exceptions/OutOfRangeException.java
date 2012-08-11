/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core.exceptions;

/**
 *
 * @author osman
 */
public class OutOfRangeException extends Exception {

    /**
     * Creates a new instance of <code>OutOfRangeException</code> without detail message.
     */
    public OutOfRangeException() {
    }


    /**
     * Constructs an instance of <code>OutOfRangeException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public OutOfRangeException(String msg) {
        super(msg);
    }
}

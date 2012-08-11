/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core.exceptions;

/**
 * 
 * @author osman
 */
public class IlegalArgumentException extends Exception {

    /**
     * Creates a new instance of <code>IlegalArgument</code> without detail message.
     */
    public IlegalArgumentException() {
    }


    /**
     * Constructs an instance of <code>IlegalArgument</code> with the specified detail message.
     * @param msg the detail message.
     */
    public IlegalArgumentException(String msg) {
        super(msg);
    }
}

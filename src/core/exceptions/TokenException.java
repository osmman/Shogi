package core.exceptions;

import core.Token;

/**
 * exception vyvolává token při manipulaci s tokenem proti jeho pravidlům
 * @author osman
 */
public class TokenException extends Exception {

    private Token t;

    public TokenException(String msg,Token t) {
        super(msg);
        this.t=t;
    }

    public Token getToken(){
        return t;
    }
}

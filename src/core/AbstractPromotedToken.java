
package core;

/**
 * rozšíření abstraktní třídy AbstractToken pro povýšené tokeny a jejich základní specifické vlastnosti
 * @author osman
 */
public abstract class AbstractPromotedToken extends AbstractToken implements Token {

    public AbstractPromotedToken(Player p,Coordinate c) {
        super(p, c);
    }

    @Override
    public boolean isPromoted(){
        return true;
    }

    @Override
    public boolean canPromote(){
        return false;
    }

    public String toString(){
        return "+";
    }

    public Token promote() {
        return null;
    }

    @Override
    public abstract Token degrade();

}

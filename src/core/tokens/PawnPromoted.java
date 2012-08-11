/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core.tokens;

import core.*;


/**
 *
 * @author osman
 */
public class PawnPromoted extends BasicPromoted{

    public PawnPromoted(Player p,Coordinate c) {
        super(p, c);
    }

    @Override
    public String getName() {
        return "Promoted Pawn";
    }

    @Override
    public String toString() {
        if(getPlayer()==Player.PLAYER1) return "p+";
        return "P+";
    }

    @Override
    public Token degrade(){
        return new Pawn(getPlayer(), getPosition());
    }

    public EnumToken getEnumToken() {
        return EnumToken.PawnPromoted;
    }
}

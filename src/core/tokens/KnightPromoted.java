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
public class KnightPromoted extends BasicPromoted{

    public KnightPromoted(Player p,Coordinate c) {
        super(p, c);
    }

    @Override
    public String getName() {
        return "Promoted Knight";
    }

    @Override
    public String toString() {
        if(getPlayer()==Player.PLAYER1) return "n+";
        return "N+";
    }

    @Override
    public Token degrade(){
        return new Knight(getPlayer(), getPosition());
    }

    public EnumToken getEnumToken() {
        return EnumToken.KnightPromoted;
    }


}

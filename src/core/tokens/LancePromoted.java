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
public class LancePromoted extends BasicPromoted{

    public LancePromoted(Player p,Coordinate c) {
        super(p, c);
    }

    @Override
    public String getName() {
        return "Promoted Lancer";
    }

    @Override
    public String toString() {
        if(getPlayer()==Player.PLAYER1) return "l+";
        return "L+";
    }

    @Override
    public Token degrade(){
        return new Lance(getPlayer(), getPosition());
    }

    public EnumToken getEnumToken() {
        return EnumToken.LancePromoted;
    }


}

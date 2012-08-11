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
public class SilverGeneralPromoted extends BasicPromoted{

    public SilverGeneralPromoted(Player p,Coordinate c) {
        super(p, c);
    }

    @Override
    public String getName() {
        return "Promoted Silver General";
    }

    @Override
    public String toString() {
        if(getPlayer()==Player.PLAYER1) return "s+";
        return "S+";
    }

    @Override
    public Token degrade(){
        return new SilverGeneral(getPlayer(), getPosition());
    }

    public EnumToken getEnumToken() {
        return EnumToken.SilverGeneralPromoted;
    }
}

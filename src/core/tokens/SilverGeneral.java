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
public class SilverGeneral extends AbstractToken {

    private static final int[][] MASKA = {
        {0,0,1},
        {0,1,1},
        {0,1,1},
    };

    public SilverGeneral(Player p,Coordinate c) {
        super(p, c);
    }

    @Override
    public String getName() {
        return "Silver General";
    }

    @Override
    public Token promote() {
        if(isMoved()){
            return new SilverGeneralPromoted(getPlayer(), getPosition());
        }

        return null;
    }

    @Override
    protected  int getRule(int x, int y) {
        return MASKA[x][y];
    }

        @Override
    public String toString() {
        if(getPlayer()==Player.PLAYER1) return "s";
        return "S";
    }

     public EnumToken getEnumToken() {
        return EnumToken.SilverGeneral;
    }
}

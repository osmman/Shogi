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
public class BishopPromoted extends AbstractPromotedToken {

    private static final int[][] MASKA = {
        {0,1,1},
        {1,2,2},
        {1,2,2},
    };

    public BishopPromoted(Player p,Coordinate c) {
        super(p, c);
    }

    @Override
    public String getName() {
        return "Promoted Bishop";
    }

    @Override
    protected  int getRule(int x, int y) {
        return MASKA[x][y];
    }

    @Override
    public String toString() {
        if(getPlayer()==Player.PLAYER1) return "b"+super.toString();
        return "B"+super.toString();
    }

    @Override
    public Token degrade(){
        return new Bishop(getPlayer(), getPosition());
    }

    public EnumToken getEnumToken() {
        return EnumToken.BishopPromoted;
    }

}

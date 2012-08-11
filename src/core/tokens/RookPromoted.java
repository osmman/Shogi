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
public class RookPromoted extends AbstractPromotedToken {

    private static final int[][] MASKA = {
        {0,2,2},
        {2,1,1},
        {2,1,1},
    };

    public RookPromoted(Player p,Coordinate c) {
        super(p, c);
    }

    @Override
    public String getName() {
        return "Promoted Rook";
    }

    @Override
    protected  int getRule(int x, int y) {
        return MASKA[x][y];
    }

    @Override
    public String toString() {
        if(getPlayer()==Player.PLAYER1) return "r+";
        return "R+";
    }

    @Override
    public Token degrade(){
        return new Rook(getPlayer(), getPosition());
    }

    public EnumToken getEnumToken() {
        return EnumToken.RookPromoted;
    }
}

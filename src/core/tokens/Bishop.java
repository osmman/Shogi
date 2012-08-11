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
public class Bishop extends AbstractToken {

    private static final int[][] MASKA = {
        {0,0,0},
        {0,2,2},
        {0,2,2},
    };

    public Bishop(Player p,Coordinate c) {
        super(p, c);
    }

    @Override
    public String getName() {
        return "Bishop";
    }

    @Override
    protected  int getRule(int x, int y) {
        return MASKA[x][y];
    }

    @Override
    public Token promote() {
        if(isMoved()){
            return new BishopPromoted(getPlayer(), getPosition());
        }
        return null;
    }

    @Override
    public String toString() {
        if(getPlayer()==Player.PLAYER1) return "b";
        return "B";
    }

    public EnumToken getEnumToken() {
        return EnumToken.Bishop;
    }


}

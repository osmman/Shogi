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
public class Rook extends AbstractToken {

    private static final int[][] MASKA = {
        {0,2,2},
        {2,0,0},
        {2,0,0},
    };

    public Rook(Player p,Coordinate c) {
        super(p, c);
    }


    @Override
    public String getName() {
        return "Rook";
    }

    @Override
    protected  int getRule(int x, int y) {
        return MASKA[x][y];
    }

    @Override
    public Token promote() {
        if(isMoved()){
            return new RookPromoted(getPlayer(), getPosition());
        }
        return null;
    }

    @Override
    public String toString() {
        if(getPlayer()==Player.PLAYER1) return "r";
        return "R";
    }

    public EnumToken getEnumToken() {
        return EnumToken.Rook;
    }
}

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
public class GoldGeneral extends AbstractToken{

    private static final int[][] MASKA = {
        {0,1,1},
        {1,0,1},
        {1,0,1},
    };

    public GoldGeneral(Player p,Coordinate c) {
        super(p, c);
    }

    @Override
    public String getName() {
        return "Gold General";
    }

    @Override
    public boolean canPromote() {
        return false;
    }

    @Override
    public Token promote() {
        return null;
    }

    @Override
    protected  int getRule(int x, int y) {
        return MASKA[x][y];
    }

    @Override
    public String toString() {
        if(getPlayer()==Player.PLAYER1) return "g";
        return "G";
    }

    public EnumToken getEnumToken() {
        return EnumToken.GoldGeneral;
    }

}

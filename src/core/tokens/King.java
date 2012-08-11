/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core.tokens;

import core.*;
import java.util.Collection;

/**
 *
 * @author osman
 */
public class King extends AbstractToken {

    private static final int[][] MASKA = {
        {0,1,1},
        {1,1,1},
        {1,1,1},
    };

    public King(Player p,Coordinate c) {
        super(p, c);
    }

    @Override
    public String getName() {
        return "King";
    }

    @Override
    protected  int getRule(int x, int y) {
        return MASKA[x][y];
    }

    @Override
    public boolean posibilityMove(Coordinate c,GameBoard g) {

        if(!super.posibilityMove(c,g)) return false;
        
        return GameUtils.canKingMoveInCoordinate(this, c, g);
    }

    @Override
    public Token promote() {
        return null;
    }

    @Override
    public boolean canPromote() {
        return false;
    }

    @Override
    public String toString() {
        if(getPlayer()==Player.PLAYER1) return "k";
        return "K";
    }

    public EnumToken getEnumToken() {
        return EnumToken.King;
    }

}

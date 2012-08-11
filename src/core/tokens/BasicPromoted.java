/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core.tokens;

import core.AbstractPromotedToken;
import core.Coordinate;
import core.Player;

/**
 * abstraktní třída se základnímy vlastnostmy většiny tokenu při povýšení
 * @author osman
 */
public abstract class BasicPromoted extends AbstractPromotedToken {

    public BasicPromoted(Player p,Coordinate c) {
        super(p, c);
    }

    private static final int[][] MASKA = {
        {0,1,1},
        {1,0,1},
        {1,0,1},
    };


    @Override
    protected int getRule(int x, int y) {
        return MASKA[x][y];
    }

}

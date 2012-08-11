/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core.tokens;

import core.*;
import core.exceptions.IlegalArgumentException;
import core.exceptions.TokenException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author osman
 */
public class Pawn extends AbstractToken {

    private static final int[][] MASKA = {
        {0,0,1},
        {0,0,0},
        {0,0,0},
    };

    public Pawn(Player p,Coordinate c) {
        super(p, c);
    }


    @Override
    public String getName() {
        return "Pawn";
    }

    @Override
    public Token promote() {
        if(isMoved()){
            return new PawnPromoted(getPlayer(), getPosition());
        }

        return null;
    }

    @Override
    protected  int getRule(int x, int y) {
        return MASKA[x][y];
    }

    @Override
    public String toString() {
        if(getPlayer()==Player.PLAYER1) return "p";
        return "P";
    }

    @Override
    public boolean canBackToGame(Coordinate c,GameBoard g) {
        if(!super.canBackToGame(c,g)) return false;
        if(getPlayer()==Player.PLAYER1){
            if(c.compareTo(Coordinate.getCoordinate(Coordinate.MAX_X, Coordinate.MIN_Y))<=0) return false;
        }else{
            if(c.compareTo(Coordinate.getCoordinate(Coordinate.MIN_X, Coordinate.MAX_Y))>=0) return false;
        }
        try {
            Collection<Token> col = g.vyber(Coordinate.getCoordinate(c.getX(), 0), Coordinate.getCoordinate(c.getX(), 8));
            for (Token token : col) {
                if(token.getPlayer()==getPlayer() && token instanceof Pawn) return false;
            }
        } catch (IlegalArgumentException ex) {
            return false;
        }
        return true;
    }

    public EnumToken getEnumToken() {
        return EnumToken.Pawn;
    }

}

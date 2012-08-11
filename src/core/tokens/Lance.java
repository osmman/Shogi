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
public class Lance extends AbstractToken {

    private static final int[][] MASKA = {
        {0,0,2},
        {0,0,0},
        {0,0,0},
    };

    public Lance(Player p,Coordinate c) {
        super(p, c);
    }


    @Override
    public String getName() {
        return "Lancer";
    }

    @Override
    public Token promote() {
        if(isMoved()){
            return new LancePromoted(getPlayer(), getPosition());
        }

        return null;
    }

    @Override
    protected int getRule(int x, int y) {
        return MASKA[x][y];
    }

        @Override
    public String toString() {
        if(getPlayer()==Player.PLAYER1) return "l";
        return "L";
    }

    /*@Override
    public void setPosition(Coordinate c) throws TokenException{
    if(getPosition()==null){
    if(getPlayer()==EnumPlayer.PLAYER1){
    if(c.compareTo(Coordinate.getCoordinate(Coordinate.MAX_X, Coordinate.MIN_Y))<=0) throw new TokenException("Nelze umístit", this);
    }
    if(getPlayer()==EnumPlayer.PLAYER2){
    if(c.compareTo(Coordinate.getCoordinate(Coordinate.MIN_Y, Coordinate.MAX_Y))>=0) throw new TokenException("Nelze umístit", this);
    }
    }
    super.setPosition(c);
    }*/

    @Override
    public boolean canBackToGame(Coordinate c,GameBoard g) {
        if(!super.canBackToGame(c,g)) return false;
        if(getPlayer()==Player.PLAYER1){
            if(c.compareTo(Coordinate.getCoordinate(Coordinate.MAX_X, Coordinate.MIN_Y))<=0) return false;
        }else{
            if(c.compareTo(Coordinate.getCoordinate(Coordinate.MIN_X, Coordinate.MAX_Y))>=0) return false;
        }
        return true;
    }

    public EnumToken getEnumToken() {
        return EnumToken.Lance;
    }

}

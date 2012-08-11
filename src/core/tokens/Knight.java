/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core.tokens;

import core.*;
import core.exceptions.TokenException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author osman
 */
public class Knight extends AbstractToken{

    public Knight(Player p,Coordinate c) {
        super(p, c);
    }


    @Override
    public String getName() {
        return "Knight";
    }

    @Override
    public Token promote() {
        if(canPromote()){
            return new KnightPromoted(getPlayer(), getPosition());
        }

        return null;
    }

    @Override
    protected  int getRule(int x, int y) {
        return 0;
    }

    @Override
    public boolean posibilityMove(Coordinate c,GameBoard g) {
        if(c==null || g==null) return false;
        int x=c.getX()-getPosition().getX();
        int y=c.getY()-getPosition().getY();
        if(g.getToken(c)!=null){
                if(g.getToken(c).getPlayer()==getPlayer()) return false;
        }
        if (!GameUtils.bezpecnyTah(getPosition(), c, getPlayer(), g)) return false;
        return Math.abs(x)==1 && ((y==-2 && getPlayer().POSITION) || (y==2 && !getPlayer().POSITION)) ;
    }

    @Override
    public String toString() {
        if(getPlayer()==Player.PLAYER1) return "n";
        return "N";
    }

    @Override
    public boolean canBackToGame(Coordinate c,GameBoard g) {
        if(!super.canBackToGame(c,g)) return false;
        if(getPlayer()==Player.PLAYER1){
            if(c.compareTo(Coordinate.getCoordinate(Coordinate.MAX_X, Coordinate.MIN_Y+1))<=0) return false;
        }else{
            if(c.compareTo(Coordinate.getCoordinate(Coordinate.MIN_X, Coordinate.MAX_Y-1))>=0) return false;
        }
        return true;
    }

    @Override
    public Collection<Coordinate> getCoordinateCanMove(GameBoard g) {
        ArrayList<Coordinate> cor=new ArrayList<Coordinate>();

        int x=getPosition().getX();
        int y=getPosition().getY();

        int i=1;
        if(getPlayer().POSITION) i=-1;

        if(posibilityMove(Coordinate.getCoordinate(x+1, y+(2*i)),g)) cor.add(Coordinate.getCoordinate(x+1, y+(2*i)));
        if(posibilityMove(Coordinate.getCoordinate(x-1, y+(2*i)),g)) cor.add(Coordinate.getCoordinate(x-1, y+(2*i)));

        return cor;
    }

    public EnumToken getEnumToken() {
        return EnumToken.Knight;
    }


}

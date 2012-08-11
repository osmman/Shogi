/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.mesage;

import core.Coordinate;
import core.EnumToken;
import core.GameBoard;
import core.Player;

/**
 * vrácení tokenu zpět do hry
 * @author osman
 */
public class MsgPutToken implements Messages  {
    private EnumToken type;
    private Coordinate destination;

    /**
     * transformace coordinátu pro druhou stranu hracího pole
     * @param type typ tokenu
     * @param d pozice
     */
    public MsgPutToken(EnumToken type, Coordinate d) {
        this.type = type;
        this.destination = Coordinate.getCoordinate(Math.abs(Coordinate.MAX_X-d.getX()),Math.abs(Coordinate.MAX_Y-d.getY()));
    }

    public Coordinate getDestination() {
        return destination;
    }

    public EnumToken getType() {
        return type;
    }

    /**
     * vyjme ze zajatých token a vložího na hrací desku
     * @param g hrací deska
     */
    public void execute(GameBoard g) {
       g.put(getDestination(), Player.PLAYER2.removeCapturedToken(getType()));
    }

}

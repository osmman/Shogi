/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.mesage;

import core.Coordinate;
import core.GameBoard;

/**
 * pro povýšení figurky
 * @author osman
 */
public class MsgPromote implements Messages  {

    private Coordinate coordinate;

    /**
     * přetransformuje coordinaty fro opačnou stranu hrací desky
     * @param s
     */
    public MsgPromote(Coordinate s) {
        this.coordinate = Coordinate.getCoordinate(Math.abs(Coordinate.MAX_X-s.getX()), Math.abs(Coordinate.MAX_Y-s.getY()));
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * povýžší token na hrací ploše
     * @param g hrací plocha
     */
    public void execute(GameBoard g) {
        g.promote(getCoordinate());
    }



}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.mesage;

import core.Coordinate;
import core.GameBoard;

/**
 * pohyb figurky
 * @author osman
 */
public class MsgMove implements Messages {

    private Coordinate source;
    private Coordinate destination;

    /**
     * transformuje souřadnice pro opačnou stranu hrací plochy
     * @param s 
     * @param d
     */
    public MsgMove(Coordinate s,Coordinate d) {
        this.source=Coordinate.getCoordinate(Math.abs(Coordinate.MAX_X-s.getX()), Math.abs(Coordinate.MAX_Y-s.getY()));
        this.destination=Coordinate.getCoordinate(Math.abs(Coordinate.MAX_X-d.getX()),Math.abs(Coordinate.MAX_Y-d.getY()));
    }

    @Override
    public String toString() {
        return getSource()+":"+getDestination();
    }

    public Coordinate getSource() {
        return source;
    }

    public Coordinate getDestination() {
        return destination;
    }

    /**
     * posune figurku na herním poli
     * @param g herní pole
     */
    public void execute(GameBoard g) {
        g.move(getSource(), getDestination());
    }



}

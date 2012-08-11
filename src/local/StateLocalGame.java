/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package local;

import businesstier.Game;
import businesstier.StateGame;
import core.Coordinate;
import core.Player;

import core.Token;


/**
 * implementace StateGame pro lokální hru nic kromě aktualizace dat a přepínání hráčů na tahu nic nedělá
 * @author osman
 */
public class StateLocalGame implements StateGame {

    private Game g;

    public StateLocalGame(Game g,Player p) {
        this.g = g;
        if(p==Player.PLAYER2) g.switchPlayer();
    }

    public void move(Coordinate source, Coordinate destination) {
        g.updateData();
    }

    public void promote(Coordinate source) {
        g.updateData();
    }

    public void addToken(Coordinate d, Token t) {
        g.updateData();
    }

    public boolean isEnableTurn() {
        return true;
    }

    public void newGame() {
        g.updateData();
    }

    public void endGame() {
        //g.dataUpdate();
    }

    public void start() {
    }

    public void endTurn() {
        g.switchPlayer();
        g.updateData();
    }



}

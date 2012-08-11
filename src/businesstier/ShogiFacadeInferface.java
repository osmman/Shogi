/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businesstier;

import core.Coordinate;
import core.Player;
import core.EnumToken;
import core.Token;
import core.exceptions.GameException;
import java.util.Collection;
import java.util.Map;
import presentationtier.update;

/**
 * interface pro facade dědí z interfasu update
 * @author osman
 */
public interface ShogiFacadeInferface extends update {


    /**
     * vrátí kolekci kam se může pohnout token
     * @param s výběr tokenu na souřadnicích
     * @return Collection<Coordinate> možnosti pohybu, null na vybraných souřadnicích neleží token
     */
    Collection<Coordinate> getCoordinatesCanMoveToken(Coordinate s);
    /**
     * posune token smísta start na místo finish
     * @param start startivní coordináty kde se nachází token
     * @param finish cílové coordináty
     * @throws GameException
     */
    void moveToken(Coordinate start,Coordinate finish) throws GameException;
    /**
     * zda lze povýšit token na coordinátech
     * @param place coordináty tokenu
     * @return true lze povýšit, false nelze
     */
    boolean canPromoteToken(Coordinate place);
    /**
     * povýší token na coordinátech
     * @param place coordináty tokenu
     * @throws GameException
     */
    void promoteToken(Coordinate place) throws GameException;
    /**
     * vrátí kolekci všech zajatých tokenů určitého hráče
     * @param p hráč
     * @return Collection<Token> kolekce tokenů
     */
    Collection<Token> getCapturedTokens(Player p);
    /**
     * vloží zajatý token zpátky do hry na pozici
     * @param typ typ tokenu
     * @param c poziceumístění
     * @throws GameException
     */
    void addTokenToGame(EnumToken typ,Coordinate c) throws GameException;
    /**
     * vrátí herní mapu
     * @return Map<Coordinate,Token>
     */
    Map<Coordinate,Token> getGameMap();
    /**
     * vrátí aktuální stav hry stav hry podle toho kdo je právě na tahu
     * @return integer <table><tr><td>+-2</td><td>Šachmat</td></tr><td>+-1</td><td>Šach</td></tr><td>0</td><td>Normální stav</td></tr></table> znaménko udává hráče +Player1, -Player2
     */
    int getGameStatus();
    /**
     * ¨vrátí token z pozice c
     * @param c pozice na herní desce
     * @return Token nebo null pokud je pozice prázdná
     */
    Token getToken(Coordinate c);
    /**
     * vrátí aktuálního hráče na tahu
     * @return Player
     */
    Player getOnTurn(); //Player 1 mystni hrac, Player2 vzdaleny hrac
    /**
     * vrátí coordinate podle zadaných souřadnic
     * @param x x souřadnice
     * @param y y souřadnice
     * @return Coordinate
     */
    Coordinate getCoordinate(int x, int y);
    /**
     * ukončí hru
     */
    public void endGame();
    /**
     * ukončí kolo
     * @throws GameException
     */
    public void endTurn() throws GameException;
    /**
     * vrací zda je povolený tah
     * @return true je, false není
     */
    public boolean onTurn();
}

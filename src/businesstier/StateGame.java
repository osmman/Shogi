package businesstier;

import core.Coordinate;
import core.Token;
import core.exceptions.GameException;

/**
 * interface contextu pro třídu Game
 * @author osman
 */
public interface StateGame {

    /**
     * povyš token
     * @param source
     * @throws GameException
     */
    public void promote(Coordinate source) throws GameException;

    /**
     * přidej token do hry
     * @param d
     * @param t
     * @throws GameException
     */
    public void addToken(Coordinate d, Token t) throws GameException;

    /**
     * je povolené aktuální herní kolo
     * @return true je povolene, false není
     */
    public boolean isEnableTurn();

    /**
     * nová hra
     */
    public void newGame();

    /**
     * ukončení hry
     */
    public void endGame();

    /**
     * posun tokenu
     * @param source
     * @param destination
     * @throws GameException
     */
    public void move(Coordinate source, Coordinate destination) throws GameException;

    /**
     * odstartování hry
     */
    public void start();

    /**
     * ukončení herního kola
     * @throws GameException
     */
    public void endTurn() throws GameException;
}

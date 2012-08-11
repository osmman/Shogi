
package businesstier;

import core.Coordinate;
import core.GameBoard;
import core.Player;
import core.Token;
import core.exceptions.GameException;
import java.io.IOException;
import local.StateLocalGame;
import net2.StateNetGame2;
import presentationtier.update;

/**
 * Je to třída která vytváří novou hru a manipujuje s daty pres GameBoard a Player,
 * na to používá dva contexty. Jeden pro lokální hru a druhý pro síťovou hru
 * @author osman
 */
public class Game implements update {

    /**
     * enum pro výběr typu nové hry
     */
    public enum GameType{
        /**
         * lokální hra
         */
        LOCAL,
        /**
         * vytvoří server pro síťovou hru
         */
        NET_CREATE,
        /**
         * připojí se na server
         */
        NET_CONNECT;
    }


    private StateGame context;
    private Player p;

    /**
     *
     * @param typ typ vytvoření hry
     * @param p pokud vytváříme novou hru LOCAL nebo NET_CREATE udáváme který hráč na jeho straně bude začínat
     * @param address adresa serveru
     * @throws IOException nepodařilo se vytvořit hru
     */
    public Game(GameType typ,Player p,String address) throws IOException{
        this.p=Player.PLAYER1;
        
        switch (typ){
            case LOCAL: context=new StateLocalGame(this,p);
                break;
            case NET_CREATE: context=new StateNetGame2(this,p);
                break;
            case NET_CONNECT: context=new StateNetGame2(this,address);
                break;
        }
        newGame();
        start();
    }

    /**
     * vrátí instanci na GameBoard
     * @return GameBoard.getInstance()
     */
    public GameBoard getGameBoard(){
        return GameBoard.getInstance();
    }

    /**
     * posune token na souřadnice
     * @param source od
     * @param destination do
     * @throws GameException
     */
    public void move(Coordinate source,Coordinate destination) throws GameException{
        GameBoard.getInstance().move(source, destination);
        context.move(source, destination);
    }

    /**
     * povýší token 
     * @param source
     * @throws GameException
     */
    public void promote(Coordinate source) throws GameException{
        GameBoard.getInstance().promote(source);
        context.promote(source);
    }

    /**
     * vrátí zajatý token zpět do hry
     * @param d
     * @param t
     * @throws GameException
     */
    public void addToGame(Coordinate d, Token t) throws GameException{
        GameBoard.getInstance().put(d, t);
        context.addToken(d,t);
    }

    /**
     * zda lze toto kolo hrát
     * @return true ano, false ne
     */
    public boolean turnIsEnable(){
        return context.isEnableTurn();
    }

    /**
     * vrátí hráče na tahu
     * @return Player
     */
    public Player getPlayerOnTurn(){
        return p;
    }

    /**
     * vytvoří novou hru
     */
    private void newGame(){
        clearGame();
        GameBoard.newGame();
        context.newGame();
    }

    /**
     * vyčiští datová uložiště
     */
    public void clearGame(){
        GameBoard.reset();
        Player.PLAYER1.clear();
        Player.PLAYER2.clear();
    }

    /**
     * ukončí hru
     */
    public void endGame(){
        context.endGame();
        context=null;
    }

    /**
     * přehodí hráče na tahu
     */
    public void switchPlayer(){
        if(p==Player.PLAYER1) p=Player.PLAYER2;
        else p=Player.PLAYER1;
    }

    public void updateData(){
        ShogiFacade.getInstance().updateData();
    }

    /**
     * vrátí aktuálně používaný context
     * @return StateGame
     */
    public StateGame getContext() {
        return context;
    }

    /**
     * odstartuje hru
     */
    private void start() {
        context.start();
    }

    /**
     * ukončí aktuální herní kolo
     * @throws GameException
     */
    public void endTurn() throws GameException{
        context.endTurn();
    }
}

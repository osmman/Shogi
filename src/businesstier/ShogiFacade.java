/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businesstier;

import businesstier.Game.GameType;
import core.Coordinate;
import core.Player;
import core.EnumToken;
import core.GameBoard;
import core.GameUtils;
import core.Token;
import core.exceptions.GameException;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import shogi.Globals;

/**
 * implementace ShogiFacadeInterface, zajištuje pravydla hry pokud bylo nějaké pravydlo porušeno vyběhne GameException
 * shogi komunikuje přes třídu Game
 * @author osman
 */
public class ShogiFacade implements ShogiFacadeInferface {

    private static ShogiFacade instance;

    /**
     * vrátí instanci
     * @return ShogiFacade
     */
    public static ShogiFacade getInstance(){
        if(instance==null) instance = new ShogiFacade();
        return instance;
    }

    private ShogiFacade() {
    }

    private Game game=null;

    /**
     * vrátí aktuální hru
     * @return Game nebo null pokud hra není vytvořená
     */
    public Game getGame(){
        return game;
    }

    public Player getOnTurn(){
        if(game==null) return null;
        return game.getPlayerOnTurn();
    }

//    @SuppressWarnings("empty-statement")
    public void moveToken(Coordinate start, Coordinate finish) throws GameException {
        if(!onTurn()) throw new GameException("Nejste na tahu!");
        if(Math.abs(getGameStatus())>1) throw new GameException("Konec hry!");
        Token tokenStart=GameBoard.getInstance().getToken(start);
        Token tokenFinish=GameBoard.getInstance().getToken(finish);
        
        
        if(tokenStart==null) throw new GameException("Nemáte vybraný token!");
        if(tokenStart.getPlayer()!=getOnTurn()) throw new GameException("Token patří jinému hráči!");
        if(tokenFinish!=null){
            if(tokenFinish.getPlayer()==tokenStart.getPlayer()) throw new GameException("Obsazené cilové políčko!");
        }
        if(!tokenStart.posibilityMove(finish,GameBoard.getInstance())) throw new GameException("Nelze se přemístit!");
        if(!GameUtils.bezpecnyTah(start, finish, getOnTurn(), GameBoard.getInstance())) throw new GameException("Nebezpecny tah!");

        game.move(start, finish);


        if(GameBoard.getInstance().getToken(finish).canPromote()) throw new GameException("Chcete token povýšit? ");
        endTurn();
    }

    public void promoteToken(Coordinate place) throws GameException{
        if(!onTurn()) throw new GameException("Nejste na tahu!");
        if(Math.abs(getGameStatus())>1) throw new GameException("Konec hry!");
        Token token=GameBoard.getInstance().getToken(place);
        if(token==null) throw new GameException("Nemáte vybraný token!");
        if(token.getPlayer()!=getOnTurn()) throw new GameException("Token patří jinému hráči!");
        if(!token.canPromote()) throw new GameException("Token nelze povíšit!");



        game.promote(place);

    }

    public Collection<Token> getCapturedTokens(Player p) {
        return p.getCapturedTokens();
    }

    public void addTokenToGame(EnumToken typ,Coordinate c) throws GameException {
        if(!onTurn()) throw new GameException("Nejste na tahu!");
        Token token=getOnTurn().removeCapturedToken(typ);
        if(token==null) throw new GameException("Nemáte token!");
        if(!token.canBackToGame(c,GameBoard.getInstance())){
            getOnTurn().capturToken(token);
            throw new GameException("Nelze umístit!");
        }



        game.addToGame(c,token);

        endTurn();
    }

    public int getGameStatus() {

        return GameUtils.state(getOnTurn(), GameBoard.getInstance());
    }

    public void newGame(GameType typ,Player p,String address) throws IOException{
        endGame();
        game=new Game(typ, p, address);
        updateData();
    }

    public void endGame(){   
        if(game==null) return;
        game.endGame();
        game=null;
        updateData();
    }

    public Map<Coordinate, Token> getGameMap() {
        return GameBoard.getInstance().getMap();
    }

    public Token getToken(Coordinate c) {
        return GameBoard.getInstance().getToken(c);
    }

    public Collection<Coordinate> getCoordinatesCanMoveToken(Coordinate s) {
        return getToken(s).getCoordinateCanMove(GameBoard.getInstance());
    }

    public boolean canPromoteToken(Coordinate place) {
        return getToken(place).canPromote();
    }

    public Coordinate getCoordinate(int x, int y) {
        return Coordinate.getCoordinate(x, y);
    }

    public boolean isCreated(){
        if(game==null) return false;
        return true;
    }

    public boolean onTurn(){
        if(game==null) return false;
        return game.turnIsEnable();
    }

    public void updateData(){
        Globals.instance.updateData();
    }

    public void endTurn() throws GameException{
        game.endTurn();
    }

}

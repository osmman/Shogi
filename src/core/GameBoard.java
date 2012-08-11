
package core;

import core.exceptions.IlegalArgumentException;
import core.tokens.King;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * herní deska
 * @author osman
 */
public class GameBoard {

    /**
     * singleton pattern
     */
    private static GameBoard instance=null;

    /**
     * singleton pattern
     */
    public static GameBoard getInstance(){
        if(instance==null) instance=new GameBoard();
        return instance;
    }

    /**
     * reset herní desky
     */
    public static void reset(){
        new GameBoard();
    }

    private GameBoard(){
        instance=this;
    }
    /**
     * vytvoření nové hry základní postavení figure a registrování králů pro hrače
     */
    public static void newGame(){
        reset();
        GameBoard.getInstance().addToken(EnumToken.Lance, Player.PLAYER2, Coordinate.getCoordinate(0, 0));
        GameBoard.getInstance().addToken(EnumToken.Knight, Player.PLAYER2, Coordinate.getCoordinate(1, 0));
        GameBoard.getInstance().addToken(EnumToken.SilverGeneral, Player.PLAYER2, Coordinate.getCoordinate(2, 0));
        GameBoard.getInstance().addToken(EnumToken.GoldGeneral, Player.PLAYER2, Coordinate.getCoordinate(3, 0));
        GameBoard.getInstance().addToken(EnumToken.King, Player.PLAYER2, Coordinate.getCoordinate(4, 0));
        GameBoard.getInstance().addToken(EnumToken.GoldGeneral, Player.PLAYER2, Coordinate.getCoordinate(5, 0));
        GameBoard.getInstance().addToken(EnumToken.SilverGeneral, Player.PLAYER2, Coordinate.getCoordinate(6, 0));
        GameBoard.getInstance().addToken(EnumToken.Knight, Player.PLAYER2, Coordinate.getCoordinate(7, 0));
        GameBoard.getInstance().addToken(EnumToken.Lance, Player.PLAYER2, Coordinate.getCoordinate(8, 0));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER2, Coordinate.getCoordinate(0, 2));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER2, Coordinate.getCoordinate(1, 2));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER2, Coordinate.getCoordinate(2, 2));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER2, Coordinate.getCoordinate(3, 2));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER2, Coordinate.getCoordinate(4, 2));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER2, Coordinate.getCoordinate(5, 2));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER2, Coordinate.getCoordinate(6, 2));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER2, Coordinate.getCoordinate(7, 2));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER2, Coordinate.getCoordinate(8, 2));
        GameBoard.getInstance().addToken(EnumToken.Bishop, Player.PLAYER2, Coordinate.getCoordinate(7, 1));
        GameBoard.getInstance().addToken(EnumToken.Rook, Player.PLAYER2, Coordinate.getCoordinate(1, 1));
        GameBoard.getInstance().addToken(EnumToken.Lance, Player.PLAYER1, Coordinate.getCoordinate(0, 8));
        GameBoard.getInstance().addToken(EnumToken.Knight, Player.PLAYER1, Coordinate.getCoordinate(1, 8));
        GameBoard.getInstance().addToken(EnumToken.SilverGeneral, Player.PLAYER1, Coordinate.getCoordinate(2, 8));
        GameBoard.getInstance().addToken(EnumToken.GoldGeneral, Player.PLAYER1, Coordinate.getCoordinate(3, 8));
        GameBoard.getInstance().addToken(EnumToken.King, Player.PLAYER1, Coordinate.getCoordinate(4, 8));
        GameBoard.getInstance().addToken(EnumToken.GoldGeneral, Player.PLAYER1, Coordinate.getCoordinate(5, 8));
        GameBoard.getInstance().addToken(EnumToken.SilverGeneral, Player.PLAYER1, Coordinate.getCoordinate(6, 8));
        GameBoard.getInstance().addToken(EnumToken.Knight, Player.PLAYER1, Coordinate.getCoordinate(7, 8));
        GameBoard.getInstance().addToken(EnumToken.Lance, Player.PLAYER1, Coordinate.getCoordinate(8, 8));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER1, Coordinate.getCoordinate(0, 6));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER1, Coordinate.getCoordinate(1, 6));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER1, Coordinate.getCoordinate(2, 6));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER1, Coordinate.getCoordinate(3, 6));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER1, Coordinate.getCoordinate(4, 6));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER1, Coordinate.getCoordinate(5, 6));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER1, Coordinate.getCoordinate(6, 6));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER1, Coordinate.getCoordinate(7, 6));
        GameBoard.getInstance().addToken(EnumToken.Pawn, Player.PLAYER1, Coordinate.getCoordinate(8, 6));
        GameBoard.getInstance().addToken(EnumToken.Bishop, Player.PLAYER1, Coordinate.getCoordinate(1, 7));
        GameBoard.getInstance().addToken(EnumToken.Rook, Player.PLAYER1, Coordinate.getCoordinate(7, 7));
        
        Player.PLAYER1.setKing((King)GameBoard.getInstance().getToken(Coordinate.getCoordinate(4, 8)));
        Player.PLAYER2.setKing((King)GameBoard.getInstance().getToken(Coordinate.getCoordinate(4, 0)));
    }

    private Map<Coordinate,Token> board=new TreeMap<Coordinate, Token>();


    /**
     * vrátí token na coordinatech
     * @param c Coordinate
     * @return Token, null na coordinátech nic není
     */
    public Token getToken(Coordinate c){
        if(c==null) return null;
        return board.get(c);
    }

    /**
     * vrátí všechyny coordináty které jsou obsazeny
     * @return Collection<Coordinate>
     */
    public Collection<Coordinate> getCoordinates(){
        return board.keySet();
    }

    /**
     * vrátí ukazatel na Mapu herní desky
     * @return Map<Coordinate,Token>
     */
    public Map<Coordinate,Token> getMap(){
        return board;
    }

    /**
     * vrátí kopii Mapy herní desky
     * @return Map<Coordinate,Token>
     */
    public Map<Coordinate,Token> getCopyMap(){
        return new TreeMap<Coordinate, Token>(board);
    }

    /**
     * Vrátí všechyn tokeny na herní desce
     * @return Collection<Token>
     */
    public Collection<Token> getTokens(){
        return new ArrayList<Token>(getMap().values());
    }

    /**
     * Vrátí všechyn tokeny na herní desce určitého hráče
     * @param player hráč
     * @return Collection<Token>
     */
    public Collection<Token> getTokens(Player player){
        Collection<Token> col=getTokens();
        Collection<Token> data=new ArrayList<Token>();
        for (Token token : col) {
            if(token.getPlayer()==player) data.add(token);
        }
        return data;
    }

    /**
     * odebere a vrátí token z herní desky na coordinátech
     * @param c coordináty
     * @return Token nebo null
     */
    public Token remove(Coordinate c){
        if(c==null) return null;
        return board.remove(c);
    }

    /**
     * vloží na pozici token, pokud původně na pozici byl už token vrátího
     * @param t token nově vložen
     * @param c pozice
     * @return původní token
     */
    public Token switchToken(Token t,Coordinate c){
        Token rem=remove(c);
        if(t!=null){
            remove(t.getPosition());
            put(c,t);
        }
        return rem;
    }

    /**
     * vrátí pokud je na pozici obsazen token
     * @param c pozice
     * @return true ano, false ne
     */
    public boolean isEngage(Coordinate c){
        return board.containsKey(c);
    }

    /**
     * vloží token na herní desku pozice se bere z Tokenu
     * @param t token
     */
    public void put(Token t){
        if(t==null) return;
        put(t.getPosition(), t);
    }

    /**
     * vloží token na herní desku na danou pozici, v Tokenu se změní pozice na novou
     * @param c pozice
     * @param t token
     */
    public void put(Coordinate c,Token t){
        if(board.containsKey(c) || c==null || t==null) return;
            t.setPosition(c);
            board.put(c, t);
    }

    /**
     * vybere tokeny mezi s a f bez začátku a konce
     * @param s source
     * @param f destination
     * @return List
     * @throws IlegalArgumentException
     */
    public List<Token> vyber(Coordinate s,Coordinate f) throws IlegalArgumentException{
        List<Token> vyber=new ArrayList<Token>();
        Iterator<Coordinate> cesta = Coordinate.getCoordinateBetween(s, f).iterator();

        while (cesta.hasNext()) {
            Token a=getToken(cesta.next());
            if(a!=null)vyber.add(a);
        }
        return vyber;
    }

    /**
     * vrátí true pokud je cesta mezi s a f volná
     * @param s source
     * @param f destination
     * @return true je volná, false není
     * @throws IlegalArgumentException
     */
    public boolean volnaCesta(Coordinate s,Coordinate f) throws IlegalArgumentException{
        return vyber(s, f).isEmpty();
    }

    private void addToken(Token t){
        if(t!=null) board.put(t.getPosition(),t);
    }

    private void addToken(EnumToken typ,Player player,Coordinate c){
        addToken(TokenFactory.getToken(typ, player, c));
    }

    /**
     * přemístí token ze s na d, pokud na d byl token přidáho mezi zajaté tokeny
     * @param s source
     * @param d destination
     */
    public void move(Coordinate s, Coordinate d){
        
        Token tokenStart=getToken(s);
        Token tokenFinish=getToken(d);

        remove(s);
        remove(d);
        put(d,tokenStart);

        tokenStart.setMoved();
        if(tokenFinish!=null){
            tokenStart.getPlayer().capturToken(tokenFinish.degrade());
        }

    }

    /**
     * povýší token na souřadnicích
     * @param d destination
     */
    public void promote(Coordinate d){
        Token t=remove(d);
        if(t==null) return;
        put(t.promote());
    }
}

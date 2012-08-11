
package core;

import core.tokens.King;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * dva hráči Player1 a Player2
 * @author osman
 */
public enum Player {
    /**
     * Hráč 1 - začíná na spodní části hracího pole, směr hry nahoru
     */
    PLAYER1(true,"Player 1",Coordinate.getCoordinate(Coordinate.MAX_X, Coordinate.MIN_Y+2)),
    /**
     * Hráč 2 - začíná na horní polovině hracího pole, směr hry dolů
     */
    PLAYER2(false,"Player 2",Coordinate.getCoordinate(Coordinate.MIN_X, Coordinate.MAX_Y-2))
    ;

    /**
     * udává směr hry pro hráče true = nahoru, false= dolů
     */
    public final boolean POSITION;
    /**
     * od jakého coordinatu začíná promote arena
     */
    private final Coordinate PROMOTE_AREA;

    private Token king;
    private String name;
    private List<Token> capturedToken = new ArrayList<Token>();

    /**
     * vrátí kolekci všech zajatých tokenů
     * @return Collection<Token>
     */
    public Collection<Token> getCapturedTokens(){
        return capturedToken;
    }

    /**
     * vloží do kolekce zajatých tokenů další token a vynuluje ho
     * @param t nově zajatý token
     */
    public void capturToken(Token t){
        t.reset();
        t.setPlayer(this);
        capturedToken.add(t);
    }

    /**
     * vyjme z kolekce zajatých tokenů prní který najde typu EnumToken a vrátí ho
     * @param typ
     * @return null v kolekci se žádný token daného typu nenachází jinak vrátí Token
     */
    public Token removeCapturedToken(EnumToken typ){

        for (Token t : capturedToken) {
            if(typ.isInstance(t)){
            capturedToken.remove(t);
            return t;
            }
        }
        return null;
    }

    /**
     * Vrátí jméno hráče
     * @return String
     */
    public String getName(){
        return name;
    }

    /**
     * Nastaví jméno hráče
     * @param n jméno
     */
    public void setName(String n){
        this.name=n;
    }

    /**
     * vynuluje hráče tj. nulování ukazatele na krále a vyčištění kolekce se zajatými tokeny
     */
    public void clear(){
        capturedToken.clear();
        king=null;
    }

    /**
     * nastaví krále
     * @param k král
     */
    public void setKing(King k){
        this.king=k;
    }

    /**
     *
     * @return vrátí krále
     */
    public Token getKing(){
        return this.king;
    }

    /**
     * zda se Coordinate nachází v povyšovací zóně
     * @param c Coordinate
     * @return true ano, false ne
     */
    public boolean inPromotedArea(Coordinate c){
        if(PLAYER1==this) return c.compareTo(PROMOTE_AREA)<=0;
        return c.compareTo(PROMOTE_AREA)>=0;
    }

    @Override
    public String toString() {
        return name;
    }

    private Player(boolean position,String n,Coordinate c){
        this.POSITION=position;
        this.name=n;
        this.PROMOTE_AREA=c;
        
    }

    

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;


import java.util.Collection;

/**
 * Interface pro tokeny
 * @author osman
 */
public interface Token {
    /**
     * Vrácí název tokune
     * @return String Název tokenu
     */
    public String getName();
    /**
     * Vrací boolean hodnotu jestli je možné token povýšit
     * @return boolean true lze povýšit, false nelze
     */
    public boolean canPromote();
    /**
     * Vrací boolean hodnotu jestli je token už povýšen
     * @return boolean true je povýšen, false není
     */
    public boolean isPromoted();
    /**
     * Vrátí povýšenou verzi tokenu
     * @return Token nebo null když už je povýšen
     */
    public Token promote();
    /**
     * Vrátí základní verzi tokenu
     * @return Token 
     */
    public Token degrade();
    /**
     * Nastaví pozici tokenu na souřadnice
     * @param c souřadnice
     */
    public void setPosition(Coordinate c);
    /**
     * Vrací boolean hodtu zda byl token přemístěn
     * @return true token byl posunut ve hre
     */
    public boolean isMoved();
    /**
     * Nastaví token že se s tokenem ve hře pohnulo
     */
    public void setMoved();
    /**
     * vrátí aktuální pozici tokenu
     * @return Coordinate nebo null
     */
    public Coordinate getPosition();
    /**
     * Vrací aktuálního vlastníka tokenu (Hráč)
     * @return Player.PLAYER1, Player.PLAYER2 nebo null
     */
    public Player getPlayer();
    /**
     * Nastaví vlstníka tokenu (Hráč)
     * @param p Player.PLAYER1 nebo Player.PLAYER2
     */
    public void setPlayer(Player p);
    /**
     * resetuje pozici,hráče a zda token byl přemístěn
     */
    public void reset();
    /**
     * <p>Funkce vrací boolean hodnotu jestli token se může přemístit na souřadnici</p>
     * <ul> Několik podmínek pro splnění:
     *    <li>1. token se musí umět pohybovat ve daném směru</li>
     *    <li>2. v cestě mu nesmí stát žádný jiný token</li>
     *    <li>3. pokud je cílové políčko obsazeno lze na něj vstoupit jen v případě že na něm je protivníkův token</li>
     *    <li>4. tah musí být bezpečný (nesmíš po něm dostat šach)</li>
     *</ul>
     * @param c cílové souřadnice přesunu
     * @param g hrací plocha na které se bude testovat
     * @return true lze se přesunout, false nelze
     */
    public boolean posibilityMove(Coordinate c,GameBoard g);
    /**
     * Vrátí kolekcí všech aktuálně možných tahů
     * @param g hrací plocha na které se bude testovat 
     * @return Collection<Coordinate>
     */
    public Collection<Coordinate> getCoordinateCanMove(GameBoard g);
    /**
     * Testuje se zda lze token vrátit zpět do hry na souřadnice
     * @param c souřadnice kam se má token vrátit
     * @param g hrací plocha na které se bude testovat 
     * @return true lze vrátit, false nelze
     */
    public boolean canBackToGame(Coordinate c,GameBoard g);
    /**
     * Vrátí EnumToken proměnou specifikující typ Tokenu
     * @return EnumToken
     */
    public EnumToken getEnumToken();
}

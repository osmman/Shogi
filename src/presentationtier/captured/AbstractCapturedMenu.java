/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier.captured;

import core.EnumToken;
import core.Player;
import core.Token;
import java.util.Collection;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;
import presentationtier.board.Chessboard;
import presentationtier.AbstractObservable;
import shogi.Globals;
import presentationtier.MyObserver;

/**
 * Abstractní třída udržuje zajaté tokeny pro každého hráče zvlášť, menu je observeble informuje všechny herní políčka při nějaké akci vyvolané jiným polem
 * zároven je observer pozoruje jednotlivé políčka zajatých tokenů v menu
 * + udržuje kolekci kde jsou obsaženy všechny CapturedField (políčka menu)
 * Menu jsou vždy dvě, každý hráč ma svoje menu
 * @author osman
 */
public abstract class AbstractCapturedMenu extends Observable implements Observer,MyObserver {

    
    private static AbstractCapturedMenu player1;
    private static AbstractCapturedMenu player2;

    /**
     * vrátí aktivní menu pro aktuální kolo
     * @return AbstractCapturedMenu aktivní povolené akce
     */
    public static AbstractCapturedMenu getActivateMenu(){
        Player p=Globals.instance.getFacade().getOnTurn();
        if(p==Player.PLAYER1) return player1;
        return player2;
    }

    /**
     * vrátí neaktivní menu pro aktuální kolo
     * @return AbstractCapturedMenu neaktivné zakázané akce
     */
    public static AbstractCapturedMenu getDeactivateMenu(){
        Player p=Globals.instance.getFacade().getOnTurn();
        if(p!=Player.PLAYER1) return player1;
        return player2;
    }

    private Player player;
    /**
     * vybraný druh zajatého tokenu pro případné vrácení ho do hry
     */
    public EnumToken selected;

    private Map<EnumToken,CapturedField> collection = new TreeMap<EnumToken, CapturedField>();

    /**
     * vytvoří menu pro hráče a registruje se jako observer pro Globals a zareistruje observer Cheassboard
     * @param p pro jakého hráče bude určeno menu
     */
    public AbstractCapturedMenu(Player p) {
        if(p==Player.PLAYER1) player1=this;
        else player2=this;

        player=p;

        addObserver(Chessboard.instance);
        Globals.instance.addObserver(this);
    }

    /**
     * odebere z kolekce CapturedFiled podle druhu tokenu a odebereho jej i z observer objektů
     * @param c druh okenu
     */
    protected void remove(EnumToken c){
        deleteObserver(collection.remove(c));
    }

    /**
     * vloží do kolekce CapturedField s klíčem EnumToken a přidá jej jako observer
     * @param c klíč k f
     * @param f položka v menu
     */
    protected void put(EnumToken c,CapturedField f){
        collection.put(c, f);
        
        addObserver(f);
    }

    /**
     * vrátí položku v menu podle klíče EnumToken
     * @param c klíč
     * @return CapturedField položka v menu
     */
    protected CapturedField getField(EnumToken c){
        return collection.get(c);
    }

    /**
     * vrátí všechny položky v menu
     * @return Collection<CapturedField>
     */
    protected Collection<CapturedField> getFields(){
        return collection.values();
    }

    /**
     * vrátí pro kterého hráče je menu určené
     * @return Player
     */
    protected Player getPlayer(){
        return player;
    }

    /**
     * vyčístí kolekci kde jsou uložené položky menu
     * i odstarní všechny observers + znovu zaregistruje observer Chessboard
     */
    protected void clear(){
        collection.clear();
        deleteObservers();
        addObserver(Chessboard.instance);
    }

    /**
     * vrátí kolekci všech zajatých tokenů které patří hráči pro ketrého je menu vytvořené
     * @return Collection<Token>
     */
    protected Collection<Token> getCapturedTokens(){
        return Globals.instance.getFacade().getCapturedTokens(player);
    }

    /**
     * znovu načtení dat v menu
     * menu se vyčistí pomocí clear() a pak se znavu nahrajou data
     */
    private void updateData(){
        clear();

        StateCapturedFiled context;
            if(this==getActivateMenu())context = StateCapturedFieldActive.getInstance();
        else context=StateCapturedFieldDeactive.getInstance();

        Collection<Token> col = getCapturedTokens();

        for (Token token : col) {
            CapturedField f=getField(token.getEnumToken());

            if(f==null){
                put(token.getEnumToken(),new CapturedField(token.getEnumToken(), context, getPlayer()));
            }
            else f.increment();
        }
        reload();
    }

    /**
     * odposlouchávání položek menu při změně se všem observerum odešle změna s arg
     * @param o observable objekt
     * @param arg argument
     */
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
    }

    /**
     * odposlouchávání Globals při změně se znovu načtou data
     * @param o
     * @param arg
     */
    public void update(AbstractObservable o, Object arg) {
        updateData();
    }

    protected abstract void reload();


}

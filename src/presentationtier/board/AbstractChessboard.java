package presentationtier.board;

import core.Coordinate;
import java.util.Collection;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;
import presentationtier.captured.CapturedField;
import presentationtier.captured.StateCapturedFieldSelected;
import presentationtier.AbstractObservable;
import shogi.Globals;
import presentationtier.MyObserver;
import presentationtier.update;

/**
 * Abstractní třída pro uchovávaní herních polí na herní desce, deska je observeble informuje všechny herní políčka při nějaké akci vyvolané jiným polem
 * zároven je observer pozoruje jednotlivé políčka
 * + udržuje kolekci kde jsou obsaženy všechny Field (políčka herní desky)
 * @author osman
 */
public abstract class AbstractChessboard extends Observable implements Observer,MyObserver,update {

    private Map<Coordinate,Field> collection = new TreeMap<Coordinate, Field>();

    /**
     * registrace pro pozorování Globals
     */
    public AbstractChessboard() {
        Globals.instance.addObserver(this);
    }

    /**
     * po zadání souřadnic vrátí Coordinate
     * @param x x souřadnice
     * @param y y souřadnice
     * @return Coordinate 
     */
    protected Coordinate getCoordinate(int x, int y){
        return Globals.instance.getFacade().getCoordinate(x, y);
    }

    /**
     * odebere observer ze svého sernamu
     * @param c
     */
    protected void remove(Coordinate c){
        deleteObserver(collection.remove(c));
    }

    /**
     * přídá Field do seznamu
     * @param c Coordináty
     * @param f herní políčko
     */
    protected void put(Coordinate c,Field f){
        collection.put(c, f);
        addObserver(f);
    }

    /**
     * vrátí herní políčko podle jeho coordinátů
     * @param c coordináty
     * @return Field herní políčko
     */
    protected Field getField(Coordinate c){
        return collection.get(c);
    }

    /**
     * vrátí kolekci všech herních políček
     * @return Collection<Field>
     */
    protected Collection<Field> getFields(){
        return collection.values();
    }
/*
    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }
*/

    public void updateData() {
        Collection<Field> col=collection.values();
        for (Field field : col) {
            field.updateData();
        }
    }

    /**
     * akce která se vyvolá při změně observable objektů
     * akci vyvolává CapturedFiled objekt
     * rozešle všem svým observrům (Field) změnu s argumentem arg
     * @param o
     * @param arg objekt který událost vyvolal
     */
    public void update(Observable o, Object arg) {

        if (arg instanceof CapturedField || arg instanceof StateCapturedFieldSelected) {
            setChanged();
            notifyObservers(arg);
        }
    }

    /**
     * akce která se vyvolá při změně observable objektů
     * akci vyvolává Field objekt
     * rozešle všem svým observrům (Field) změnu s argumentem arg
     * @param f jaká Field vyvolala update
     * @param arg argument
     */
    public void update(Field f,Object arg){
        setChanged();
        notifyObservers(arg);
    }

    /**
     * akce která se vyvolá při změně observable objektů
     * akci vyvolává Globals objekt
     * updejtuje data
     * @param o observable který vyvolal změnu
     * @param arg argument
     */
    public void update(AbstractObservable o, Object arg) {
        updateData();
    }


}

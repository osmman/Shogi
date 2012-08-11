package shogi;

import presentationtier.AbstractObservable;
import presentationtier.MyFrame;
import businesstier.ShogiFacade;
import javax.swing.JOptionPane;
import presentationtier.dialogs.ThreadDialogs;
import presentationtier.update;

/**
 * Globální přístup v aplikaci přístup k facade  k dialogs a hlavně je potomkem třídy AbstractObserver a implementuje update
 * to znamená že jakákoliv změna v jádru aplikace probublá až sem a od tud se dál šíří na všechny zaregistrované observery
 * @author osman
 */
public class Globals extends AbstractObservable implements update {
    public static Globals instance;
    private ShogiFacade facade;
    private ThreadDialogs dialogs;




    public Globals() {
        instance=this;
        facade=ShogiFacade.getInstance();
        dialogs=ThreadDialogs.getInstance();
    }

    public ShogiFacade getFacade(){
        return facade;
    }

    public ThreadDialogs getThreadDialogs(){
        return dialogs;
    }

    /**
     * dialogy pro zobrazení stavu hry (šach a šachmat) a následovně ukončení hry pokud je mat
     */
    public void dialogGameState(){

        if(getFacade().isCreated()){
            
            int i=getFacade().getGameStatus();
            if((getFacade().onTurn() && Math.abs(i)>1)){
                JOptionPane.showMessageDialog(MyFrame.instance,"Šachmat","Hra",JOptionPane.WARNING_MESSAGE);
            }
            else if((getFacade().onTurn() && Math.abs(i)==1)) JOptionPane.showMessageDialog(MyFrame.instance,"Šach","Hra",JOptionPane.WARNING_MESSAGE);

            if(Math.abs(i)>1){ JOptionPane.showMessageDialog(MyFrame.instance,"Konec hry","Hra",JOptionPane.INFORMATION_MESSAGE);
                getFacade().endGame();
               
            }
        }
    }

    /**
     * informuje všechyn observery že byla změna dat v jádru aplikace
     */
    public void updateData() {
        notifyObservers();
        dialogGameState();
    }

}

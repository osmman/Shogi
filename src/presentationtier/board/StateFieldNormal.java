
package presentationtier.board;

import core.Coordinate;
import core.exceptions.GameException;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Observable;
import javax.swing.JOptionPane;
import presentationtier.captured.CapturedField;
import shogi.Globals;
import presentationtier.MyFrame;

/**
 * základní context, při volání akce se context změní na StateFieldActive
 * @author osman
 */
class StateFieldNormal implements StateField {

    private static StateFieldNormal instance = new StateFieldNormal();

    public static StateField getInstance(){
        return  instance;
    }

    /**
     * aktivované pole se změní na Active pokud na něm leží token aktuálního hráče na tahu, a pokud lze token povýšit
     * vyběhne dialok s otázkou
     * pak se pošle collection se souřadnicemi možných tahů označeného tokenu na Chessboard a ten to dál roznese
     * @param f pole které bude označené
     */
    public void action(Field f) {

        if(f.token!=null && Globals.instance.getFacade().onTurn()){
            if(f.token.getPlayer()==Globals.instance.getFacade().getOnTurn()){
                Chessboard.instance.selected=f;
                f.notifyChessboard(Globals.instance.getFacade().getCoordinatesCanMoveToken(f.POSITION));
                f.setState(StateFieldActive.getInstance());

                

                if(Globals.instance.getFacade().canPromoteToken(f.POSITION)){
                    int n=JOptionPane.showConfirmDialog(MyFrame.instance,"Chcete povýšit token?","Povýšení tokenu",JOptionPane.YES_NO_OPTION);
                    if(0==n){
                        try {
                            Globals.instance.getFacade().promoteToken(f.POSITION);
                        } catch (GameException ex) {

                        }
                        f.updateData();
                    }
                }
                f.repaint();
            }
        }

    }

    public void drawGrafic(Field f,Graphics g) {

    }

    /**
     * pokud je arg instancí CapturedField nastaví se context na StateFieldHighlightCaptured
     * pokud je arg instancí collection a souřadnice pole se z této collection nacházejí změní se context na StateFieldHighlight
     * při jiné možnosti context zůstává
     * @param f pole
     * @param o
     * @param arg argument
     */
    public void update(Field f,Observable o, Object arg) {
        
        if(arg instanceof  CapturedField){
            f.setState(StateFieldHighlightCaptured.getInstance());
            f.repaint();
        }
        else if(arg instanceof Collection){

            Collection<Coordinate> col = (Collection<Coordinate>) arg;

            for (Coordinate coordinate : col) {
                if(f.POSITION.equals(coordinate)){
                    f.setState(StateFieldHighlight.getInstance());
                    f.repaint();
                    return;
                }


            }

        }

    }
}

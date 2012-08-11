/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Label;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import shogi.Globals;

/**
 * status panel
 * @author osman
 */
public class StatusPanel extends JPanel implements MyObserver {

    private Label left=new Label();

    public StatusPanel() {
        super(new BorderLayout());
        init();
        Globals.instance.addObserver(this);
    }

    private void init() {

        left.setPreferredSize(new Dimension(500, 20));


        add(new JSeparator(),BorderLayout.NORTH);
        add(left,BorderLayout.WEST);
    }

    /**
     * pří změně znovu vykreslí status bar podle toho zda je hra vytvořená, kdo je na tahu nebo čekání na dokončení kola druhého hráče
     * @param o
     * @param arg
     */
    public void update(AbstractObservable o, Object arg) {
        if(!Globals.instance.getFacade().isCreated()){ left.setText("Konec hry"); return;}

        if(!Globals.instance.getFacade().onTurn()) left.setText("Čekám na odpověď...");

        else left.setText("Hráč na tahu: "+Globals.instance.getFacade().getOnTurn().toString());
        repaint();
    }

}

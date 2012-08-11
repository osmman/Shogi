/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier.actions;

import businesstier.Game.GameType;
import businesstier.ShogiFacade;
import core.Player;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import shogi.Globals;
import presentationtier.MyFrame;

/**
 * akce pro připojení k serveru
 * @author osman
 */
public class JoinGameAction extends Action {
    
    public static JoinGameAction instance = new JoinGameAction();

    private JoinGameAction() {
        super("Připojit");
        Globals.instance.addObserver(this);
    }

    /**
     * vyběhne JOptionPanel kam se napíše ip adresa serveru
     * @param e
     */
    public void actionPerformed(ActionEvent e) {

        String s=JOptionPane.showInputDialog(MyFrame.instance,"Ip adresa serveru","Připojit",JOptionPane.QUESTION_MESSAGE);
        if(s!=null){
            try {
                Globals.instance.getFacade().newGame(GameType.NET_CONNECT, Player.PLAYER1, s);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(MyFrame.instance,"Nelze se pripojit","Game error",JOptionPane.WARNING_MESSAGE);
                Globals.instance.getFacade().endGame();
            }
        }
    }

    /**
     * akce je povolena pokus není hra vytvořena
     * @return
     */
    @Override
    boolean isEnabledInState() {
        return !ShogiFacade.getInstance().isCreated();
    }
}

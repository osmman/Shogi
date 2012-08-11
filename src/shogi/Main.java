/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shogi;

import javax.swing.SwingUtilities;
import presentationtier.MyFrame;

/**
 * Main třída
 * @author osman
 */
public class Main {

    /**
     * @param args the command line arguments
     */
        public static void main(String[] args){

       final Globals glob = new Globals();

       SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MyFrame dlg = new MyFrame();
                dlg.setVisible(true);
                glob.updateData();
           }
       });

    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier.captured;

import java.awt.Graphics;
import java.util.Observable;

/**
 * context pro deaktivované položka menu pro aktuální herní kolo
 * položka menu nic nedělá
 * @author osman
 */
public class StateCapturedFieldDeactive implements StateCapturedFiled {


    private static StateCapturedFieldDeactive instance = new StateCapturedFieldDeactive();

    public static StateCapturedFiled getInstance(){
        return  instance;
    }

    public void action(CapturedField f) {
        
    }

    public void drawGrafic(CapturedField f, Graphics g) {
        
    }

    public void update(CapturedField f, Observable o, Object arg) {
        
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier.dialogs;

import java.util.Collection;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import presentationtier.MyFrame;

/**
 * vyhazuje dialogi z vláken u kterých si vytvoří sondu
 * třída je nedodělaná
 * @author osman
 */
public class ThreadDialogs {

    /**
     * je vlákno které se vytvoří, spostí a pak se uspí v metode check() u objektů které implementují interface ThreadInteruptError
     * po probuzení se vyvolá dialog
     */
    private class Sonda implements Runnable{

        private ThreadInteruptError o;
        private String message;

        public Sonda(ThreadInteruptError o,String message) {
            this.o=o;
            this.message=message;
        }

        public void run() {
            o.check();
            JOptionPane.showMessageDialog(MyFrame.instance,message, "",JOptionPane.WARNING_MESSAGE);
        }

    }

    private static  ThreadDialogs instance;

    public static  ThreadDialogs getInstance() {
        if(instance==null) instance=new ThreadDialogs();
        return instance;
    }

    Collection<Thread> col;

    private ThreadDialogs() {
        col= new LinkedList<Thread>();
    }

    /**
     * vytvoří a aktivuje sondu která vypíše zprávu message
     * @param o
     * @param message zpráva která se vypíše při erroru
     */
    public void addThread(ThreadInteruptError o,String message){
        new Thread(new Sonda(o,message)).start();
    }

    public void addThread(ThreadInteruptError o,Exception ex){
        String a = null;

        StackTraceElement[] s= ex.getStackTrace();

        for (StackTraceElement stackTraceElement : s) {
            a+=stackTraceElement.toString()+"\n\b";
        }


        new Thread(new Sonda(o,a)).start();
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationtier.dialogs;

/**
 * interface pro vlákna které budou pozorována pomocí sondy v třídě ThreadDialogs
 * @author osman
 */
public interface ThreadInteruptError {
    /**
     * porovudí uspané vlákno a aktivuje hlášku
     */
    public void error();

    /**
     * uspí vlákno
     */
    public void check();
}

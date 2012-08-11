

package presentationtier.captured;

import core.Player;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collection;
import javax.swing.JPanel;

/**
 * potomek AbstractCapturedMenu rozšíření ve vykreslování menu 
 * @author osman
 */
public class CapturedMenu extends AbstractCapturedMenu{

    private class CapturedMenuPanel extends JPanel {

        public CapturedMenuPanel(){        
            init();
        }

        void init() {
           setPreferredSize(new Dimension(75,600));
            JPanel zaj = new JPanel(new GridLayout(8, 1));
        }
        
    }

    private CapturedMenuPanel panel;

    public CapturedMenu(Player p) {
        super(p);
        panel= new CapturedMenuPanel();
        reload();
        
    }

    public JPanel getJPanel(){
        return panel;
    }

    /**
     * znovu načteí CapturedField do panelu
     */
    protected void reload(){
        panel.removeAll();
        Collection<CapturedField> fil =getFields();
        for (CapturedField capturedField : fil) {
            panel.add(capturedField);
        }
        
        panel.doLayout();
       
    }
}


package presentationtier.dialogs;

import businesstier.Game.GameType;
import core.Player;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import shogi.Globals;


/**
 * dialog pro vytvoření nové hry
 * možnosti lokální hra, nebo síťová a výběz začínajícího hráče
 * @author osman
 */
public class CreateNewGame extends JDialog{

    private JFrame frame;

    private ButtonGroup buttonGroup1 = new ButtonGroup();
    private JButton jButton1 = new JButton("Nová hra");
    private JButton jButton2 = new JButton("Cancel");
    private JComboBox jComboBox1 = new JComboBox(new String[] {  "Lokální","Síťová" });
    private JRadioButton jRadioButton1 = new JRadioButton("Hráč 1");
    private JRadioButton jRadioButton2 = new JRadioButton("Hráč 2");
    private JLabel jLabel1 = new JLabel("Typ hry:");
    private JLabel jLabel2 = new JLabel("První na tahu:");


    public CreateNewGame(JFrame f) {
        super(f, "Nová hra", true);
        frame=f;

        init();
        visible();
    }




    public void visible(){

        setResizable(false);
        pack();

        Point localFrame=frame.getLocation();

        setLocation(localFrame.x + frame.getWidth()/2 - getWidth() / 2, localFrame.y + frame.getHeight()/2 - getHeight() / 2);
        setVisible(true);
    }

    private void init() {
        setLayout(new BorderLayout());
        JPanel panel= new JPanel();
        BoxLayout group =new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(group);

        panel.add(jLabel1);
        jLabel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(jComboBox1);
        jComboBox1.setAlignmentX(Component.LEFT_ALIGNMENT);

        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        jRadioButton1.setSelected(true);
        jRadioButton1.setActionCommand("Player1");
        jRadioButton1.setLocation(5, 5);
        jRadioButton1.setAlignmentX(Component.LEFT_ALIGNMENT);
        jRadioButton2.setActionCommand("Player2");
        jRadioButton2.setLocation(100, 100);
        jRadioButton2.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        jLabel2.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(jLabel2);
        panel.add(jRadioButton1);
        panel.add(jRadioButton2);

        add(panel,BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));

        buttons.add(jButton1);
        buttons.add(jButton2);

        jButton1.addActionListener(new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                Player p = null;
                if(buttonGroup1.getSelection().getActionCommand().equals("Player1")){
                    p = Player.PLAYER1;
                }else {
                    p = Player.PLAYER2;
                }
                if (jComboBox1.getSelectedIndex() == 0) {
                    try {
                            Globals.instance.getFacade().newGame(GameType.LOCAL, p, null);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Nepodarilo se vytvorit hru", "Game error", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    try {
                            Globals.instance.getFacade().newGame(GameType.NET_CREATE, p, null);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Nepodarilo se vytvorit hru", "Game error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                dispose();
            }
        });
        jButton2.addActionListener(new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        add(buttons,BorderLayout.SOUTH);
    }

    

}

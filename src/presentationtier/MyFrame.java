package presentationtier;

import core.Player;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import presentationtier.board.Chessboard;
import presentationtier.captured.CapturedMenu;
import presentationtier.menu.SchogiMenuBar;
import shogi.Globals;


/**
 * hlavní okno aplikace
 * @author osman
 */
public class MyFrame extends JFrame implements MyObserver{


    public static MyFrame instance;
    public Chessboard chessboard;
    public CapturedMenu player1CapturedMenu;
    public CapturedMenu player2CapturedMenu;

    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;

    public MyFrame() {
        instance=this;
        chessboard=new Chessboard();
        
        player1CapturedMenu= new CapturedMenu(Player.PLAYER1);
        player2CapturedMenu=new CapturedMenu(Player.PLAYER2);
        init();
        Globals.instance.addObserver(this);
    }

    private void init(){
        setTitle("Shogi");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(710, 630));

        setJMenuBar(new SchogiMenuBar());
        JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        
        // main pael
        mainPanel = new javax.swing.JPanel(new BorderLayout());
        JPanel mainPanel2 = new JPanel();
        mainPanel2.add(chessboard.getJPanel());
        mainPanel.add(player1CapturedMenu.getJPanel(),BorderLayout.WEST);
        mainPanel.add(player2CapturedMenu.getJPanel(),BorderLayout.EAST);
        mainPanel.add(mainPanel2,BorderLayout.CENTER);


        statusPanel = new StatusPanel();

        

        add(mainPanel,BorderLayout.CENTER);
        add(statusPanel,BorderLayout.SOUTH);

        pack();
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                /**
                 * pokud je rozehraná hra zeptá se zda doopravdy chcete ukončit hru
                 */
                int i = 0;

                if (Globals.instance.getFacade().isCreated()) {
                    i = JOptionPane.showConfirmDialog(MyFrame.instance, "Chcete ukončit rozehranou hru?", "Exit", JOptionPane.CANCEL_OPTION);
                }
                if (i == 0) {
                    Globals.instance.getFacade().endGame();
                    System.exit(0);
                }
            }

        });
    }
    /**
     * překreslí okno
     * @param o
     * @param arg
     */
    public void update(AbstractObservable o, Object arg) {
        repaint();
    }
}

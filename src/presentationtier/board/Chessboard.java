package presentationtier.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * potomek AbstractChessboard rozšíření ve vykreslování hracího pole
 * @author osman
 */
public class Chessboard extends AbstractChessboard{

    private class ChessboardFrame extends JPanel {

        public ChessboardFrame(){
            init();
            
        }      
        
        void init() {
            setBackground(Color.white);

            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

            setName("Herni Deska");

            setPreferredSize(new Dimension(544, 544));

            setLayout(new GridLayout(9, 9));


            getGraphicsConfiguration();
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.black);
            g.fillOval(176, 176, 11, 11);
            g.fillOval(176 + 180, 176, 11, 11);
            g.fillOval(176 + 180, 176 + 180, 11, 11);
            g.fillOval(176, 176 + 180, 11, 11);
        }
    }


    public static Chessboard instance;
    private JPanel panel = new ChessboardFrame();
    public Field selected;


    public Chessboard(){
        instance=this;

        for(int y=0;y<9;y++){
            for(int x=0;x<9;x++){
                Field f = new Field(getCoordinate(x, y));
                put(getCoordinate(x, y) ,f);
                panel.add(f);
            }
        }
    }

    public JPanel getJPanel(){
        return panel;
    }
}

package presentationtier.board;

import core.Coordinate;
import core.Token;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import presentationtier.images.ShogiImage;
import presentationtier.update;
import shogi.Globals;

/**
 * Field je jedno hrí pole na herní desce, o chování pole v určitých situacích se stará context který je Implementací interfasu StateFiled
 * objekt je observer a také informuje Chessboard o svoji změně
 * @author osman
 */
public class Field extends JComponent implements Observer,update{
    
    

    /**
     * na jakém pozici leží Field
     */
    public final Coordinate POSITION;
    /**
     * token ležící na políčku
     */
    protected  Token token;
    /**
     * obrázek tokenu
     */
    private Image img;
    /**
     * aktuální context
     */
    private StateField context;


    /**
     * nastavení contextu
     * @param newState context který se má nastavit
     */
    public void setState(StateField newState){
        context=newState;
    }

    /**
     * akce která se vyvolá při kliknutí na Field
     * akce se vykoná podle contextu
     */
    public void action(){
        if(Globals.instance.getFacade().onTurn()){
            context.action(this);
        }   
    }

    /**
     * vykreslení grafiky podle contextu
     * @param g
     */
    public void drawGraphic(Graphics g){
        context.drawGrafic(this, g);
    }

    /**
     * construktor context se nastavý na základní tj. StateFieldNormal a nastaví se pozice políčka
     * @param c pozice
     */
    public Field(Coordinate c) {
        super();
        setState(StateFieldNormal.getInstance());
        POSITION=c;
        init();
        
    }

    /**
     * aktualízují se data aktuálního políčka tj. získá se token který stojí na políčko a pokud nějaký takový existuje získá se jeho obrázek
     */
    public void updateData(){
        token=Globals.instance.getFacade().getToken(POSITION);
        if(token!=null)
            img=ShogiImage.instance.getScaletImage(token.getEnumToken(), token.getPlayer());
    }

    /**
     * informuje observer Chessboard o změně
     * @param s parametr
     */
    public void notifyChessboard(Object s){
        Chessboard.instance.update(this,s);
    }

    private void init(){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(60, 60));

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                action();
            }
        });
    }

    /**
     * vykreslí komponent tj. vyvolá funkci drawGraphic a posléze vykreslí obrázek tokenu
     * @param g
     */
    @Override
    public void paintComponent(Graphics g){
        drawGraphic(g);
        if(token!=null)
            g.drawImage(img, (getWidth()-37)/2, (getHeight()-54)/2, 38, 54, this);
    }

    /**
     * pri změně observable objektu se aktualizují data updateData() a jeste udtate u contextu
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        if(o instanceof Chessboard){
            updateData();   
            context.update(this,o, arg);              
        }
        
    }


}


package presentationtier.captured;

import core.EnumToken;
import core.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.event.MouseAdapter;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import presentationtier.images.ShogiImage;

/**
 * Jsou jednotlivé položky menu pro zajaté tokeny vždy je přiřazená k nějakému typu tokenu a udržuje kolik tokenů tohoto typu obsahuje.
 * Chování jednotlivých položek menu se používá contexty ty jsou implementací interfasu StateCapturedField
 * @author osman
 */
public class CapturedField extends JComponent implements Observer {

    /**
     * aktuální context
     */
    private StateCapturedFiled context;
    /**
     * obrázek tokenu
     */
    private Image img;
    /**
     * počet zajatých tokenů stejného typu
     */
    private int pocet;
    /**
     * typ tokenu
     */
    private EnumToken typ;
    /**
     * hráč
     */
    private Player player;

    /**
     * vytvoří se nová položka vypu EnumToken s contextem a přiřazení k hráči
     * plus počet tokenů se nastaví na pocet=1
     * @param typ typ tokenu
     * @param context
     * @param p hráč
     */
    public CapturedField(EnumToken typ,StateCapturedFiled context,Player p) {
        this.typ=typ;
        this.context=context;
        this.player=p;
        pocet=1;
        init();
    }

    private void init(){
        setPreferredSize(new Dimension(70, 63));
        img=ShogiImage.instance.getScaletImage(typ, player);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                action();
            }
        });
    }

    /**
     * vykreslí component nejdřív volá funkci drawGrafic() pak vykreslí obrázek a počet tokenů
     * @param g
     */
    @Override
    public void paintComponent(Graphics g){
        drawGraphic(g);

        g.setColor(Color.black);

        g.setFont(new Font("Arial", 1, 12));
        g.drawString(Integer.toString(pocet), getWidth()-12, getHeight()-5);

        g.drawImage(img, (getWidth()-37)/2, (getHeight()-54)/2, 38, 54, this);
    }

    /**
     * zvýší počet tokenů o jedna
     */
    public void increment() {
        pocet++;
    }

    /**
     * nastaví context
     * @param newState nový context
     */
    public void setState(StateCapturedFiled newState){
        context=newState;
    }

    /**
     * akce která se volá při kliknutí miší na položku menu
     * akce se vykoná podle contextu
     */
    private void action() {
        if(pocet==0) return;
        context.action(this);
    }

    /**
     * vykreslení grafiky podle contextu
     * @param g
     */
    private void drawGraphic(Graphics g){
        context.drawGrafic(this, g);
    }

    /**
     * odposouchávání podle contextu
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        context.update(this, o, arg);
    }

    /**
     * vrátí enumToken pro který je položka menu určená
     * @return EnumToken
     */
    public EnumToken getTyp() {
        return typ;
    }

    
}

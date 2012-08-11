
package core;

import core.exceptions.IlegalArgumentException;
import java.util.ArrayList;
import java.util.Collection;
/**
 * abstraktní třída pro základ tokenu, základní logika
 * @author Turek Tomáš
 */
public abstract class AbstractToken implements Token {

    private boolean moved=false;
    private Coordinate position;
    private Player player;

    public abstract String getName();

    /**
     * Token lze povýšit jen pokud byl přesunut a zaroveň se nachází v povyšovací zómě
     * @return true pokud lze povýšit
     */
    public boolean canPromote() {
       return isMoved()&&getPlayer().inPromotedArea(getPosition());
    }
    
    public boolean isPromoted(){
        return false;
    }

    public abstract Token promote();

    public Token degrade() {
        return this;
    }

    /**
     * Po zadání souradnic x a y získáte z tabulky pravydlo pro přesun na určitý směr
     * @see <table border="1">
     *  <tr><td>X</td><td>↓</td><td>↑</td></tr>
     *  <tr><td>→</td><td>↓→</td><td>↑→</td></tr>
     *  <tr><td>←</td><td>↓←</td><td>↑←</td></tr>
     * </table>
     * @param x souřadnice tabulky
     * @param y souřadnice tabulky
     * @return int <ul><li>0 - nepovolený směr</li> <li>1 - ve směru se lze posunout o maximálně jedno políčko</li> 2- lze se přemístit o libovolný počet</li></ul>
     */
    protected abstract int getRule(int x, int y);
    @Override
    public abstract String toString();

    public void setPosition(Coordinate c){
        if(getPosition()==null){
            position=c;
        }
        else {
            position=c;
        }
    }

    public void setMoved(){
        moved=true;
    }

    public boolean isMoved(){
        return moved;
    }
    
    public Coordinate getPosition(){
        return position;
    }

    public Player getPlayer(){
        return player;
    }
    
    public void setPlayer(Player p){
        player=p;
    }

    public void reset(){
        player=null;
        moved=false;
        position=null;
    }

    /**
     *
     * @param p vlastník tokenu (Hráč)
     * @param c pozice tokenu
     */
    protected AbstractToken(Player p,Coordinate c){
        player=p;
        position=c;
    }
   
    public boolean posibilityMove(Coordinate c,GameBoard g){
        try {
            int x = c.getX() - this.getPosition().getX();
            int y = c.getY() - this.getPosition().getY();
            int vysledek = getRule(transformaceSmeru(x), transformaceSmeru(y));
            if(g.getToken(c)!=null){
                if(g.getToken(c).getPlayer()==getPlayer()) return false;
            }
            if (!g.volnaCesta(getPosition(), c)) {
                return false;
            }
            if (!GameUtils.bezpecnyTah(getPosition(), c, getPlayer(), g)) return false;
            switch (vysledek) {
                case 0:
                    return false;
                case 1:
                    if (Math.abs(x) <= 1 && Math.abs(y) <= 1) {
                        return true;
                    }
                    return false;
                case 2:
                    return true;
                default:
                    return false;
            }
        } catch (IlegalArgumentException ex) {
            return false;
        }
    }

    /**
     * transformuje cislo pro pouziti na tabulku posunu
     * @param cislo
     * @return int 0,1,2
     */
    private int transformaceSmeru(int cislo){
        int i=-1;
        if(getPlayer().POSITION) i=1;
        if(cislo*i<0)return 2;
        if(cislo*i>0)return 1;
        return 0;
    }

    public Collection<Coordinate> getCoordinateCanMove(GameBoard g) {

        ArrayList<Coordinate> cor=new ArrayList<Coordinate>();
        Coordinate move=null;

        int x = getPosition().getX();
        int y = getPosition().getY();

        int distance=8;
        int horizontal =-1;
        int vertical =-1;
        while(true){

            if(vertical>1) break;
            int vz=1;
            while(vz<=distance){
                move=dejVeSmeru(horizontal, vertical, vz, getPosition());
                

                if(move==null) break;

                if(posibilityMove(move,g))cor.add(move);

                vz++;
            }
            if(horizontal==1){
                vertical++;
                horizontal=-1;
            }
            else horizontal++;
        }

        return cor;
    }

    /**
     * vrátí Coordinate ve smeru a vzdálenisti od rodiče
     * @param horizontal smer horizontální
     * @param vertical směr vertikální
     * @param distance vzdálenost od rodiče
     * @param parrant rodič
     * @return Coordinate
     */
    private Coordinate dejVeSmeru(int horizontal,int vertical,int distance,Coordinate parrant){
        return Coordinate.getCoordinate(parrant.getX()+(horizontal*distance), parrant.getY()+(vertical*distance));
    }

    public boolean canBackToGame(Coordinate c,GameBoard g) {
        if(g.getToken(c)==null) return true;
        return false;
    }

    public abstract EnumToken getEnumToken();

    

}

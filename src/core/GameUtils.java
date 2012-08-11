package core;

import core.tokens.King;
import java.util.Collection;

/**
 * nástroje pro herní logiku
 * <ul>
 * <li>Šach</li>
 * <li>Šachmat</li>
 * <li>Pohyb krále</li>
 * <li>Bezpočný tah</li>
 * <li>Stav hry</li>
 * </ul>
 * @author osman
 */
public class GameUtils {
    /**
     * Vyhodnotí zda je tah bezpecný tj. po tahu nedojde k ohrožení vlastního krále
     * @param s source coordinate tahu
     * @param f detination coordinate tahu
     * @param p pro kterého hráče se testuje
     * @param g herní deska na které se testuje
     * @return true tah je povolen, false zakázaný tah
     */
    public static boolean bezpecnyTah(Coordinate s,Coordinate f,Player p,GameBoard g){
        boolean vysledek;

        Token temp = g.remove(f);
        Token temp2 = g.remove(s);
        g.put(f,temp2);
        //Token temp= g.switchToken(g.getToken(s), f);
        
        vysledek= !sach(p, g);

        g.remove(f);
        g.put(f,temp);
        g.put(s,temp2);

        /*temp =g.switchToken(temp, f);
        g.switchToken(temp, s);*/

        return vysledek;
    }

    /**
     * Vyhodnotí stav hry
     * 
     * @param p hráč pro kerého je určen test
     * @param g testovací herní deska
     * @return <table><tr><td>+-2</td><td>Šachmat</td></tr><td>+-1</td><td>Šach</td></tr><td>0</td><td>Normální stav</td></tr></table> znaménko udává hráče +Player1, -Player2
     */
    public static int state(Player p,GameBoard g){
        int znamenko=-1;
        int vysledek=0;
        if(p==Player.PLAYER1) znamenko=1;

        if(sach(p, g)){
            vysledek++;
            if(mat(p, g)) vysledek++;
        }
        return vysledek*znamenko;
    }

    /**
     * vyhodnocení šachu
     * @param p hráč pro kerého je určen test, bere se z něj umístění krále
     * @param g testovací herní deska
     * @return true šach jinak false
     */
    public static boolean sach(Player p,GameBoard g){
        Player opponent=Player.PLAYER1;
        if(p==opponent) opponent=Player.PLAYER2;
        Collection<Token> col=g.getTokens(opponent);
        if(p.getKing().getPosition()==null) return true;
        for (Token token : col) {
            if(token==null) continue;
            if(token.posibilityMove(p.getKing().getPosition(),g)) return true;
        }
        return false;
    }

    /**
     * funkci využívá pouze král pří testu bezpečného tahu pomocí krále
     * @param k token krále
     * @param c misto přesunu
     * @param g testovací herní deska
     * @return true bezpečný, false nebezpečný
     */
    public static boolean canKingMoveInCoordinate(King k,Coordinate c,GameBoard g){
        if(k.getPosition()==null) return false;
        boolean vysledek=true;

        Coordinate positionKing=k.getPosition();
       // if(k.posibilityMove(c)){
            Token temp=g.remove(c);
            g.remove(positionKing);
            g.put(c,k);
            //Token temp=g.switchToken(k, c);

            if(sach(k.getPlayer(), g)){
                vysledek=false;
            }

            g.remove(c);
            g.put(c,temp);
            g.put(positionKing,k);
            
            //temp=g.switchToken(temp, c);
            //g.switchToken(temp, positionKing);
      //  }
        return vysledek;
    }

    /**
     * Vyhodnocení matu zjištěno není toálně potřeba u této hry ale používám
     * @param p hráč
     * @param g herní deska
     * @return true mat jinak false
     */
    public static boolean mat(Player p,GameBoard g){
       if(p.getKing().getPosition()==null) return true;
       Collection<Token> col=g.getTokens(p);
       Collection<Coordinate> cor;

       Token temp;
       Token tmp;
       Coordinate puvodniPozice;

       for (Token token : col) {
            cor=token.getCoordinateCanMove(g);
            puvodniPozice=token.getPosition();
            for (Coordinate coordinate : cor) {           

                temp=g.remove(coordinate);
                g.remove(puvodniPozice);
                g.put(coordinate,token);
                //temp=g.switchToken(token, coordinate);
                if(!sach(p, g)){
                    g.remove(coordinate);
                    g.put(coordinate,temp);
                    g.put(puvodniPozice,token);
                    //temp=g.switchToken(temp, coordinate);
                    //g.switchToken(temp, puvodniPozice);
                    return false;
                }
                else{
                    g.remove(coordinate);
                    g.put(coordinate,temp);
                    g.put(puvodniPozice,token);
                //temp=g.switchToken(temp, coordinate);
                //g.switchToken(temp, puvodniPozice);
                }
            }
        }

       return true;
    }

}

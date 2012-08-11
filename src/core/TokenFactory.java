
package core;

import core.tokens.*;

/**
 * továrna na Tokeny
 * @author osman
 */
public class TokenFactory {


    /**
     * vytvoří nový objekt Token podle daných vlastností
     * vyrábí tyto tokeny GoldGeneral, King, Rook, SilverGeneral, Knight, Bishop, Lancer, Pawn
     * @param typ druh vytvořeného tokenu možnosti (GoldGeneral, King, Rook, SilverGeneral, Knight, Bishop, Lancer, Pawn)
     * @param p vlastník tokenu (Hráč)
     * @param c pozice tokenu
     * @return Token nebo null
     */
    public static Token getToken(EnumToken typ,Player p,Coordinate c){

        switch (typ) {
            case GoldGeneral:
                return new GoldGeneral(p, c);
            case King:
                King king =new King(p, c);
                return king;
            case Rook:
                return new Rook(p, c);
            case SilverGeneral:
                return new SilverGeneral(p, c);
            case Knight:
                return new Knight(p, c);
            case Bishop:
                return new Bishop(p, c);
            case Lance:
                return new Lance(p, c);
            case Pawn:
                return new Pawn(p, c);
        }
        return null;
    }
}

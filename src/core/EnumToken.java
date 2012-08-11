/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import java.io.Serializable;

/**
 * enum pro všechny druhy tokenů a rozpoznání instance od tříd
 * @author osman
 */
public enum EnumToken implements Serializable {
    King("King"),
    Rook("Rook"),
    RookPromoted("RookPromoted"),
    Bishop("Bishop"),
    BishopPromoted("BishopPromoted"),
    GoldGeneral("GoldGeneral"),
    SilverGeneral("SilverGeneral"),
    SilverGeneralPromoted("SilverGeneralPromoted"),
    Knight("Knight"),
    KnightPromoted("KnightPromoted"),
    Lance("Lance"),
    LancePromoted("LancePromoted"),
    Pawn("Pawn"),
    PawnPromoted("PawnPromoted")
    ;

    /**
     * název třídy kretá je pro něj přidělena
     */
    public final String CLASS_NAME;

    /**
     * true pokud je objekt instancí třídy kterou zastupuje jedna z enum konstant
     * @param a objekt
     * @return true je instancí, false není
     */
    public boolean isInstance(Object a){
        try {
            return Class.forName("core.tokens." + CLASS_NAME).isInstance(a);
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }

    private EnumToken(String className) {
          this.CLASS_NAME=className;
    }

}

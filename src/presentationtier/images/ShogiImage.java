
package presentationtier.images;

import core.EnumToken;
import core.Player;

import java.awt.Image;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import java.net.MalformedURLException;

import java.net.URISyntaxException;
import java.net.URL;

import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;


/**
 * třída načítá obrázky a upravuje je
 * @author osman
 */
public class ShogiImage {

    public static ShogiImage instance=new ShogiImage();

    private URL imgRootAdress=ClassLoader.getSystemResource("presentationtier/images/");
    private Map<EnumToken,Image> collection1 = new TreeMap<EnumToken, Image>();
    private Map<EnumToken,Image> collection2 = new TreeMap<EnumToken, Image>();

    /**
     * načtení obrázků se zmenšením a otočením
     */
    private ShogiImage() {
        try {

            collection1.put(EnumToken.Bishop, ImageIO.read(getImageURI("bishop.png")).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection1.put(EnumToken.GoldGeneral, ImageIO.read(getImageURI("goldgeneral.png")).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection1.put(EnumToken.King, ImageIO.read(getImageURI("king.png")).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection1.put(EnumToken.Knight, ImageIO.read(getImageURI("knight.png")).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection1.put(EnumToken.Lance, ImageIO.read(getImageURI("lance.png")).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection1.put(EnumToken.Pawn, ImageIO.read(getImageURI("pawn.png")).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection1.put(EnumToken.Rook, ImageIO.read(getImageURI("rook.png")).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection1.put(EnumToken.SilverGeneral, ImageIO.read(getImageURI("silvergeneral.png")).getScaledInstance(38, 54, Image.SCALE_SMOOTH));

            collection1.put(EnumToken.BishopPromoted, ImageIO.read(getImageURI("promotedbishop.png")).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection1.put(EnumToken.KnightPromoted, ImageIO.read(getImageURI("promotedknight.png")).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection1.put(EnumToken.LancePromoted, ImageIO.read(getImageURI("promotedlance.png")).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection1.put(EnumToken.PawnPromoted, ImageIO.read(getImageURI("promotedpawn.png")).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection1.put(EnumToken.RookPromoted, ImageIO.read(getImageURI("promotedrook.png")).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection1.put(EnumToken.SilverGeneralPromoted, ImageIO.read(getImageURI("promotedsilvergeneral.png")).getScaledInstance(38, 54, Image.SCALE_SMOOTH));

            collection2.put(EnumToken.Bishop, rotatedImage(ImageIO.read(getImageURI("bishop.png"))).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection2.put(EnumToken.GoldGeneral, rotatedImage(ImageIO.read(getImageURI("goldgeneral.png"))).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection2.put(EnumToken.King, rotatedImage(ImageIO.read(getImageURI("king.png"))).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection2.put(EnumToken.Knight, rotatedImage(ImageIO.read(getImageURI("knight.png"))).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection2.put(EnumToken.Lance, rotatedImage(ImageIO.read(getImageURI("lance.png"))).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection2.put(EnumToken.Pawn, rotatedImage(ImageIO.read(getImageURI("pawn.png"))).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection2.put(EnumToken.Rook, rotatedImage(ImageIO.read(getImageURI("rook.png"))).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection2.put(EnumToken.SilverGeneral, rotatedImage(ImageIO.read(getImageURI("silvergeneral.png"))).getScaledInstance(38, 54, Image.SCALE_SMOOTH));

            collection2.put(EnumToken.BishopPromoted, rotatedImage(ImageIO.read(getImageURI("promotedbishop.png"))).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection2.put(EnumToken.KnightPromoted, rotatedImage(ImageIO.read(getImageURI("promotedknight.png"))).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection2.put(EnumToken.LancePromoted, rotatedImage(ImageIO.read(getImageURI("promotedlance.png"))).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection2.put(EnumToken.PawnPromoted, rotatedImage(ImageIO.read(getImageURI("promotedpawn.png"))).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection2.put(EnumToken.RookPromoted, rotatedImage(ImageIO.read(getImageURI("promotedrook.png"))).getScaledInstance(38, 54, Image.SCALE_SMOOTH));
            collection2.put(EnumToken.SilverGeneralPromoted, rotatedImage(ImageIO.read(getImageURI("promotedsilvergeneral.png"))).getScaledInstance(38, 54, Image.SCALE_SMOOTH));

        } catch (Exception ex) {
            System.exit(1);
        }   
    }

    /**
     * vrátí URL adresu k obrázku
     * @param name název obrázku
     * @return URL cesta k obrázku
     * @throws URISyntaxException
     * @throws MalformedURLException
     */
    private URL getImageURI(String name) throws URISyntaxException, MalformedURLException{
        return new URL(imgRootAdress, name);
    }

    /**
     * vrátí zmenšený i otočený obrázep podle hráče a typu tokenu
     * @param typ typ tokenu pro který je obrázek určen
     * @param p hráč kvůli otočení
     * @return Image
     */
    public Image getScaletImage(EnumToken typ,Player p) {
        if(p==Player.PLAYER1){
            return collection1.get(typ);
        }
        return collection2.get(typ);
    }

    /**
     * otočí obrázek o 180°
     * @param i obrázek
     * @return BufferedImage otočený obrázek
     */
    private BufferedImage rotatedImage(Image i){
        BufferedImage im=new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        im.getGraphics().drawImage(i, 0, 0, null);

        AffineTransform transform = new AffineTransform();
           transform.rotate(Math.toRadians(180), im.getWidth()/2, im.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
        return im = op.filter(im,null);
    }
}

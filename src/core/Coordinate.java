package core;

import core.exceptions.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * určuje souřadnicový systém herní desky, velikost herní desky, určuje pozice jednotlivých tokenů atd..
 * @author Turek Tomáš
 */
public class Coordinate implements Comparable<Coordinate>,Serializable {

    /**
     * catch pro souřadnice
     */
    private static Map<Integer,Coordinate> created = new TreeMap<Integer,Coordinate>();

    private int x;
    private int y;

    /**
     * maximální X souřadníce
     */
    public static final int MAX_X=8;
    /**
     * maximální Y souřadníce
     */
    public static final int MAX_Y=8;
    /**
     * minimální X souřadníce
     */
    public static final int MIN_X=0;
    /**
     * minimální Y souřadníce
     */
    public static final int MIN_Y=0;

    private Coordinate(int x,int y){
        this.x=x;
        this.y=y;
    }

    /**
     * vrátí Coordinate z catch po zadání x a y souřadnic, pokud ještě coordinate nebyl vytvořen vytvoříse a přidá se do catch
     * @param x x souřadnice
     * @param y y souřadnice
     * @return Coordinate,null mimo meze
     */
    public static Coordinate getCoordinate(int x,int y){ //throws OutOfRangeException{
        if(x > MAX_X || x<MIN_X || y > MAX_Y || y<MIN_Y) return null;// throw new OutOfRangeException();

        Coordinate coor=created.get(hashCode(x,y));

        if(coor==null){
            Coordinate c=new Coordinate(x, y);
            created.put(c.hashCode(), c);
            return c;
        }

        return coor;
        
    }

    /**
     * Vrátí kolekci coordinatu od s do f bez
     * @param s source coordinate
     * @param f destination coordinate
     * @return Collection<Coordinate>
     * @throws IlegalArgumentException
     */
    public static Collection<Coordinate> getCoordinateBetween(Coordinate s, Coordinate f) throws IlegalArgumentException{
        if(s==null || f==null) throw new IlegalArgumentException();
        int x=Math.abs(s.getX()-f.getX());
        int y=Math.abs(s.getY()-f.getY());
        if(!((x==0 ^ y==0) ^ (x==y))) throw new IlegalArgumentException();

        Set<Coordinate> data = new TreeSet<Coordinate>();
        Coordinate temp=s;

            while(!temp.equals(f)){

                    temp = getCoordinate(temp.getX() + znamenko(temp.getX() - f.getX()), temp.getY() + znamenko(temp.getY() - f.getY()));

                data.add(temp);

            }
            data.remove(f);
        
        return data;
    }

    private static int znamenko(int i){
        if(i>0)return -1;
        if(i<0)return +1;
        return 0;
    }

    /**
     * vrátí x souřadnici
     * @return int
     */
    public int getX(){
        return x;
    }

    /**
     * vrátí y souřadnici
     * @return int
     */
    public int getY(){
        return y;
    }

    public int compareTo(Coordinate o){
        if(y==o.getY()){
        if(x==o.getX()) return 0;
        if(x>o.getX()) return 1;
        return -1;
        }
        if(y>o.getY()) return 1;
        return -1;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Coordinate){
            return compareTo((Coordinate)o)==0;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return hashCode(this.x, this.y);
    }

    private static int hashCode(int x,int y){
        int hash = 3;
        hash = 71 * hash + x;
        hash = 71 * hash + y;
        return hash;
    }

    @Override
    public String toString(){
        return "("+x+","+y+")";
    }

}

package net2;

import businesstier.Game;
import businesstier.StateGame;
import core.Coordinate;

import core.Player;
import core.Token;
import core.exceptions.GameException;

import java.io.IOException;
import java.net.SocketException;
import net.mesage.*;

import presentationtier.dialogs.AbstractThreadInteruptError;
import shogi.Globals;

/**
 * druhá verze síťové komunikace
 * implementace contextu pro Game, kontext je určen pro síťovou hru
 * Po každé provedené operaci odšle zprávu která tuto operaci vykoná po síti k druhému uživateli
 * @author osman
 */
public class StateNetGame2 implements StateGame {


    public void start() {
        a.start();
    }

    /**
     * implementace Runnable a zdědená třída AbstractThreadInteruptError
     * zajišťuje odposlech zpráv ze sítě a jejich vyonání, při problému se síťí se hra přeruší a aktivuje se sonda
     */
    private class Listing extends AbstractThreadInteruptError implements Runnable{
        private Messages m;
        private boolean quit=false;
        private Game g;

        public Listing(Game g) {
            this.g=g;
            Globals.instance.getThreadDialogs().addThread(this,"Disconnect"); // vytvoření sondy
        }

        /**
         * získá zprávu a vykonáji pomocí execute
         */
        public void run() {
            while(net.isConnected()){
                try {
                    Messages message = net.getMessage();

                    if (message != null) {
                        message.execute(g.getGameBoard());
                        g.updateData();
                    }
                } catch (SocketException ex) {
                    Globals.instance.getFacade().endGame();
                } catch (IOException ex) {
                }
                
            }
            error(); // aktivace sondy
            g.updateData();
        }
    }

    private NetGame net;
    private Listing listing;
    private Game g;
    private Thread t;
    /**
     * vlákno čekající na odstartování odposlechu do té doby dokavaď se hra nepřípojí
     */
    private Thread a = new Thread(){

            @Override
            public void run() {
                while(!net.isConnected()){
                    try {
                        sleep(500);
                    } catch (InterruptedException ex) {

                    }
                }
                t.start();
                interrupt();
            }

        };

    /**
     * Vytvoří server a vygeneruje MsgSync podle zadaného parametru
     * a zahájení komunikace
     * @param g hra
     * @param p hráč který začíná z pohledu serveru
     * @throws IOException
     */
    public StateNetGame2(Game g, Player p) throws IOException {
        this.g = g;
        net = new NetGame();
        net.createGame(new MsgSync(p));

        listing = new Listing(g);

        t = new Thread(listing);
        if(p==Player.PLAYER2) g.switchPlayer();
        g.updateData();

    }

    /**
     * připojení k serveru a zahájení komunikace
     * @param g
     * @param address
     * @throws IOException
     */
    public StateNetGame2(Game g,String address) throws IOException {
        this.g=g;
        net=new NetGame();
        net.connectGame(address);

        listing = new Listing(g);
        t = new Thread(listing);
        g.updateData();
    }


    public void promote(Coordinate source) throws GameException {
        try {
            Messages m = new MsgPromote(source);
            net.sendMessage(m);
        } catch (SocketException ex) {
            
            throw new GameException("Spojení přerušeno");
        } catch (IOException ex) {
            
            throw new GameException("Chyba spojení");
        }
        g.updateData();

    }

    public void addToken(Coordinate d, Token t) throws GameException {

            Messages m = new MsgPutToken(t.getEnumToken(), d);
        try {
            net.sendMessage(m);
        } catch (SocketException ex) {
            
            throw new GameException("Spojení přerušeno");
        } catch (IOException ex) {
            
            throw new GameException("Chyba spojení");
        }
        g.updateData();
       
    }

    public boolean isEnableTurn() {
        return g.getPlayerOnTurn()==Player.PLAYER1  && net.isConnected();
    }

    public void newGame() {
        g.updateData();
    }

    public void endGame() {
        t.interrupt();
        try {           
            net.sendMessage(new MsgDisconnect());            
        } catch (Exception ex) {
        }
        net.close();
        //g.dataUpdate();
        
    }

    public void move(Coordinate source, Coordinate destination) throws GameException {
        try {
            Messages m = new MsgMove(source, destination);
            net.sendMessage(m);
        } catch (SocketException ex) {
            
            throw new GameException("Spojení přerušeno");
            
        } catch (IOException ex) {
            
           throw new GameException("Chyba spojení");
        }
        g.updateData();
    }

    public void endTurn() throws GameException {
        try {
            Messages m = new MsgEndRound();
            net.sendMessage(m);
            g.switchPlayer();
        } catch (SocketException ex) {
            
            throw new GameException("Spojení přerušeno");
        } catch (IOException ex) {
            
            throw new GameException("Chyba spojení");
        }
        g.updateData();
    }
}

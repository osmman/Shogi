
package net2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import net.mesage.Messages;
import presentationtier.dialogs.AbstractThreadInteruptError;
import shogi.Globals;




/**
 * implementace interfasu Net + vytvoření serveru pro hru nebo připojení k serveru
 * @author osman
 */
public class NetGame implements Net{

    private Socket socket;
    private ServerSocket serverSocket;
    private final int PORT=44444;
    private ObjectInputStream vstup;
    private ObjectOutputStream vystup;

    /**
     * třída implementující interface  Runnable pro a je potomkem AbstractThreadInteruptError
     * Tato třída vytvoří ServerSocket a naváže spojení s klientem v Treadu
     */
    private class CreateRunnable extends AbstractThreadInteruptError implements Runnable{

        Messages sync;

        /**
         * @param sync synchronizační zpráva
         */
        public CreateRunnable(Messages sync) {
            this.sync=sync;
            Globals.instance.getThreadDialogs().addThread(this, "Nelze vytvořit hru"); //vytvoření sondy
        }


        /**
         * vytvoří spojení s clientem pokud nastane nejaká chyba zruší se spojení a aktivuje se sonda ThreadInteruptError
         */
        public void run() {
            serverSocket=null;
            try {
                serverSocket = new ServerSocket(PORT);
                socket = serverSocket.accept();
                
                vystup = new ObjectOutputStream(socket.getOutputStream());
                vstup = new ObjectInputStream(socket.getInputStream());
                sendMessage(sync);
                serverSocket.close();
            } catch (Exception ex) {
                try {
                    serverSocket.close();
                } catch (Exception ex1) {
                }
                Globals.instance.getFacade().endGame();
                error(); // aktivace sondy
                
            }
            Globals.instance.updateData();

        }


    }

    public NetGame() throws IOException{
    }

    /**
     * vytvoří vlákno a v něm vytvoří server pro hru a čeká dokud se někdo nepřipojí
     * @param m
     * @throws IOException
     */
    public void createGame(Messages m) throws IOException{

        Thread newGame = new Thread(new CreateRunnable(m));
        newGame.start();
    }

    /**
     * připojení k serveru
     * @param address adresa serveru
     * @throws UnknownHostException
     * @throws IOException
     */
    public void connectGame(String address) throws UnknownHostException, IOException{
        socket=new Socket(address, PORT);
        socket.setSoTimeout(3000);
        this.vystup= new ObjectOutputStream(socket.getOutputStream());
        this.vstup = new ObjectInputStream(socket.getInputStream());

    }

    public Messages getMessage() throws SocketException,IOException{
        try {
            Object o = vstup.readObject();
            if (o instanceof Messages) {
                return (Messages) o;
            }
            
        } catch (ClassNotFoundException ex) {
            close();
        }
        return null;
    }

    public void sendMessage(Messages m) throws SocketException,IOException {
        vystup.writeObject(m);
    }

    public void close() {
        try {
            socket.close();
        } catch (Exception ex) {
            
        }
        try {
            vstup.close();
        } catch (Exception ex) {

        }
        try {
            vystup.close();
        } catch (Exception ex) {

        }
        try {
            serverSocket.close();
        } catch (Exception ex) {
        }
        socket=null;
        
    }

    public boolean isConnected() {
       if(socket==null) return false;
       return !socket.isClosed() && socket.isConnected();
    }  

}

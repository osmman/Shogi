package net2;

import java.io.IOException;
import java.net.SocketException;
import net.mesage.Messages;

/**
 * interface pro základní práci se síťí odesíláni přijímání zpráv
 * @author osman
 */
public interface Net {
    /**
     * získá zprávu ze síťová komunikace
     * @return Messages nebo null pokud příchozí objekt nebyl instancí Messages
     * @throws SocketException
     * @throws IOException
     */
    public Messages getMessage() throws SocketException,IOException;
    /**
     * pošle zprávu
     * @param m zpráva
     * @throws SocketException
     * @throws IOException
     */
    public void sendMessage(Messages m) throws SocketException,IOException;
    /**
     * uzavře spojení
     */
    public void close();
    /**
     * vrací stav připojení
     * @return true připojen, false odpojen
     */
    public boolean isConnected();
}

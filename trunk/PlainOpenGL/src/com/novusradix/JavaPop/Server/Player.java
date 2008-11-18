package com.novusradix.JavaPop.Server;

import com.novusradix.JavaPop.Messaging.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author erinhowie
 */
public class Player implements Runnable {

    public Server s;
    public Socket socket;
    public Game currentGame;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    public String name;
    enum PlayerState{InServerLobby, InGameLobby, InGame};
    
    public Player(Server s, Socket socket) {
        this.s = s;
        this.socket = socket;
        name = "No name";
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
                    
            ois = new ObjectInputStream(socket.getInputStream());
            (new Thread(this, "Server Player")).start();
        } catch (IOException ioe) {
        }
    }

    public void run() {

        //Message loop

        try {

            Message message;
            while (socket.isConnected()) {
                message = (Message) ois.readObject();
                message.server = s;
                message.serverGame = currentGame;
                message.serverPlayer = this;
                message.execute();
            }
        } catch (IOException ioe) {
        } catch (ClassNotFoundException cnfe) {
        }
    //Disconnect
    }

    public synchronized void sendMessage(Message m)  {
        try {
            oos.writeObject(m);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
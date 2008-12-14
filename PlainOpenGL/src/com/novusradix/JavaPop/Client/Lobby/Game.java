/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novusradix.JavaPop.Client.Lobby;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import com.novusradix.JavaPop.Server.GameInfo;
import com.novusradix.JavaPop.Server.Player.Info;

/**
 *
    Game(GameInfo gi) {
        id = gi.id;
        playerList = new DefaultListModel();
        for (String player : gi
 * @author erinhowie
 */
public class Game {

    DefaultListModel playerList;
    int id;

    Game(GameInfo gi) {
        id = gi.id;
        playerList = new DefaultListModel();
        for (Info player : gi.players.values()) {
            playerList.addElement(player.name);
        }
    }

    public ListModel getPlayerList() {
        return playerList;
    }

    void update(GameInfo gi) {

        playerList.clear();
        for (Info player : gi.players.values()) {
            playerList.addElement(player.name);
        }
    }
}

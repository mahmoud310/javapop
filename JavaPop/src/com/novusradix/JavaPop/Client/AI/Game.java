package com.novusradix.JavaPop.Client.AI;

import com.novusradix.JavaPop.Client.Peons;
import com.novusradix.JavaPop.Messaging.Lobby.GameStarted;
import com.novusradix.JavaPop.Client.Player;
import com.novusradix.JavaPop.Server.Player.Info;
import java.util.HashMap;

/**
 *
 * @author gef
 */
public class Game extends com.novusradix.JavaPop.Client.Game {

    com.novusradix.JavaPop.Client.AI.Houses AIHouses;
    AI ai;
    public Game(GameStarted g, Client c) {
      
        heightMap = new HeightMap(g.gi.mapSize, this);
        client = c;
        peons = new Peons(this);
        AIHouses =  new Houses(this);
        houses = AIHouses;
        players = new HashMap<Integer, Player>();
        int index = 0;
        for (Info i : g.gi.players.values()) {
            Player p = new Player(i, this, index++);
            players.put(i.id, p);
            if (i.id == c.info.id) {
                me = p;
            }
        }
        ai = new AI(this);
        startTimer();
    }
    
    @Override
    public void kill()
    {
       super.kill();
       ai.kill();
    }
    
}
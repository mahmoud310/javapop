/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.novusradix.JavaPop.Messaging;

import com.novusradix.JavaPop.Server.Game;
import com.novusradix.JavaPop.Server.GameInfo;
import java.awt.Dimension;

/**
 *
 * @author erinhowie
 */
public class GameStarted extends Message{

    public GameInfo gi;
    
    public GameStarted(Game g)
    {
      gi = new GameInfo(g);
    
    }

    @Override
    public void execute() {
        client.newGame(this);
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novusradix.JavaPop.Messaging;

import com.novusradix.JavaPop.Server.Player.Info;
import java.awt.Point;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author mom
 */
public class PlayerUpdate extends Message implements Externalizable {
 private static final long serialVersionUID = 1L;
   
    private Collection<Info> info;

    public PlayerUpdate(Info i) {
        this.info = new ArrayList<Info>(1);
        this.info.add(i);
    }

    public PlayerUpdate(Collection<Info> is) {
        this.info = is;
    }

    @Override
    public void execute() {
        for (Info i : info) {
            clientGame.players.get(i.id).update(i);
        }
    }
    
    public PlayerUpdate() {
    }
    
    public void writeExternal(ObjectOutput o) throws IOException {
        o.writeInt(info.size());
        for (Info i : info) {
            o.writeInt(i.id);
            o.writeUTF(i.name);
            o.writeInt(i.ankh.x);
            o.writeInt(i.ankh.y);
            o.writeFloat(i.colour[0]);
            o.writeFloat(i.colour[1]);
            o.writeFloat(i.colour[2]);
            o.writeDouble(i.mana);
        }
    }

    public void readExternal(ObjectInput i) throws IOException, ClassNotFoundException {
        info = new ArrayList<Info>();
        int n = i.readInt();
        for (; n > 0; n--) {
            Info inf = new Info();
            inf.id = i.readInt();
            inf.name = i.readUTF();
            inf.ankh = new Point();
            inf.ankh.x = i.readInt();
            inf.ankh.y = i.readInt();
            inf.colour = new float[3];
            inf.colour[0] = i.readFloat();
            inf.colour[1] = i.readFloat();
            inf.colour[2] = i.readFloat();
            inf.mana = i.readDouble();
            info.add(inf);
        }
    }
}
